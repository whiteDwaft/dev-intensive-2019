package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.HashMap

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern:String="HH:mm:ss dd.MM.yy"):String{
    val dateFormat = SimpleDateFormat(pattern,Locale("ru"))
    return dateFormat.format(this)
}
fun Date.add(value:Int, units: TimeUnits = TimeUnits.SECOND):Date{
    var time = this.time
    time += when(units){
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY

    }
    this.time = time
    return this
}

fun Date.humanizeDiff(date: Date = Date()): String {
    val diff:Long = date.time - this.time
    val map:HashMap<String,Long> = HashMap()
    map.put("YEARS", Math.abs(diff)/1000/60/60/24/365)
    map.put("DAYS",Math.abs(diff)/1000/60/60/24)
    map.put("HOURS", Math.abs(diff)/1000/60/60)
    map.put("MINUTES",Math.abs(diff)/1000/60)
    map.put("SECONDS",Math.abs(diff)/1000)

    val vocabulary:HashMap<String,Array<String>> = HashMap()
    vocabulary.put("YEARS",arrayOf("год","года","лет"))
    vocabulary.put("DAYS",arrayOf("день","дня","дней"))
    vocabulary.put("HOURS",arrayOf("час","часа","часов"))
    vocabulary.put("MINUTES",arrayOf("минуту","минуты","минут"))
    vocabulary.put("SECONDS",arrayOf("секунду","секунды","секунд"))

    val min:Map.Entry<String, Long>? = map.filter { (_,item:Long) -> item != 0L}.minBy { (_,item:Long) -> item}
    if(diff > 0) {
        when (min!!.key) {
            "SECONDS" -> when (min.value) {
                in 0L..1L -> return "только что"
                in 2L..45L -> return "несколько секунд назад"
                else -> return "минуту назад"
            }
            "MINUTES" -> when (min.value) {
                1L -> if(map.get("SECONDS")!! <= 75) return "минуту назад" else return "1 минуту назад"
                in 2L..45L -> when (min.value % 10) {
                    1L -> return "${min.value} ${vocabulary.get(min.key)?.get(0)} назад"
                    in 2L..4L -> return "${min.value} ${vocabulary.get(min.key)?.get(1)} назад"
                    else -> return "${min.value} ${vocabulary.get(min.key)?.get(2)} назад"
                }
                else -> return "час назад"
            }
            "HOURS" -> when (min.value) {
                1L -> if(map.get("MINUTES")!! <= 75) return "час назад" else return "1 час назад"
                in 2L..22L -> when (min.value % 10) {
                    1L -> return "${min.value} ${vocabulary.get(min.key)?.get(0)} назад"
                    in 2L..4L -> return "${min.value} ${vocabulary.get(min.key)?.get(1)} назад"
                    else -> return "${min.value} ${vocabulary.get(min.key)?.get(2)} назад"
                }
                else -> return "день назад"
            }
            "DAYS" -> when (min.value) {
                1L -> if(map.get("HOURS")!! <= 26) return "день назад" else return "1 день назад"
                in 2L..360L -> when (min.value % 10) {
                    1L -> return "${min.value} ${vocabulary.get(min.key)?.get(0)} назад"
                    in 2L..4L -> return "${min.value} ${vocabulary.get(min.key)?.get(1)} назад"
                    else -> return "${min.value} ${vocabulary.get(min.key)?.get(2)} назад"
                }
                else -> return "более года назад"
            }
            else -> return "более года назад"
        }
    }
    else {
        when (min!!.key) {
            "SECONDS" -> when (min.value) {
                in 0L..1L -> return "только что"
                in 2L..45L -> return "через несколько секунд "
                else -> return "через минуту "
            }
            "MINUTES" -> when (min.value) {
                1L -> if(map.get("SECONDS")!! <= 75) return "через минуту" else return "через 1 минуту"
                in 2L..45L -> when (min.value % 10) {
                    1L -> return "через ${min.value} ${vocabulary.get(min.key)?.get(0)}"
                    in 2L..4L -> return "через ${min.value} ${vocabulary.get(min.key)?.get(1)}"
                    else -> return "через ${min.value} ${vocabulary.get(min.key)?.get(2)}"
                }
                else -> return "через час"
            }
            "HOURS" -> when (min.value) {
                1L -> if(map.get("MINUTES")!! <= 75) return "через час" else return "через 1 час"
                in 2L..22L -> when (min.value % 10) {
                    1L -> return "через ${min.value} ${vocabulary.get(min.key)?.get(0)}"
                    in 2L..4L -> return "через ${min.value} ${vocabulary.get(min.key)?.get(1)}"
                    else -> return "через ${min.value} ${vocabulary.get(min.key)?.get(2)}"
                }
                else -> return "через день"
            }
            "DAYS" -> when (min.value) {
                1L -> if(map.get("HOURS")!! <= 26) return "через день" else return "через 1 день"
                in 2L..360L -> when (min.value % 10) {
                    1L -> return "через ${min.value} ${vocabulary.get(min.key)?.get(0)}"
                    in 2L..4L -> return "через ${min.value} ${vocabulary.get(min.key)?.get(1)}"
                    else -> return "через ${min.value} ${vocabulary.get(min.key)?.get(2)}"
                }
                else -> return "больше чем через год"
            }
            else -> return "более года назад"
        }
    }
}
enum class TimeUnits{
    SECOND,
    MINUTE,
    HOUR,
    DAY;
    fun plural(num:Int):String
    {
        val vocabulary:HashMap<String,Array<String>> = HashMap()
        vocabulary.put("DAY",arrayOf("день","дня","дней"))
        vocabulary.put("HOUR",arrayOf("час","часа","часов"))
        vocabulary.put("MINUTE",arrayOf("минуту","минуты","минут"))
        vocabulary.put("SECOND",arrayOf("секунду","секунды","секунд"))
        when(num)
        {
            1 -> return "$num ${vocabulary.get(this.name)!![0]}"
            in 2..4 -> return "$num ${vocabulary.get(this.name)!![1]}"
            else -> return "$num ${vocabulary.get(this.name)!![2]}"
        }
    }
}