package ui.mainPage


import core.ApiWeatherData
import ui.EventListener
import ui.util.resizeIcon
import java.awt.*
import javax.swing.ImageIcon
import javax.swing.JButton
import javax.swing.JLabel
import javax.swing.JPanel

class MainPageView(response: ApiWeatherData, eventListener: EventListener) : JPanel() {
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

        // Country's Name
        JLabel(mainPageController.getCountry()).apply {
            foreground = foregroundColor
            setBounds(0, 90, 360, 50)
            font = Font(null, Font.BOLD, 36)
            horizontalAlignment = JLabel.CENTER
            verticalAlignment = JLabel.CENTER
        }.also {
            add(it)
        }

        // Date
        JLabel("${mainPageController.getDay()}/${mainPageController.getTime()}").apply {
            foreground = foregroundColor
            setBounds(0, 140, 360, 30)
            font = Font(null, Font.BOLD, 20)
            horizontalAlignment = JLabel.CENTER
            verticalAlignment = JLabel.CENTER
        }.also {
            add(it)
        }

        val imageLabel = JLabel(resizeIcon(
            icon = ImageIcon("assets/${mainPageController.getIcon()}.png"),
            width = 100,
            height = 100
        ))
        imageLabel.setBounds(130, 190, 100, 100)
        add(imageLabel)

        //FeelingTemp
        JLabel("${mainPageController.getFellingTemp()}°C").apply {
            foreground = foregroundColor
            setBounds(0, 280, 360, 50)
            font = Font(null, Font.BOLD, 40)
            horizontalAlignment = JLabel.CENTER
            verticalAlignment = JLabel.CENTER
        }.also {
            add(it)
        }

        //Description
        JButton(mainPageController.getDescription()).apply {
            foreground = foregroundColor
            setBounds(0, 340, 360, 30)
            font = Font(null, Font.BOLD, 20)
            horizontalAlignment = JLabel.CENTER
            verticalAlignment = JLabel.CENTER
            isOpaque = false
            isBorderPainted = false
            isContentAreaFilled = false
            addActionListener {
                this@MainPageView.isVisible = false
                eventListener.nextPage(response)
            }
        }.also {
            add(it)
        }


        val sunStatusIconLabel = JLabel(resizeIcon(
            icon = ImageIcon("assets/sunStatus${mainPageController.getDayOrNight()}.png"),
            width = 50,
            height = 50
        ))
        sunStatusIconLabel.setBounds(37, 450, 50, 50)
        add(sunStatusIconLabel)

        val sunStatus = if (mainPageController.getDayOrNight() == "d") mainPageController.getSunSet()
        else mainPageController.getSunRise()

        //SunStatusLabel
        JLabel(sunStatus).apply {
            foreground = foregroundColor
            setBounds(0, 490, 120, 50)
            font = Font(null, Font.PLAIN, 16)
            horizontalAlignment = JLabel.CENTER
            verticalAlignment = JLabel.CENTER
        }.also {
            add(it)
        }

        val windIconLabel = JLabel(resizeIcon(
            icon = ImageIcon("assets/windIcon${mainPageController.getDayOrNight()}.png"),
            width = 50,
            height = 50
        ))
        windIconLabel.setBounds(154, 450, 50, 50)
        add(windIconLabel)

        //WindStatus
        JLabel("${mainPageController.getWind()}").apply {
            foreground = foregroundColor
            setBounds(120, 490, 120, 50)
            font = Font(null, Font.PLAIN, 16)
            horizontalAlignment = JLabel.CENTER
            verticalAlignment = JLabel.CENTER
        }.also {
            add(it)
        }

        val tempIconLabel = JLabel(resizeIcon(
            icon = ImageIcon("assets/tempIcon${mainPageController.getDayOrNight()}.png"),
            width = 50,
            height = 50
        ))
        tempIconLabel.setBounds(273, 450, 50, 50)
        add(tempIconLabel)

        //Temp
         JLabel("${mainPageController.getTemp()}").apply {
            foreground = foregroundColor
            setBounds(240, 490, 120, 50)
            font = Font(null, Font.PLAIN, 16)
            horizontalAlignment = JLabel.CENTER
            verticalAlignment = JLabel.CENTER
        }.also {
            add(it)
        }

        val backButton = JButton(resizeIcon(
            icon = ImageIcon("assets/back${mainPageController.getDayOrNight()}.png"),
            width = 30,
            height = 30
        ))
        backButton.apply {
            isOpaque = false
            isBorderPainted = false
            isContentAreaFilled = false
            setBounds(0, 0, 50, 50)
            addActionListener { eventListener.previousPage() }
        }.also {
            add(it)
        }

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