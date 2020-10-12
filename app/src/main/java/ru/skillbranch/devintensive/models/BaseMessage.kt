package ru.skillbranch.devintensive.models

import java.util.*

abstract class BaseMessage (val id: String,
                            val from: User?,
                            val chat: Chat,
                            val isIncoming: Boolean = false,
                            val date: Date = Date()
){
    abstract fun formatMessage(): String

    companion object Factory {
        var id = -1

        fun makeMessage(
            from: User?,
            chat:Chat,
            date:Date = Date(),
            type:String = "text",
            payload:Any?,
            isIncoming: Boolean = false
        ): BaseMessage {
            ++id;
            return when (type) {
                "text" -> TextMessage(id.toString(), from, chat, isIncoming, date, payload as String)
                "image" -> ImageMessage(id.toString(), from, chat, isIncoming, date, payload as String)
                else -> throw IllegalArgumentException(type)
            }
        }
    }
}