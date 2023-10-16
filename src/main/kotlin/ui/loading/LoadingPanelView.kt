package ui.loading

import kotlinx.coroutines.runBlocking
import ui.startedPanel.EventListener
import ui.util.resizeIcon
import java.awt.Color
import java.awt.Font
import java.awt.event.ActionListener
import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import javax.swing.ImageIcon
import javax.swing.JButton
import javax.swing.JLabel
import javax.swing.JPanel

class LoadingPanelView(loadingPanelController: LoadingPanelController, eventListener: EventListener) : JPanel() {
    private val backgroundColor = if (loadingPanelController.getDayOrNight() == "d") Color(0xE5ECF4)
    else Color(0x1E1E1E)

    private val foregroundColor = if (loadingPanelController.getDayOrNight() == "d") Color(0x1E1E1E)
    else Color(0xE5ECF4)

    init {
        val weatherType = loadingPanelController.getWeatherApi().weathers[0]
        layout = null
        isVisible = true
        background = backgroundColor

        val weatherText = when (weatherType.main) {
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


        val icon = ImageIcon("assets/${weatherType.icon}.png")
        val imageLabel = JLabel(resizeIcon(icon, 100, 100))
        imageLabel.setBounds(25, 35, 100, 100)
        add(imageLabel)

        val firstLine = JLabel("It’s")
        firstLine.foreground = foregroundColor
        firstLine.setBounds(25, 340, 240, 45)
        firstLine.font = Font(null, Font.BOLD, 36)
        add(firstLine)

        val secondLine = JLabel("fucking")
        secondLine.foreground = foregroundColor
        secondLine.setBounds(25, 385, 240, 45)
        secondLine.font = Font(null, Font.BOLD, 36)
        add(secondLine)

        val weatherLabel = JButton(weatherText)

        when (weatherText) {
            "Sunny" -> weatherLabel.foreground = Color(0xd8eb34)
            "Cloudy" -> weatherLabel.foreground = Color(0x88d2f7)
            "Rainy" -> weatherLabel.foreground = Color(0x34afed)
            "Snow" -> weatherLabel.foreground = Color(0xcfecfa)
            "Mist" -> weatherLabel.foreground = Color(0xd3dce0)
            "smokey" -> weatherLabel.foreground = Color(0x9ea2a3)
            "Dusty" -> weatherLabel.foreground = Color(0xb09f96)
            "Tornado" -> weatherLabel.foreground = Color(0x434a5c)
        }

        weatherLabel.setBounds(5, 430, 240, 45)
        weatherLabel.font = Font(null, Font.BOLD, 36)
        weatherLabel.addActionListener(ActionListener {
            this@LoadingPanelView.isVisible = false
            eventListener.nextPage()
        })
        weatherLabel.isOpaque=false
        weatherLabel.isBorderPainted=false
        weatherLabel.isContentAreaFilled=false
        weatherLabel.horizontalAlignment = JLabel.LEFT
        add(weatherLabel)

        val lastLine = JLabel("now.")
        lastLine.foreground = foregroundColor
        lastLine.setBounds(25, 475, 240, 45)
        lastLine.font = Font(null, Font.BOLD, 36)
        add(lastLine)


        val bottomLabel = JLabel("you can look outside to get more information")
        bottomLabel.foreground = foregroundColor
        bottomLabel.setBounds(25, 570, 360, 45)
        bottomLabel.font = Font(null, Font.BOLD, 12)
        bottomLabel.horizontalAlignment = JLabel.LEFT
        bottomLabel.verticalAlignment = JLabel.CENTER
        add(bottomLabel)
    }
}