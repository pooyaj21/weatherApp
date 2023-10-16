package ui.mainPage

import ui.startedPanel.EventListener
import ui.util.resizeIcon
import java.awt.*
import javax.swing.ImageIcon
import javax.swing.JLabel
import javax.swing.JPanel

class MainPageView (mainPageController: MainPageController):JPanel(){
    private val backgroundColor =if (mainPageController.getDayOrNight()=="d")Color(0xE5ECF4)
    else Color(0x1E1E1E)

    private val foregroundColor =if (mainPageController.getDayOrNight()=="d")Color(0x1E1E1E)
    else Color(0xE5ECF4)
    init {
        layout = null
        isVisible = true
        background = backgroundColor
        setBounds(0, 0, width, height)

        val country = JLabel(mainPageController.getCountry())
        country.foreground = foregroundColor
        country.setBounds(110, 90, 240, 50)
        country.font = Font(null, Font.BOLD, 36)
        add(country)

        val date = JLabel( "${mainPageController.getDay()}/${mainPageController.getTime()}" )
        date.foreground = foregroundColor
        date.setBounds(100, 140, 240, 30)
        date.font = Font(null, Font.BOLD, 20)
        add(date)

        val icon = ImageIcon("assets/${mainPageController.getIcon()}.png")
        val imageLabel = JLabel(resizeIcon(icon, 90, 90))
        imageLabel.setBounds(130, 190, 90, 90)
        add(imageLabel)

        val feelingTemp = JLabel("${mainPageController.getFellingTemp()}°C")
        feelingTemp.foreground = foregroundColor
        feelingTemp.setBounds(130, 280, 240, 50)
        feelingTemp.font = Font(null, Font.BOLD, 40)
        add(feelingTemp)

        val description = JLabel(mainPageController.getDescription())
        description.foreground = foregroundColor
        description.setBounds(110, 340, 240, 30)
        description.font = Font(null, Font.BOLD, 20)
        add(description)


        val sunStatusIcon = ImageIcon("assets/sunStatus${mainPageController.getDayOrNight()}.png")
        val sunStatusIconLabel = JLabel(resizeIcon(sunStatusIcon, 50, 50))
        sunStatusIconLabel.setBounds(40, 450, 50, 50)
        add(sunStatusIconLabel)

        val sunStatus = if(mainPageController.getDayOrNight()=="d")mainPageController.getSunSet()
        else mainPageController.getSunRise()

        val sunStatusLabel = JLabel(sunStatus)
        sunStatusLabel.foreground = foregroundColor
        sunStatusLabel.setBounds(40, 490, 60, 50)
        sunStatusLabel.font = Font(null, Font.PLAIN, 16)
        add(sunStatusLabel)

        val windIcon = ImageIcon("assets/windIcon${mainPageController.getDayOrNight()}.png")
        val windIconLabel = JLabel(resizeIcon(windIcon, 50, 50))
        windIconLabel.setBounds(160, 450, 50, 50)
        add(windIconLabel)

        val windStatus = JLabel("${mainPageController.getWind()}")
        windStatus.foreground = foregroundColor
        windStatus.setBounds(165, 490, 40, 50)
        windStatus.font = Font(null, Font.PLAIN, 16)
        add(windStatus)

        val tempIcon = ImageIcon("assets/tempIcon${mainPageController.getDayOrNight()}.png")
        val tempIconLabel = JLabel(resizeIcon(tempIcon, 50, 50))
        tempIconLabel.setBounds(280, 450, 50, 50)
        add(tempIconLabel)

        val temp = JLabel("${mainPageController.getTemp()}")
        temp.foreground = foregroundColor
        temp.setBounds(285, 490, 40, 50)
        temp.font = Font(null, Font.PLAIN, 16)
        add(temp)
    }
    override fun paintComponent(g: Graphics) {
        super.paintComponent(g)

        val g2d = g as Graphics2D

        g2d.stroke = BasicStroke(2F)
        g2d.color = foregroundColor
        g2d.drawLine(125, 450, 125, 500)
        g2d.drawLine(245, 450, 245, 500)

    }
}