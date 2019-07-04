package ru.skillbranch.devintensive.extensions

fun TimeUnits.plural(num:Int):String
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
