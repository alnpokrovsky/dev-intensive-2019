package ru.skillbranch.devintensive.models

class TextMessage(id: String, from: User?, chat: Chat, val text: String) : BaseMessage(id, from, chat) {
    override fun formatMessage(): String =
        "id:$id ${from?.firstName} " +
                if (isIncoming) "получил" else "отправил" +
                    """ cообщение "$text" """
}
