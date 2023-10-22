package ui.util

import java.awt.Font
import java.awt.FontFormatException
import java.io.File
import java.io.IOException
import java.time.*
import java.time.format.DateTimeFormatter
import javax.swing.ImageIcon
import javax.swing.JComponent

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
    val formatter = DateTimeFormatter.ofPattern("HH:mm")
    return formatter.format(offsetDateTime)
}

fun JComponent.setFont(fontType: FontEnum?, size: Int) {
    val fontFilePath = when (fontType) {
        FontEnum.BOLD -> "assets/Font/Quicksand-Bold.ttf"
        FontEnum.LIGHT -> "assets/Font/Quicksand-Light.ttf"
        FontEnum.MEDIUM -> "assets/Font/Quicksand-Medium.ttf"
        FontEnum.REGULAR -> "assets/Font/Quicksand-Regular.ttf"
        FontEnum.SEMI_BOLD -> "assets/Font/Quicksand-SemiBold.ttf"
        else -> {
            println("Invalid font type")
            return
        }
    }

    try {
        val fontFile = File(fontFilePath)
        val customFont = Font.createFont(Font.TRUETYPE_FONT, fontFile)
        val customFontSized = customFont.deriveFont(size.toFloat())
        this.font = customFontSized
    } catch (e: IOException) {
        e.printStackTrace()
    } catch (e: FontFormatException) {
        e.printStackTrace()
    }
}