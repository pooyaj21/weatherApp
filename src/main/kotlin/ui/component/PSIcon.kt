package ui.component

import javax.swing.ImageIcon
import javax.swing.JLabel
import javax.swing.SwingConstants

class PSIcon(icon: ImageIcon, text: String) : JLabel(text, icon, SwingConstants.CENTER) {
    init {
        horizontalTextPosition = SwingConstants.CENTER
        verticalTextPosition = SwingConstants.BOTTOM
    }
}
