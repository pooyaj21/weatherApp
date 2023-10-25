package ui.component

import ui.util.FontEnum
import ui.util.setFont
import javax.swing.JButton
import javax.swing.JLabel

class PSButton : JButton() {
    init {
        horizontalAlignment = JLabel.CENTER
        verticalAlignment = JLabel.CENTER
        isOpaque = false
        isBorderPainted = false
        isContentAreaFilled = false
    }

}
