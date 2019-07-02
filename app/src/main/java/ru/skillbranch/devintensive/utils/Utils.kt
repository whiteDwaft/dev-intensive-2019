package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullname:String?):Pair<String?, String?>
    {
        val parts: List<String>? = fullname?.split(" ")
        var firstName = parts?.getOrNull(0)
        var lastName = parts?.getOrNull(1)
        if(firstName.equals(""))
            firstName = null
        if(lastName.equals(""))
            lastName = null
        return firstName to lastName
    }

    fun transliteration(payload: String,divider:String = " "): String {
        val alphavit: HashMap<String, String> = hashMapOf(
            "а" to "a",
            "б" to "b",
            "в" to "v",
            "г" to "g",
            "д" to "d",
            "е" to "e",
            "ё" to "e",
            "ж" to "zh",
            "з" to "z",
            "и" to "i",
            "й" to "i",
            "к" to "k",
            "л" to "l",
            "м" to "m",
            "н" to "n",
            "о" to "o",
            "п" to "p",
            "р" to "r",
            "с" to "s",
            "т" to "t",
            "у" to "u",
            "ф" to "f",
            "х" to "h",
            "ц" to "c",
            "ч" to "ch",
            "ш" to "sh",
            "щ" to "sh'",
            "ъ" to "",
            "ы" to "i",
            "ь" to "",
            "э" to "e",
            "ю" to "yu",
            "я" to "ya",
            " " to divider
//            Regex("[a-z]").toString() to Regex("[a-z]").toString()
        )
//        val sep_fullname: List<String> = payload.trim().split(" ")
//        var nick: StringBuilder = StringBuilder(sep_fullname.get(0) + divider + sep_fullname.get(1))
//        println(nick.toString())
        val newnickList: List<String?> = payload.trim().map {if(alphavit.get(it.toString().toLowerCase()).equals(null)) it.toString() else if(it.isUpperCase())
            alphavit.get(it.toString().toLowerCase())?.toUpperCase() else alphavit.get(it.toString())}
//        val iterator:Iterator<String?> = newnickList.iterator()
        var newnickStr: StringBuilder = StringBuilder()
        newnickList.forEach{newnickStr.append(it)}
        return newnickStr.toString()
    }



    fun toInitials(firstName: String?, lastName: String?): String? {
        var init:StringBuilder = StringBuilder()
        when(firstName?.trim()){
            null -> init.append(lastName)
            "" -> {}
            else -> init.append(firstName.substring(0,1).toUpperCase())
        }
        when(lastName?.trim()){
            null -> {}
            "" -> {}
            else -> if(firstName?.trim().equals(null))
                init.append(" $lastName")
                else init.append(lastName.substring(0,1).toUpperCase())
        }
        if(/*init.contains(Regex("""^\w""")) ||*/ init.isEmpty())
            return null
        else
            return init.toString()
    }

}
