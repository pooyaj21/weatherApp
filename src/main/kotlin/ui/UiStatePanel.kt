package ui

import ui.util.resizeIcon
import java.awt.Color
import javax.swing.ImageIcon
import javax.swing.JLabel
import javax.swing.JPanel

abstract class UiStatePanel : JPanel() {
    fun onLoading(): JPanel {
        val loadingPanel = JPanel()
        loadingPanel.layout=null
        loadingPanel.isVisible = true
        loadingPanel.background = Color(0xE5ECF4)
        val loadingIcon = ImageIcon("assets/loading.gif")
        val loadingLabel = JLabel(loadingIcon)
        loadingLabel.setBounds(130,260,100,100)
        loadingPanel.add(loadingLabel)
        return loadingPanel
    }

    fun onError(text: String, buttonText: String) {

    }

    abstract fun createDataPanel()
}