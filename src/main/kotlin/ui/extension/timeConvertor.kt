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
    val dateFormat = SimpleDateFormat("HH:mm")
    dateFormat.timeZone = timeZone
    return dateFormat.format(this)
}