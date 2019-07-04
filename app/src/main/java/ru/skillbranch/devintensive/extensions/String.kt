package ru.skillbranch.devintensive.extensions

fun String.truncate(count:Int = 16):String{
    try {
        val sub = this.substring(0, count)
        when(sub.equals(this))
        {
            true -> return this
            else -> if(sub.trimEnd().equals(this.trimEnd())) return this.trimEnd() else return sub.trimEnd().plus("...")
        }
    }
    catch(e:StringIndexOutOfBoundsException){
        return this
    }
}
fun String.stripHtml():String{
    val patterns:Array<Regex> = arrayOf(Regex("<+>"),Regex("&lt;"),Regex("&gt;"),
        Regex("&amp;"),Regex("quot;"))
//    this.trimMargin()
    val list:List<String> =  this.split(Regex("<.+?>|&gt;|&lt;|&amp;|&quot;|&nbsp;| +"))
    var newnickStr: StringBuilder = StringBuilder()
    list.forEach{newnickStr.append("$it ")}
    return newnickStr.toString().replace(Regex(" +")," ").trim()
}