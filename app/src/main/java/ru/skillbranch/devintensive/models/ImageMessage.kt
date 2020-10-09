package ru.skillbranch.devintensive.models

class ImageMessage(id: String, from: User?, chat: Chat, val img: String) : BaseMessage(id, from, chat) {
    override fun formatMessage(): String =
        "id:$id ${from?.firstName} " +
                if (isIncoming) "получил" else "отправил" +
                        """ изображение "$img" """

}