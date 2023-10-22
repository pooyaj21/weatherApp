package ui.loading

import core.ApiWeatherData
import ui.EventListener
import ui.util.resizeIcon
import java.awt.Color
import java.awt.Font
import javax.swing.ImageIcon
import javax.swing.JButton
import javax.swing.JLabel
import javax.swing.JPanel

class LoadingPanelView(response: ApiWeatherData, eventListener: EventListener) : JPanel() {
    private val loadingPanelController = LoadingPanelController(response)
    private val backgroundColor = if (loadingPanelController.getDayOrNight() == "d") Color(0xE5ECF4)
    else Color(0x1E1E1E)

    private val foregroundColor = if (loadingPanelController.getDayOrNight() == "d") Color(0x1E1E1E)
    else Color(0xE5ECF4)

    init {
        layout = null
        isVisible = true
        background = backgroundColor

        val weatherText = when (loadingPanelController.getMainName()) {
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


        val imageLabel = JLabel(
            resizeIcon(
                icon = ImageIcon("assets/${loadingPanelController.getIcon()}.png"),
                width = 100,
                height = 100
            )
        )
        imageLabel.setBounds(25, 35, 100, 100)
        add(imageLabel)

        // First Line
        JLabel("It’s").apply {
            foreground = foregroundColor
            setBounds(25, 340, 240, 45)
            font = Font(null, Font.BOLD, 36)
        }.also {
            add(it)
        }

        // Second Line
        JLabel("fucking").apply {
            foreground = foregroundColor
            setBounds(25, 385, 240, 45)
            font = Font(null, Font.BOLD, 36)
        }.also {
            add(it)
        }

        // Weather Status
        JButton(weatherText).apply {
            when (weatherText) {
                "Sunny" -> foreground = Color(0xd8eb34)
                "Cloudy" -> foreground = Color(0x88d2f7)
                "Rainy" -> foreground = Color(0x34afed)
                "Snow" -> foreground = Color(0xcfecfa)
                "Mist" -> foreground = Color(0xd3dce0)
                "smokey" -> foreground = Color(0x9ea2a3)
                "Dusty" -> foreground = Color(0xb09f96)
                "Tornado" -> foreground = Color(0x434a5c)
            }
            setBounds(5, 430, 240, 45)
            font = Font(null, Font.BOLD, 36)
            addActionListener {
                this@LoadingPanelView.isVisible = false
                eventListener.nextPage(response)
            }
            isOpaque = false
            isBorderPainted = false
            isContentAreaFilled = false
            horizontalAlignment = JLabel.LEFT
        }.also {
            add(it)
        }
        // Last Line
        JLabel("now.").apply {
            foreground = foregroundColor
            setBounds(25, 475, 240, 45)
            font = Font(null, Font.BOLD, 36)
        }.also {
            add(it)
        }

        // Bottom Line
        JLabel("you can look outside to get more information").apply {
            foreground = foregroundColor
            setBounds(25, 570, 360, 45)
            font = Font(null, Font.BOLD, 12)
            horizontalAlignment = JLabel.LEFT
            verticalAlignment = JLabel.CENTER
        }.also {
            add(it)
        }

        // Back Icon
        JButton(
            resizeIcon(
                icon = ImageIcon("assets/back${loadingPanelController.getDayOrNight()}.png"),
                width = 30,
                height = 30
            )
        ).apply {
            isOpaque = false
            isBorderPainted = false
            isContentAreaFilled = false
            setBounds(0, 0, 50, 50)
            addActionListener { eventListener.previousPage() }
        }.also {
            add(it)
        }

    }
}