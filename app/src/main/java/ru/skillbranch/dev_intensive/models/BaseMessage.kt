package ru.skillbranch.dev_intensive.models

import ru.skillbranch.dev_intensive.extensions.TimeUnits
import ru.skillbranch.dev_intensive.extensions.add
import java.util.*

abstract class BaseMessage(
    val id:String,
    val from: User,
    val chat: Chat,
    val isInComing:Boolean = false,
    val date: Date = Date()
) {
    abstract fun formatMessage():String
    companion object AbstractFactory{
        var lastId = -1
        fun makeMessage(from:User,chat:Chat,date:Date= Date(),type:String = "text",payload:Any?):BaseMessage
        {
            lastId++
            return when(type){
                "image" -> ImageMessage("$lastId",from,chat,date=date.add(-5,TimeUnits.MINUTE),image = payload as String)
                else -> TextMessage("$lastId",from,chat,date=date.add(-6,TimeUnits.MINUTE),text = payload as String)
            }

        }
    }
    }
