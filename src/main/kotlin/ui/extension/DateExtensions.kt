package ui.extension

import java.text.SimpleDateFormat
import java.util.*

fun Date.convertToDayOfWeek(timeZone: TimeZone): String {
    val calendar = Calendar.getInstance(timeZone)
    calendar.time = this
    val dateFormat = SimpleDateFormat("EEEE")
    return dateFormat.format(calendar.time)
}

fun Date.convertToCurrentHour(timeZone: TimeZone): String {
    val calendar = Calendar.getInstance(timeZone)
    calendar.time = this
    val dstOffset = if (timeZone.inDaylightTime(this)) timeZone.dstSavings else 0
    val totalOffsetMillis = timeZone.rawOffset + dstOffset
    calendar.add(Calendar.MILLISECOND, -totalOffsetMillis)
    val dateFormat = SimpleDateFormat("HH:mm")
    return dateFormat.format(calendar.time)
}