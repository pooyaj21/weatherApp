package ui.loading

import ui.util.resizeIcon
import java.awt.Color
import java.awt.Font
import javax.swing.ImageIcon
import javax.swing.JLabel
import javax.swing.JPanel

class LoadingPanelView(loadingPanelController: LoadingPanelController) : JPanel() {
    init {
        val whetherType =loadingPanelController.getWeatherType()
        layout = null
        isVisible = true
        background = Color(0xE5ECF4)

        val weatherText =when(whetherType){
            "clear sky" -> "Sunny"
            "few clouds" -> "Cloudy"
            "scattered clouds" -> "Cloudy"
            "broken clouds" -> "Cloudy"
            "shower rain" -> "Rainy"
            "rain"-> "Rainy"
            "thunderstorm"->"Rainy"
            "snow"->"Snow"
            "mist"->"Mist"
            else -> {"Error"}
        }



        val icon = ImageIcon("assets/$whetherType.png")
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
        when (whetherType) {
            "clear sky" -> weatherLabel.foreground = Color.yellow
            "few clouds" -> weatherLabel.foreground = Color(0xC7EDF3)
            "scattered clouds" -> weatherLabel.foreground = Color(0xbfc0c7)
            "broken clouds" -> weatherLabel.foreground = Color(0xbfc0c7)
            "shower rain" -> weatherLabel.foreground = Color(0x6fa8dc)
            "rain"-> weatherLabel.foreground = Color(0x6fa8dc)
            "thunderstorm"->weatherLabel.foreground = Color(0x18486a)
            "snow"->weatherLabel.foreground = Color(0xc1daf2)
            "mist"->weatherLabel.foreground = Color.WHITE
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
    }
}