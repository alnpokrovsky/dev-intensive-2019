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
        var id = 0

        fun makeMessage(from: User, chat: Chat, date: Date, type: String, payload: String, isIncoming: Boolean = false) =
            when (type) {
                "text" -> TextMessage((++id).toString(), from, chat, payload)
                "image" -> ImageMessage(id++.toString(), from, chat, payload)
                else -> throw IllegalArgumentException(type)
            }
    }
}