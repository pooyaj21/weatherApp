package convertors

import java.util.*

fun Int.toTimeZone(): TimeZone {
    val offset = this
    val sign = if (offset >= 0) 1 else -1
    val hours = (Math.abs(offset) / (60 * 60 * 1000))
    val minutes = ((Math.abs(offset) / (60 * 1000)) % 60)
    val timeZoneId = "GMT${if (sign == 1) "+" else "-"}$hours:${String.format("%02d", minutes)}"
    return TimeZone.getTimeZone(timeZoneId)
}