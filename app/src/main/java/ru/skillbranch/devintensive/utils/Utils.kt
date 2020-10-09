package ru.skillbranch.devintensive.utils

object Utils {

    fun parseFullName(fullName: String?): Pair<String?, String?> {
        val parts = fullName?.split(" ")
        return parts?.get(0) to parts?.get(1)
    }

    fun toInitials(firstName: String?, lastName: String?): String? =
        listOfNotNull(
            firstName?.firstOrNull { it != ' ' },
            lastName?.firstOrNull { it != ' ' }
        ).takeIf { it.isNotEmpty() }?.joinToString("")

    fun  transliteration(text: String, divider: String = " "): String {
        val tr = mapOf<Char, String>(
            'а' to "a",
            'б' to "b",
            'в' to "v",
            'г' to "g",
            'д' to "d",
            'е' to "e",
            'ё' to "e",
            'ж' to "zh",
            'з' to "z",
            'и' to "i",
            'й' to "i",
            'к' to "k",
            'л' to "l",
            'м' to "m",
            'н' to "n",
            'о' to "o",
            'п' to "p",
            'р' to "r",
            'с' to "s",
            'т' to "t",
            'у' to "u",
            'ф' to "f",
            'х' to "h",
            'ц' to "c",
            'ч' to "ch",
            'ш' to "sh",
            'щ' to "sh'",
            'ъ' to "",
            'ы' to "i",
            'ь' to "",
            'э' to "e",
            'ю' to "yu",
            'я' to "ya"
        )
        text.trim().replace("\\s+".toRegex(), divider)
        var result = ""
        for (ch in text) {
            result += tr.getOrElse(ch, {ch})
        }
        return result
    }
}