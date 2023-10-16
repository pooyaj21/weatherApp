package ui.loading

import kotlinx.coroutines.runBlocking
import ui.startedPanel.EventListener
import ui.util.resizeIcon
import java.awt.Color
import java.awt.Font
import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import javax.swing.ImageIcon
import javax.swing.JLabel
import javax.swing.JPanel

class LoadingPanelView(loadingPanelController: LoadingPanelController,eventListener: EventListener) : JPanel() {
    init {
        val whetherType = loadingPanelController.getWeatherApi().weathers.get(0)
        layout = null
        isVisible = true
        background = Color(0xE5ECF4)

        val weatherText = when (whetherType.main) {
            "Clear" -> "Sunny"
            "Clouds" -> "Cloudy"
            "Rain" -> "Rainy"
            "Drizzle" -> "Rainy"
            "Thunderstorm" -> "Rainy"
            "Snow" -> "Snow"
            "Mist" -> "Mist"
            "Smoke" -> "smokey"
            "Haze" -> "Mist"
            "Dust" -> "Dusty"
            "Fog" -> "Mist"
            "Sand" -> "Mist"
            "Ash" -> "Mist"
            "Squall" -> "Mist"
            "Tornado" -> "Tornado"
            else -> {
                "Error"
            }
        }


        val icon = ImageIcon("assets/${whetherType.icon}.png")
        val imageLabel = JLabel(resizeIcon(icon, 100, 100))
        imageLabel.setBounds(25, 35, 100, 100)
        add(imageLabel)

        val firstLine = JLabel("It’s")
        firstLine.foreground = Color(0x1E1E1E)
        firstLine.setBounds(25, 340, 240, 45)
        firstLine.font = Font(null, Font.BOLD, 36)
        add(firstLine)

        val secondLine = JLabel("fucking")
        secondLine.foreground = Color(0x1E1E1E)
        secondLine.setBounds(25, 385, 240, 45)
        secondLine.font = Font(null, Font.BOLD, 36)
        add(secondLine)

        val weatherLabel = JLabel(weatherText)

            when (weatherText) {
                "Sunny" -> weatherLabel.foreground = Color(0xd8eb34)
                "Cloudy" -> weatherLabel.foreground = Color(0x88d2f7)
                "Rainy" -> weatherLabel.foreground = Color(0x34afed)
                "Snow" -> weatherLabel.foreground = Color(0xcfecfa)
                "Mist"->weatherLabel.foreground = Color(0xd3dce0)
                "smokey" -> weatherLabel.foreground = Color(0x9ea2a3)
                "Dusty"-> weatherLabel.foreground = Color(0xb09f96)
                "Tornado"->weatherLabel.foreground = Color(0x434a5c)
            }

        weatherLabel.setBounds(25, 430, 240, 45)
        weatherLabel.font = Font(null, Font.BOLD, 36)
        add(weatherLabel)

        val lastLine = JLabel("now.")
        lastLine.foreground = Color(0x1E1E1E)
        lastLine.setBounds(25, 475, 240, 45)
        lastLine.font = Font(null, Font.BOLD, 36)
        add(lastLine)


        val bottomLabel = JLabel("you can look outside to get more information")
        bottomLabel.foreground = Color(0x1E1E1E)
        bottomLabel.setBounds(25, 575, 360, 45)
        bottomLabel.font = Font(null, Font.BOLD, 12)
        add(bottomLabel)

        isFocusable = true
        addKeyListener(object : KeyListener {
            override fun keyTyped(e: KeyEvent?) {}

            override fun keyPressed(e: KeyEvent?) {
                if (e?.keyCode == KeyEvent.VK_ENTER) {
                    this@LoadingPanelView.isVisible = false
                    eventListener.nextPage()
                }
            }

            override fun keyReleased(e: KeyEvent?) {}
        })
    }
}