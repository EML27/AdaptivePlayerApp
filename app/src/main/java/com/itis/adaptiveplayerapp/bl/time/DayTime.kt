package com.itis.adaptiveplayerapp.bl.time

import java.text.SimpleDateFormat
import java.util.*

class DayTime {
    private fun getTimeSegmentNumber(): Int {
        val date = Date()
        val timeFormat = SimpleDateFormat("HH", Locale.getDefault())
        val currentHour = timeFormat.format(date)
        val hour = currentHour.toInt()
        val res: Int
        res = when (hour) {
            in 4..8 -> 1 // morning
            in 9..14 -> 2 // noon
            in 15..20 -> 3 //evening
            else -> 0 // night
        }
        return res
    }


    fun isMorning(): Boolean {
        return getTimeSegmentNumber() == 1
    }

    fun isNoon(): Boolean {
        return getTimeSegmentNumber() == 2
    }

    fun isEvening(): Boolean {
        return getTimeSegmentNumber() == 3
    }

    fun isNight(): Boolean {
        return getTimeSegmentNumber() == 0
    }

    fun getTimeOfDay(): String {
        return when {
            isMorning() -> TimeOfDay.MORNING
            isNoon() -> TimeOfDay.NOON
            isEvening() -> TimeOfDay.EVENING
            isNight() -> TimeOfDay.NIGHT
            else -> TimeOfDay.NOON
        }.toString()
    }
}