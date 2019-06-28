package ru.skillbranch.dev_intensive.extensions

import java.lang.IllegalStateException
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
    map.put("YEARS", diff/1000/60/60/24/365)
    map.put("DAYS",diff/1000/60/60/24)
    map.put("HOURS", diff/1000/60/60)
    map.put("MINUTES",diff/1000/60)
    map.put("SECONDS",diff/1000)

    val vocabulary:HashMap<String,Array<String>> = HashMap()
    vocabulary.put("YEARS",arrayOf("год","года","лет"))
    vocabulary.put("DAYS",arrayOf("день","дня","дней"))
    vocabulary.put("HOURS",arrayOf("час","часа","часов"))
    vocabulary.put("MINUTES",arrayOf("минуту","минуты","минут"))
    vocabulary.put("SECONDS",arrayOf("секунду","секунды","секунд"))

    val min:Map.Entry<String, Long>? = map.filter { (_,item:Long) -> item != 0L}.minBy { (_,item:Long) -> item}
    when(min!!.value%10){
        1L -> return "${min.value} ${vocabulary.get(min.key)?.get(0)} назад"
        in 2L..4L -> return "${min.value} ${vocabulary.get(min.key)?.get(1)} назад"
        else -> return "${min.value} ${vocabulary.get(min.key)?.get(2)} назад"
    }
//    return "${min!!.value} ${min!!.key}"

//    val lists: List<String> = this.format().split(" ")
//    val timeList:List<String> = lists.get(0).split(":")
//    val dateList:List<String> = lists.get(1).split(".")

//    val iterator:Iterator<String> = list.iterator()
//    while(iterator.hasNext() && iterator.equals("00"))
//        iterator.next()
//    when(iterator.)
//    {
//        1 -> return "год назад"
//        0 ->
//        else-> return "более года назад"
//    }
//    val x:Long = date.time - this.time
//    when(x)
//    {
//        in 1..60000 -> return "несколько секунд назад"
//        else -> when(x/60000){
//            1 -> return "минуту назад"
//            2..4 -> return "$x минуты назад"
//            5
//        }
//    }
}
enum class TimeUnits{
    SECOND,
    MINUTE,
    HOUR,
    DAY
}