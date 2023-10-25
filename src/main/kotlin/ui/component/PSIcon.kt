import java.awt.Font
import javax.swing.ImageIcon
import javax.swing.JLabel
import javax.swing.SwingConstants


//package ui.component
//
//import ui.util.FontEnum
//import javax.swing.ImageIcon
//import javax.swing.JPanel
//import javax.swing.SwingConstants
//
//class PSIcon(space: Int, icon: ImageIcon, title: String,panelWidth :Int) : JPanel(null) {
//    val iconLabel = PSLabel()
//    val textLabel = PSLabel()
//    init {
//        layout = null
//       iconLabel.apply {
//           horizontalTextPosition=SwingConstants.BOTTOM
//            setBounds(0, 0, panelWidth, icon.iconHeight)
//            horizontalAlignment = SwingConstants.CENTER
//            verticalAlignment = SwingConstants.CENTER
//            setIcon(icon)
//        }
//        add(iconLabel)
//
//        textLabel.apply {
//            text = title
//            setBounds(0, icon.iconHeight + space, panelWidth, 30)
//        }
//        add(textLabel)
//    }
//}
class PSIcon( icon: ImageIcon,text: String,) : JLabel(text, icon, SwingConstants.CENTER) {
    init {
        horizontalTextPosition = SwingConstants.CENTER
        verticalTextPosition = SwingConstants.BOTTOM
    }
}
