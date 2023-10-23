package ui.panel.mainPage


import core.model.Weather
import core.service.response.WeatherResponse
import ui.Navigator
import ui.panel.airPollution.AirPollutionView
import ui.util.FontEnum
import ui.util.resizeIcon
import ui.util.setFont
import java.awt.*
import javax.swing.*


class MainPageView(response: Weather, navigator: Navigator) : JPanel() {
    private val mainPageController = MainPageController(response)
    private val backgroundColor = if (mainPageController.getDayOrNight() == "d") Color(0xE5ECF4)
    else Color(0x1E1E1E)

    private val foregroundColor = if (mainPageController.getDayOrNight() == "d") Color(0x1E1E1E)
    else Color(0xE5ECF4)

    init {
        layout = null
        isVisible = true
        background = backgroundColor
        setBounds(0, 0, width, height)

        val country = JLabel(mainPageController.getCountry()).apply {
            foreground = foregroundColor
            setBounds(0, 90, 360, 50)
            setFont(FontEnum.BOLD, 36)
            horizontalAlignment = JLabel.CENTER
            verticalAlignment = JLabel.CENTER
        }
        add(country)

        val date = JLabel("${mainPageController.getDay()}/${mainPageController.getTime()}").apply {
            foreground = foregroundColor
            setBounds(0, 140, 360, 30)
            setFont(FontEnum.SEMI_BOLD, 20)
            horizontalAlignment = JLabel.CENTER
            verticalAlignment = JLabel.CENTER
        }
        add(date)

        val imageLabel = JLabel(resizeIcon(
            icon = ImageIcon("assets/IMG/${mainPageController.getIcon()}.png"),
            width = 100,
            height = 100
        ))
        imageLabel.setBounds(130, 190, 100, 100)
        add(imageLabel)

        val feelingTemp = JLabel("${mainPageController.getFellingTemp()}°C").apply {
            foreground = foregroundColor
            setBounds(0, 280, 360, 50)
            setFont(FontEnum.BOLD, 40)
            horizontalAlignment = JLabel.CENTER
            verticalAlignment = JLabel.CENTER
        }
        add(feelingTemp)

        val description =JButton(mainPageController.getDescription()).apply {
            foreground = foregroundColor
            setBounds(0, 340, 360, 30)
            setFont(FontEnum.SEMI_BOLD, 20)
            horizontalAlignment = JLabel.CENTER
            verticalAlignment = JLabel.CENTER
            isOpaque = false
            isBorderPainted = false
            isContentAreaFilled = false
            addActionListener {
                val airPollutionPanel = AirPollutionView(response, navigator).apply {
                    setBounds(0, 0, width, height)
                }
                navigator.push(airPollutionPanel)
            }
        }
        add(description)


        val sunStatusIconLabel = JLabel(resizeIcon(
            icon = ImageIcon("assets/IMG/sunStatus${mainPageController.getDayOrNight()}.png"),
            width = 50,
            height = 50
        ))
        sunStatusIconLabel.setBounds(37, 450, 50, 50)
        add(sunStatusIconLabel)

        val sunStatus = if (mainPageController.getDayOrNight() == "d") mainPageController.getSunSet()
        else mainPageController.getSunRise()

        val sunStatusLabel = JLabel(sunStatus).apply {
            foreground = foregroundColor
            setBounds(0, 490, 120, 50)
            setFont(FontEnum.REGULAR, 16)
            horizontalAlignment = JLabel.CENTER
            verticalAlignment = JLabel.CENTER
        }
        add(sunStatusLabel)

        val windIconLabel = JLabel(resizeIcon(
            icon = ImageIcon("assets/IMG/windIcon${mainPageController.getDayOrNight()}.png"),
            width = 50,
            height = 50
        ))
        windIconLabel.setBounds(154, 450, 50, 50)
        add(windIconLabel)

        val windStatus = JLabel("${mainPageController.getWind()}").apply {
            foreground = foregroundColor
            setBounds(120, 490, 120, 50)
            setFont(FontEnum.REGULAR, 16)
            horizontalAlignment = JLabel.CENTER
            verticalAlignment = JLabel.CENTER
        }
        add(windStatus)

        val tempIconLabel = JLabel(resizeIcon(
            icon = ImageIcon("assets/IMG/tempIcon${mainPageController.getDayOrNight()}.png"),
            width = 50,
            height = 50
        ))
        tempIconLabel.setBounds(273, 450, 50, 50)
        add(tempIconLabel)

        val temp = JLabel("${mainPageController.getTemp()}").apply {
            foreground = foregroundColor
            setBounds(240, 490, 120, 50)
            setFont(FontEnum.REGULAR, 16)
            horizontalAlignment = JLabel.CENTER
            verticalAlignment = JLabel.CENTER
        }
        add(temp)

        val backButton = JButton(resizeIcon(
            icon = ImageIcon("assets/IMG/back${mainPageController.getDayOrNight()}.png"),
            width = 30,
            height = 30
        ))
        backButton.apply {
            isOpaque = false
            isBorderPainted = false
            isContentAreaFilled = false
            setBounds(0, 0, 50, 50)
            addActionListener { navigator.pop()}
        }
        add(backButton)

        repaint()
        revalidate()
    }

    override fun paintComponent(g: Graphics) {
        super.paintComponent(g)

        val g2d = g as Graphics2D
        g2d.stroke = BasicStroke(2F)
        g2d.color = foregroundColor
        g2d.drawLine(120, 450, 120, 500)
        g2d.drawLine(240, 450, 240, 500)

    }
}