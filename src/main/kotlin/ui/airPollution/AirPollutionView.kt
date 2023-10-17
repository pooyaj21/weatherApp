package ui.airPollution

import core.ApiManager
import core.ApiPollutionData
import core.ApiWeatherData
import domain.GetWeatherPollutionUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import ui.UiState
import ui.util.resizeIcon
import java.awt.Color
import java.awt.Font
import javax.swing.ImageIcon
import javax.swing.JLabel
import javax.swing.JPanel

class AirPollutionView(response: ApiWeatherData) : JPanel() {
    private val airPollutionController = AirPollutionController(
        CoroutineScope(Dispatchers.IO),
        GetWeatherPollutionUseCase(ApiManager)
    )
    private val backgroundColor = if (response.weathers[0].icon.last().toString() == "d") Color(0xE5ECF4)
    else Color(0x1E1E1E)

    private val foregroundColor = if (response.weathers[0].icon.last().toString() == "d") Color(0x1E1E1E)
    else Color(0xE5ECF4)

    private lateinit var airPollutionData: ApiPollutionData

    init {
        layout = null
        isVisible = true
        background = backgroundColor

        airPollutionController.pollution(response)
        airPollutionController.callBack = {
            when (it) {
                is UiState.Loading -> {}
                is UiState.Data -> {
                    airPollutionData = it.model
                    val pollutionIcon = ImageIcon("assets/${response.weathers[0].icon}.png")
                    val pollutionIconLabel = JLabel(resizeIcon(pollutionIcon, 100, 100))
                    pollutionIconLabel.setBounds(130, 90, 100, 100)
                    add(pollutionIconLabel)

                    val description = JLabel(response.weathers[0].description)
                    description.foreground = foregroundColor
                    description.setBounds(0, 200, 360, 30)
                    description.font = Font(null, Font.PLAIN, 20)
                    description.horizontalAlignment = JLabel.CENTER
                    description.verticalAlignment = JLabel.CENTER
                    add(description)

                    val coStatus = JLabel("CO:${airPollutionData.list[0].components.co}")
                    coStatus.foreground = foregroundColor
                    coStatus.setBounds(0, 350, 180, 30)
                    coStatus.font = Font(null, Font.PLAIN, 20)
                    coStatus.horizontalAlignment = JLabel.CENTER
                    coStatus.verticalAlignment = JLabel.CENTER
                    add(coStatus)

                    val no2Status = JLabel("NO2:${airPollutionData.list[0].components.no2}")
                    no2Status.foreground = foregroundColor
                    no2Status.setBounds(180, 350, 180, 30)
                    no2Status.font = Font(null, Font.PLAIN, 20)
                    no2Status.horizontalAlignment = JLabel.CENTER
                    no2Status.verticalAlignment = JLabel.CENTER
                    add(no2Status)

                    val noStatus = JLabel("NO:${airPollutionData.list[0].components.no}")
                    noStatus.foreground = foregroundColor
                    noStatus.setBounds(0, 400, 180, 30)
                    noStatus.font = Font(null, Font.PLAIN, 20)
                    noStatus.horizontalAlignment = JLabel.CENTER
                    noStatus.verticalAlignment = JLabel.CENTER
                    add(noStatus)

                    val o3Status = JLabel("O3:${airPollutionData.list[0].components.o3}")
                    o3Status.foreground = foregroundColor
                    o3Status.setBounds(180, 400, 180, 30)
                    o3Status.font = Font(null, Font.PLAIN, 20)
                    o3Status.horizontalAlignment = JLabel.CENTER
                    o3Status.verticalAlignment = JLabel.CENTER
                    add(o3Status)

                    repaint()
                    revalidate()
                }

                is UiState.Error -> println("Error")
            }
        }
    }

}