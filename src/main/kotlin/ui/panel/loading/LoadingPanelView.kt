package ui.panel.loading

import model.Weather
import ui.Navigator
import ui.panel.mainPage.MainPageView
import ui.util.FontEnum
import ui.util.resizeIcon
import ui.util.setFont
import java.awt.Color
import javax.swing.ImageIcon
import javax.swing.JButton
import javax.swing.JLabel
import javax.swing.JPanel


class LoadingPanelView(private val response: Weather, private val navigator: Navigator) : JPanel() {
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
                icon = ImageIcon("assets/IMG/${loadingPanelController.getIcon()}.png"),
                width = 100,
                height = 100
            )
        )
        imageLabel.setBounds(25, 35, 100, 100)
        add(imageLabel)

        val firstLine = JLabel("It’s").apply {
            foreground = foregroundColor
            setBounds(25, 340, 240, 45)
            setFont(FontEnum.BOLD, 36)
        }
        add(firstLine)


        val secondLine = JLabel("fucking").apply {
            foreground = foregroundColor
            setBounds(25, 385, 240, 45)
            setFont(FontEnum.BOLD, 36)
        }
        add(secondLine)


        val weatherLabel = JButton(weatherText).apply {
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
            setFont(FontEnum.BOLD, 36)
            addActionListener {
                val mainPage = MainPageView(response, navigator).apply {
                    setBounds(0, 0, width, height)
                }
                navigator.push(mainPage)
            }
            isOpaque = false
            isBorderPainted = false
            isContentAreaFilled = false
            horizontalAlignment = JLabel.LEFT
        }
        add(weatherLabel)


        val lastLine = JLabel("now.").apply {
            foreground = foregroundColor
            setBounds(25, 475, 240, 45)
            setFont(FontEnum.BOLD, 36)
        }
        add(lastLine)


        val bottomLabel = JLabel("you can look outside to get more information").apply {
            foreground = foregroundColor
            setBounds(25, 570, 360, 45)
            setFont(FontEnum.SEMI_BOLD, 12)
            horizontalAlignment = JLabel.LEFT
            verticalAlignment = JLabel.CENTER
        }
        add(bottomLabel)


        val backButton = JButton(
            resizeIcon(
                icon = ImageIcon("assets/IMG/back${loadingPanelController.getDayOrNight()}.png"),
                width = 30,
                height = 30
            )
        ).apply {
            isOpaque = false
            isBorderPainted = false
            isContentAreaFilled = false
            setBounds(0, 0, 50, 50)
            addActionListener { navigator.pop() }
        }
        add(backButton)


    }
}