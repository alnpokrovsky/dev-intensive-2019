package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.absoluteValue


enum class TimeUnits(val ms: Int, val russianCases: Array<String>) {
    SECOND(1000, arrayOf("секунд", "секунду", "секунды")),
    MINUTE(60 * SECOND.ms, arrayOf("минут", "минуту", "минуты")),
    HOUR(60 * MINUTE.ms, arrayOf("часов", "час", "часа")),
    DAY(24 * HOUR.ms, arrayOf("дней", "день", "дня"));

    fun plural(value: Long): String {
        val case = when {
            value % 100 in 5..20 -> 0
            value % 10 in 2..4 -> 2
            value % 10 == 1L -> 1
            else -> 0
        }
        return "${value.absoluteValue} ${this.russianCases[case]}"
    }

}

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, units: TimeUnits): Date {
    this.time += value * units.ms
    return this
}


val Int.sec get() = this * TimeUnits.SECOND.ms
val Int.min get() = this * TimeUnits.MINUTE.ms
val Int.hour get() = this * TimeUnits.HOUR.ms
val Long.day get() = this * TimeUnits.DAY.ms

fun Date.humanizeDiff(date: Date = Date()) =
    when(val diff: Long = date.time - time) {
        in 0..1.sec -> "только что"
        in 1.sec..45.sec -> "несколько секунд назад"
        in 45.sec..75.sec -> "минуту назад"
        in 75.sec..45.min -> "${TimeUnits.MINUTE.plural(diff/TimeUnits.MINUTE.ms)} назад"
        in 45.min..75.min-> "час назад"
        in 75.min .. 22.hour -> "${TimeUnits.HOUR.plural(diff/TimeUnits.HOUR.ms)} назад"
        in 22.hour .. 26.hour -> "день назад"
        in 27.hour..360L.day -> "${TimeUnits.DAY.plural(diff/TimeUnits.DAY.ms)} назад"
        in (-1).sec..0.sec -> "прямо сейчас"
        in (-45).sec..(-1).sec -> "через несколько секунд"
        in (-75).sec..(-45).sec -> "через минуту"
        in (-45).min..(-75).sec -> "через ${TimeUnits.MINUTE.plural(diff/TimeUnits.MINUTE.ms)}"
        in (-75).min..(-45).min -> "через час"
        in (-22).hour..(-75).min -> "через ${TimeUnits.HOUR.plural(diff/TimeUnits.HOUR.ms)}"
        in (-26).hour..(-22).hour -> "через день"
        in (-360L).day..(-26).hour -> "через ${TimeUnits.DAY.plural(diff/TimeUnits.DAY.ms)}"
        else -> if (diff > 0) "более года назад" else "более чем через год"
    }
