package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.utils.Utils
import java.util.*

data class User (val id : String,
                 var firstName : String?,
                 var lastName : String?,
                 var avatar : String?,
                 var rating : Int = 0,
                 var respect : Int = 0,
                 var lastVisit : Date? = Date(),
                 var isOnline : Boolean = false)
{
    constructor(id: String,
                firstName: String?,
                lastName: String?) : this(id, firstName, lastName, null)

    class Builder {
        var id: String? = null
        var firstName: String? = null
        var lastName: String? = null
        var avatar: String? = null
        var rating: Int = 0
        var respect: Int = 0
        var lastVisit: Date? = null
        var isOnline: Boolean = false

        fun id(id: String) = apply { this.id = id }
        fun firstName(firstName: String) = apply { this.firstName = firstName }
        fun lastName(lastName: String) = apply { this.lastName = lastName }
        fun avatar(avatar: String) = apply { this.avatar = avatar }
        fun rating(rating: Int) = apply { this.rating = rating }
        fun respect(respect: Int) = apply { this.respect = respect }
        fun lastVisit(lastVisit: Date) = apply { this.lastVisit = lastVisit }
        fun isOnline(isOnline: Boolean) = apply { this.isOnline = isOnline }

        fun Build(): User = makeUser(this)
    }

    companion object Factory {
        var id = 0
        fun makeUser(fullName: String): User {
            val (f, l) = Utils.parseFullName(fullName)
            return User(id++.toString(), f, l)
        }
        fun makeUser(builder: Builder): User =
            User(
                (builder.id ?: id++).toString(),
                builder.firstName,
                builder.lastName,
                builder.avatar,
                builder.rating,
                builder.respect,
                builder.lastVisit,
                builder.isOnline
            )
    }


}