package ui.mainPage


import ui.startedPanel.EventListener
import ui.util.resizeIcon
import java.awt.*
import java.awt.event.ActionListener
import javax.swing.ImageIcon
import javax.swing.JButton
import javax.swing.JLabel
import javax.swing.JPanel

class MainPageView(mainPageController: MainPageController, eventListener: EventListener) : JPanel() {
    private val backgroundColor = if (mainPageController.getDayOrNight() == "d") Color(0xE5ECF4)
    else Color(0x1E1E1E)

    private val foregroundColor = if (mainPageController.getDayOrNight() == "d") Color(0x1E1E1E)
    else Color(0xE5ECF4)

    init {
        layout = null
        isVisible = true
        background = backgroundColor
        setBounds(0, 0, width, height)

        val country = JLabel(mainPageController.getCountry())
        country.foreground = foregroundColor
        country.setBounds(0, 90, 360, 50)
        country.font = Font(null, Font.BOLD, 36)
        country.horizontalAlignment = JLabel.CENTER
        country.verticalAlignment = JLabel.CENTER
        add(country)

        val date = JLabel("${mainPageController.getDay()}/${mainPageController.getTime()}")
        date.foreground = foregroundColor
        date.setBounds(0, 140, 360, 30)
        date.font = Font(null, Font.BOLD, 20)
        date.horizontalAlignment = JLabel.CENTER
        date.verticalAlignment = JLabel.CENTER
        add(date)

        val icon = ImageIcon("assets/${mainPageController.getIcon()}.png")
        val imageLabel = JLabel(icon)
        imageLabel.setBounds(130, 190, 100, 100)
        add(imageLabel)

        val feelingTemp = JLabel("${mainPageController.getFellingTemp()}°C")
        feelingTemp.foreground = foregroundColor
        feelingTemp.setBounds(0, 280, 360, 50)
        feelingTemp.font = Font(null, Font.BOLD, 40)
        feelingTemp.horizontalAlignment = JLabel.CENTER
        feelingTemp.verticalAlignment = JLabel.CENTER
        add(feelingTemp)

        val description = JButton(mainPageController.getDescription())
        description.foreground = foregroundColor
        description.setBounds(0, 340, 360, 30)
        description.font = Font(null, Font.BOLD, 20)
        description.horizontalAlignment = JLabel.CENTER
        description.verticalAlignment = JLabel.CENTER
        description.isOpaque = false
        description.isBorderPainted = false
        description.isContentAreaFilled = false
        description.addActionListener(ActionListener {
            this@MainPageView.isVisible = false
            eventListener.nextPage()
        })
        add(description)


        val sunStatusIcon = ImageIcon("assets/sunStatus${mainPageController.getDayOrNight()}.png")
        val sunStatusIconLabel = JLabel(resizeIcon(sunStatusIcon, 50, 50))
        sunStatusIconLabel.setBounds(37, 450, 50, 50)
        add(sunStatusIconLabel)

        val sunStatus = if (mainPageController.getDayOrNight() == "d") mainPageController.getSunSet()
        else mainPageController.getSunRise()

        val sunStatusLabel = JLabel(sunStatus)
        sunStatusLabel.foreground = foregroundColor
        sunStatusLabel.setBounds(0, 490, 120, 50)
        sunStatusLabel.font = Font(null, Font.PLAIN, 16)
        sunStatusLabel.horizontalAlignment = JLabel.CENTER
        sunStatusLabel.verticalAlignment = JLabel.CENTER
        add(sunStatusLabel)

        val windIcon = ImageIcon("assets/windIcon${mainPageController.getDayOrNight()}.png")
        val windIconLabel = JLabel(resizeIcon(windIcon, 50, 50))
        windIconLabel.setBounds(154, 450, 50, 50)
        add(windIconLabel)

        val windStatus = JLabel("${mainPageController.getWind()}")
        windStatus.foreground = foregroundColor
        windStatus.setBounds(120, 490, 120, 50)
        windStatus.font = Font(null, Font.PLAIN, 16)
        windStatus.horizontalAlignment = JLabel.CENTER
        windStatus.verticalAlignment = JLabel.CENTER
        add(windStatus)

        val tempIcon = ImageIcon("assets/tempIcon${mainPageController.getDayOrNight()}.png")
        val tempIconLabel = JLabel(resizeIcon(tempIcon, 50, 50))
        tempIconLabel.setBounds(273, 450, 50, 50)
        add(tempIconLabel)

        val temp = JLabel("${mainPageController.getTemp()}")
        temp.foreground = foregroundColor
        temp.setBounds(240, 490, 120, 50)
        temp.font = Font(null, Font.PLAIN, 16)
        temp.horizontalAlignment = JLabel.CENTER
        temp.verticalAlignment = JLabel.CENTER
        add(temp)
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