package ui.component

import ui.util.FontEnum
import ui.util.RoundedTextField
import ui.util.setFont
import java.awt.Color

class PSTextField(
    columns: Int, cornerRadius: Int, backgroundColor: Color, foregroundColor: Color, fontType: FontEnum, fontSize: Int
) : RoundedTextField(
    columns, cornerRadius,
    backgroundColor,
    foregroundColor,
) {
    init {
        setFont(fontType, fontSize)
    }

}