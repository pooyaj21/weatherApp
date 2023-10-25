package ui.component

import ui.extension.FontEnum
import ui.extension.setFont
import javax.swing.JLabel

open class PSLabel : JLabel() {
    init {
        setFont(FontEnum.SEMI_BOLD, 20)
        horizontalAlignment = CENTER
        verticalAlignment = CENTER
    }
}
