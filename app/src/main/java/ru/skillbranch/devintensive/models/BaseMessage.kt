package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.extensions.TimeUnits
import ru.skillbranch.devintensive.extensions.add
import java.util.*

abstract class BaseMessage(
    val id:String,
    val from: User?,
    val chat: Chat,
    val isInComing:Boolean = false,
    val date: Date = Date()
) {
    abstract fun formatMessage():String
    companion object AbstractFactory{
        var lastId = -1
        fun makeMessage(from:User,chat:Chat,date:Date= Date(),type:String = "text",payload:Any?,isInComing:Boolean = false):BaseMessage
        {
            lastId++
            return when(type){
                "image" -> ImageMessage("$lastId",from,chat,date=date.add(-45,TimeUnits.SECOND),image = payload as String)
                else -> TextMessage("$lastId",from,chat,date=date.add(-76,TimeUnits.SECOND),text = payload as String)
            }

        }
    }
    }
