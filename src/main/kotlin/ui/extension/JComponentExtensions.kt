package ui.extension

import ui.model.FontEnum
import java.awt.Font
import java.awt.FontFormatException
import java.io.File
import java.io.IOException
import javax.swing.JComponent

fun JComponent.setFont(fontType: FontEnum, size: Int) {
    val fontFilePath = when (fontType) {
        FontEnum.BOLD -> "assets/Font/Quicksand-Bold.ttf"
        FontEnum.LIGHT -> "assets/Font/Quicksand-Light.ttf"
        FontEnum.MEDIUM -> "assets/Font/Quicksand-Medium.ttf"
        FontEnum.REGULAR -> "assets/Font/Quicksand-Regular.ttf"
        FontEnum.SEMI_BOLD -> "assets/Font/Quicksand-SemiBold.ttf"
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

