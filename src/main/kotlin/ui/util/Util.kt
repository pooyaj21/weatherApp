package ui.util

import java.time.Instant
import java.time.OffsetDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import javax.swing.ImageIcon

fun resizeIcon(icon: ImageIcon, width: Int, height: Int): ImageIcon {
    val image = icon.image
    val newImage = image.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH)
    return ImageIcon(newImage)
}

fun convertUTCToLocalDate(utcTimestamp: Long, offsetInSeconds: Int): String {
    val instant = Instant.ofEpochMilli(utcTimestamp)
    val offset = ZoneOffset.ofTotalSeconds(offsetInSeconds)
    val offsetDateTime = OffsetDateTime.ofInstant(instant, offset)
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    return formatter.format(offsetDateTime)
}

fun convertUTCToLocalHour(utcTimestamp: Long, offsetInSeconds: Int): String {
    val instant = Instant.ofEpochMilli(utcTimestamp)
    val offset = ZoneOffset.ofTotalSeconds(offsetInSeconds)
    val offsetDateTime = OffsetDateTime.ofInstant(instant, offset)
    val formatter = DateTimeFormatter.ofPattern("HH:mm:ss")
    return formatter.format(offsetDateTime)
}