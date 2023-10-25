package ui.panel.homepanel

import model.Weather
import ui.Navigator
import ui.component.PSButton
import ui.component.PSLabel
import ui.panel.mainPanel.MainPanelView
import ui.extension.*
import java.awt.Color
import javax.swing.ImageIcon
import javax.swing.JLabel
import javax.swing.JPanel


class LoadingPanelView(private val response: Weather, private val navigator: Navigator) : JPanel() {
    private val backgroundColor = if (response.icon.last() == 'd') Color(0xE5ECF4)
    else Color(0x1E1E1E)

    private val foregroundColor = if (response.icon.last() == 'd') Color(0x1E1E1E)
    else Color(0xE5ECF4)

    init {
        layout = null
        isVisible = true
        background = backgroundColor

        val weatherText = when (response.status.short) {
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


        val imageLabel = PSLabel().apply {
            icon = ImageIcon("assets/IMG/${response.icon}.png").resizeIcon(100, 100)
            setBounds(25, 35, 100, 100)
        }
        add(imageLabel)

        val firstLine = PSLabel().apply {
            setFont(FontEnum.BOLD, 36)
            text = "It’s"
            foreground = foregroundColor
            horizontalAlignment = JLabel.LEFT
            setBounds(25, 340, 240, 45)
        }
        add(firstLine)


        val secondLine = PSLabel().apply {
            setFont(FontEnum.BOLD, 36)
            text = "fucking"
            foreground = foregroundColor
            horizontalAlignment = JLabel.LEFT
            setBounds(25, 385, 240, 45)
        }
        add(secondLine)


        val weatherLabel = PSButton().apply {
            setFont(FontEnum.BOLD, 36)
            text = weatherText
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
            setBounds(0, 430, 240, 45)
            addActionListener {
                val mainPage = MainPanelView(response, navigator).apply {
                    setBounds(0, 0, width, height)
                }
                navigator.push(mainPage)
            }
            horizontalAlignment = JLabel.LEFT
        }
        add(weatherLabel)


        val lastLine = PSLabel().apply {
            setFont(FontEnum.BOLD, 36)
            text = "now."
            horizontalAlignment = JLabel.LEFT
            foreground = foregroundColor
            setBounds(25, 475, 240, 45)
        }
        add(lastLine)


        val bottomLabel = PSLabel().apply {
            setFont(FontEnum.SEMI_BOLD, 12)
            text = "you can look outside to get more information"
            foreground = foregroundColor
            horizontalAlignment = JLabel.LEFT
            setBounds(25, 570, 360, 45)
        }
        add(bottomLabel)


        val backButton = PSButton().apply {
            icon = ImageIcon("assets/IMG/back${response.icon.last()}.png").resizeIcon(30, 30)
            setBounds(0, 0, 50, 50)
            addActionListener { navigator.pop() }
        }
        add(backButton)


    }
}