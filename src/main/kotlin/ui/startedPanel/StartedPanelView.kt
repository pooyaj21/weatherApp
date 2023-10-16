package ui.startedPanel

import core.ApiManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import ui.RoundedTextField
import ui.util.*
import java.awt.Color
import java.awt.Font
import java.awt.event.ActionListener
import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import javax.swing.ImageIcon
import javax.swing.JLabel
import javax.swing.JPanel

class StartedPanelView(startedPanelController: StartedPanelController, nextPageLoader: EventListener) : JPanel() {
    init {
        layout = null
        isVisible = true
        background = Color(0xE5ECF4)

        val title = JLabel("SkyCast")
        title.foreground = Color(0x1E1E1E)
        title.setBounds(110, 70, 240, 86)
        title.font = Font(null, Font.BOLD, 36)
        add(title)

        val icon = ImageIcon("assets/SkyCast.png")
        val imageLabel = JLabel(icon)
        imageLabel.setBounds(130, 166, 100, 100)
        add(imageLabel)

        val placeHolder = JLabel("Enter your location:")
        placeHolder.foreground = Color(0x1E1E1E)
        placeHolder.setBounds(60, 300, 240, 30)
        placeHolder.font = Font(null, Font.BOLD, 16)
        add(placeHolder)

        val searchBox = RoundedTextField(23, 25, Color(0xE5ECF4), Color(0x1E1E1E), 16)
        searchBox.setBounds(55, 330, 240, 50)
        searchBox.addKeyListener(object : KeyListener {
            override fun keyTyped(e: KeyEvent?) {}

            override fun keyPressed(e: KeyEvent?) {
                if (e?.keyCode == KeyEvent.VK_ENTER) {
                    runBlocking {
                        startedPanelController.getCity(searchBox.text)
                        this@StartedPanelView.isVisible = false
                        nextPageLoader.nextPage()
                    }
                }
            }

            override fun keyReleased(e: KeyEvent?) {}
        })
        add(searchBox)


    }
}


fun interface EventListener {
    fun nextPage()
}
