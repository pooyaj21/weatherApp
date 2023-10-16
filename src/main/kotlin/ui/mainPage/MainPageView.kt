package ui.mainPage

import ui.util.resizeIcon
import java.awt.Color
import java.awt.Font
import javax.swing.ImageIcon
import javax.swing.JLabel
import javax.swing.JPanel

class MainPageView ():JPanel(){
    init {
        layout = null
        isVisible = true
        background = Color(0xE5ECF4)
        setBounds(0, 0, width, height)

        val country = JLabel("Country")
        country.foreground = Color(0x1E1E1E)
        country.setBounds(110, 90, 240, 50)
        country.font = Font(null, Font.BOLD, 36)
        add(country)

        val date = JLabel("Date")
        date.foreground = Color(0x1E1E1E)
        date.setBounds(100, 140, 240, 30)
        date.font = Font(null, Font.BOLD, 20)
        add(date)

        val icon = ImageIcon("assets/01d.png")
        val imageLabel = JLabel(resizeIcon(icon, 90, 90))
        imageLabel.setBounds(130, 190, 90, 90)
        add(imageLabel)

        val temp = JLabel("25 °C")
        temp.foreground = Color(0x1E1E1E)
        temp.setBounds(130, 280, 240, 50)
        temp.font = Font(null, Font.BOLD, 40)
        add(temp)

        val description = JLabel("description")
        description.foreground = Color(0x1E1E1E)
        description.setBounds(130, 340, 240, 30)
        description.font = Font(null, Font.BOLD, 20)
        add(description)


        val sunIcon = ImageIcon("assets/01d.png")
        val sunIconLabel = JLabel(resizeIcon(sunIcon, 50, 50))
        sunIconLabel.setBounds(40, 450, 50, 50)
        add(sunIconLabel)

        val windIcon = ImageIcon("assets/01d.png")
        val windIconLabel = JLabel(resizeIcon(windIcon, 50, 50))
        windIconLabel.setBounds(160, 450, 50, 50)
        add(windIconLabel)

        val tempIcon = ImageIcon("assets/01d.png")
        val tempIconLabel = JLabel(resizeIcon(tempIcon, 50, 50))
        tempIconLabel.setBounds(280, 450, 50, 50)
        add(tempIconLabel)
    }
}