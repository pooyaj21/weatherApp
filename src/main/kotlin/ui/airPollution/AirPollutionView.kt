package ui.airPollution

import core.ApiManager
import kotlinx.coroutines.runBlocking
import ui.util.resizeIcon
import java.awt.Color
import java.awt.Font
import javax.swing.ImageIcon
import javax.swing.JLabel
import javax.swing.JPanel

class AirPollutionView(airPollutionController: AirPollutionController) : JPanel() {
    private val backgroundColor = if (airPollutionController.getDayOrNight() == "d") Color(0xE5ECF4)
    else Color(0x1E1E1E)

    private val foregroundColor = if (airPollutionController.getDayOrNight() == "d") Color(0x1E1E1E)
    else Color(0xE5ECF4)

    init {
        layout = null
        isVisible = true
        background = backgroundColor
        runBlocking { ApiManager.pollutionApiCreator() }

        val pollutionIcon = ImageIcon("assets/${airPollutionController.getIcon()}.png")
        val pollutionIconLabel = JLabel(resizeIcon(pollutionIcon, 100, 100))
        pollutionIconLabel.setBounds(130, 90, 100, 100)
        add(pollutionIconLabel)

        val description = JLabel(airPollutionController.getDescription())
        description.foreground = foregroundColor
        description.setBounds(70, 200, 360, 30)
        description.font = Font(null, Font.PLAIN, 20)
        add(description)

        val coStatus = JLabel("CO:${airPollutionController.getCo()}")
        coStatus.foreground = foregroundColor
        coStatus.setBounds(50, 350, 240, 30)
        coStatus.font = Font(null, Font.PLAIN, 20)
        add(coStatus)

        val no2Status = JLabel("NO2:${airPollutionController.getNo2()}")
        no2Status.foreground = foregroundColor
        no2Status.setBounds(210, 350, 240, 30)
        no2Status.font = Font(null, Font.PLAIN, 20)
        add(no2Status)

        val noStatus = JLabel("NO:${airPollutionController.getNo()}")
        noStatus.foreground = foregroundColor
        noStatus.setBounds(50, 400, 240, 30)
        noStatus.font = Font(null, Font.PLAIN, 20)
        add(noStatus)

        val o3Status = JLabel("O3:${airPollutionController.getO3()}")
        o3Status.foreground = foregroundColor
        o3Status.setBounds(210, 400, 240, 30)
        o3Status.font = Font(null, Font.PLAIN, 20)
        add(o3Status)

    }

}