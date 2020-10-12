package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.extensions.humanizeDiff
import java.util.*

class ImageMessage(
    id:String,
    from: User?,
    chat: Chat,
    isIncomming: Boolean = false,
    date: Date = Date(),
    var img: String?
) : BaseMessage(id, from, chat, isIncomming, date) {
    override fun formatMessage(): String =
        "id:$id ${from?.firstName} " +
                if (isIncoming) "получил" else "отправил" +
                        """ изображение "$img" ${date.humanizeDiff()}"""

}