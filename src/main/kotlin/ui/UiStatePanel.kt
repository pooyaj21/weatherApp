package ui

import java.awt.Color
import java.awt.Font
import javax.swing.ImageIcon
import javax.swing.JLabel
import javax.swing.JPanel


abstract class UiStatePanel : JPanel() {
    private val loadingPanel = object : JPanel() {
        init {
            layout = null
            isVisible = false
            background = Color(0xE5ECF4)
            setBounds(0, 0, 370, 640)
            val loadingIcon = ImageIcon("assets/IMG/loading.gif")
            val loadingLabel = JLabel(loadingIcon)
            loadingLabel.setBounds(130, 260, 100, 100)
            add(loadingLabel)
            this@UiStatePanel.add(this)
            this@UiStatePanel.setComponentZOrder(this, 0)
        }
    }
    private val errorPanel = object : JPanel() {
        val errorMassage = JLabel()


        init {
            layout = null
            isVisible = false
            background = Color(0xE5ECF4)
            setBounds(0, 0, 370, 640)

            errorMassage.font = Font(null, Font.PLAIN, 12)
            errorMassage.horizontalAlignment = JLabel.CENTER
            errorMassage.verticalAlignment = JLabel.CENTER
            errorMassage.setBounds(0, 240, 370, 50)


            add(errorMassage)


            isFocusable = true
            this@UiStatePanel.add(this)
            this@UiStatePanel.setComponentZOrder(this, 0)
        }
    }
    private val dataPanel by lazy { createDataPanel() }
    fun onLoading(bacGroundColor: Color) {
        loadingPanel.background = bacGroundColor
        errorPanel.isVisible = false
        loadingPanel.isVisible = true
    }


    fun onError(message: String) {
        errorPanel.errorMassage.text = message
        loadingPanel.isVisible = false
        errorPanel.isVisible = true
    }


    fun onData() {
        loadingPanel.isVisible = false
        errorPanel.isVisible = false
        dataPanel.isVisible = true
    }

    abstract fun createDataPanel(): JPanel
}