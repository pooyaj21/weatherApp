package ui

import ui.util.RoundedButton
import java.awt.Color
import java.awt.Font
import java.awt.event.ActionListener
import java.awt.event.KeyEvent
import java.awt.event.KeyListener
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
        val errorButton = RoundedButton(
            "Try Again",
            14,
            Color(0x1E1E1E),
            Color(0xE5ECF4),
            20
        )

        init {
            layout = null
            isVisible = false
            background = Color(0xE5ECF4)
            setBounds(0, 0, 370, 640)

            errorMassage.font = Font(null, Font.PLAIN, 12)
            errorMassage.horizontalAlignment = JLabel.CENTER
            errorMassage.verticalAlignment = JLabel.CENTER
            errorMassage.setBounds(0, 240, 370, 50)

            errorButton.setBounds(135, 280, 100, 50)
            errorButton.horizontalAlignment = JLabel.CENTER
            errorButton.verticalAlignment = JLabel.CENTER

            add(errorButton)
            add(errorMassage)

            addKeyListener(object : KeyListener {
                override fun keyTyped(e: KeyEvent?) {}

                override fun keyPressed(e: KeyEvent?) {
                    if (e?.keyCode == KeyEvent.VK_ENTER) {
                        errorButton.doClick()
                    }
                }

                override fun keyReleased(e: KeyEvent?) {}
            })
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

    data class ButtonInfo(val title: String?, val action: ActionListener?)

    fun onError(message: String, buttonInfo: ButtonInfo?) {
        errorPanel.errorMassage.text = message

        errorPanel.errorButton.isVisible = buttonInfo != null
        errorPanel.errorButton.text = buttonInfo?.title

        buttonInfo?.action?.let { errorPanel.errorButton.addActionListener(it) }

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