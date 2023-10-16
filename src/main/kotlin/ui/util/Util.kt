package ui.util

import java.time.*
import java.time.format.DateTimeFormatter
import javax.swing.ImageIcon

fun resizeIcon(icon: ImageIcon, width: Int, height: Int): ImageIcon {
    val image = icon.image
    val newImage = image.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH)
    return ImageIcon(newImage)
}

fun getDayOfWeekFromUTC(utcTimestamp: Long, offsetInSeconds: Int): DayOfWeek {
    val instant = Instant.ofEpochMilli(utcTimestamp*1000)
    val offset = ZoneOffset.ofTotalSeconds(offsetInSeconds)
    val offsetDateTime = instant.atOffset(offset)
    return offsetDateTime.dayOfWeek
}

fun convertUTCToLocalHour(utcTimestamp: Long, offsetInSeconds: Int): String {
    val instant = Instant.ofEpochMilli(utcTimestamp*1000)
    val offset = ZoneOffset.ofTotalSeconds(offsetInSeconds)
    val offsetDateTime = instant.atOffset(offset)
    val formatter = DateTimeFormatter.ofPattern("HH:mm:ss")
    return formatter.format(offsetDateTime)
}