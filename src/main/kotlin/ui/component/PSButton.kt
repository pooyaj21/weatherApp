package ui.component

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
