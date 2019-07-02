package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.extensions.humanizeDiff
import java.util.*

class TextMessage (
    id:String,
    from: User,
    chat: Chat,
    isInComing:Boolean = false,
    date: Date = Date(),
    var text:String?)

    :BaseMessage(id,from,chat,isInComing, date){
    override fun formatMessage(): String = "id=$id ${from?.firstName}" +
            " ${if(isInComing) "получил"  else "отправил"} сообщение \"$text\" ${date.humanizeDiff()} "
}