package ru.skillbranch.dev_intensive.models

import ru.skillbranch.dev_intensive.extensions.humanizeDiff
import java.util.*

class ImageMessage(
    id:String,
    from: User,
    chat: Chat,
    isInComing:Boolean = false,
    date: Date = Date(),
    var image:String?)

    :BaseMessage(id,from,chat,isInComing, date) {
    override fun formatMessage(): String = "id=$id ${from?.firstName}" +
            " ${if(isInComing) "получил"  else "отправил"} изображение \"$image\" ${date.humanizeDiff()} "
}