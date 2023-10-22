package ui.airPollution

import core.*
import domain.GetWeatherPollutionUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import ui.*
import ui.util.resizeIcon
import java.awt.Color
import java.awt.Font
import javax.swing.*


class AirPollutionView(private val response: ApiWeatherData, private val eventListener: EventListener) :
    UiStatePanel() {
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
        background = Color(0xE5ECF4)

        airPollutionController.callBack = {
            when (it) {
                is UiState.Loading -> {
                    onLoading()
                }

                is UiState.Data -> {
                    airPollutionData = it.model
                    createDataPanel()
                    onData()
                    super.repaint()
                }

                is UiState.Error -> {
                    onError(
                        "An error occurred while processing the request",
                        null
                    )
                    Thread.sleep(5000)
                    eventListener.previousPage()
                }
            }
        }
        airPollutionController.pollution(response)
    }

    override fun createDataPanel(): JPanel {
        val dataPanel = JPanel().apply {
            layout = null
            isVisible = true
            background = backgroundColor
            setBounds(0, 0, 370, 640)
        }

        val pollutionIconLabel = JLabel(
            resizeIcon(
                icon = ImageIcon("assets/IMG/${response.weathers[0].icon}.png"),
                width = 100,
                height = 100
            )
        )
        pollutionIconLabel.setBounds(130, 90, 100, 100)
        dataPanel.add(pollutionIconLabel)

        val description = JLabel(response.weathers[0].description).apply {
            foreground = foregroundColor
            setBounds(0, 200, 360, 30)
            font = Font(null, Font.PLAIN, 20)
            horizontalAlignment = JLabel.CENTER
            verticalAlignment = JLabel.CENTER
        }
        dataPanel.add(description)


        val coStatus = JLabel("CO:${airPollutionData.list[0].components.co}").apply {
            foreground = foregroundColor
            setBounds(0, 350, 180, 30)
            font = Font(null, Font.PLAIN, 20)
            horizontalAlignment = JLabel.CENTER
            verticalAlignment = JLabel.CENTER
        }
        dataPanel.add(coStatus)


        val no2Status = JLabel("NO2:${airPollutionData.list[0].components.no2}").apply {
            foreground = foregroundColor
            setBounds(180, 350, 180, 30)
            font = Font(null, Font.PLAIN, 20)
            horizontalAlignment = JLabel.CENTER
            verticalAlignment = JLabel.CENTER
        }
        dataPanel.add(no2Status)


        val noStatus = JLabel("NO:${airPollutionData.list[0].components.no}").apply {
            foreground = foregroundColor
            setBounds(0, 400, 180, 30)
            font = Font(null, Font.PLAIN, 20)
            horizontalAlignment = JLabel.CENTER
            verticalAlignment = JLabel.CENTER
        }
        dataPanel.add(noStatus)


        val o3Status = JLabel("O3:${airPollutionData.list[0].components.o3}").apply {
            foreground = foregroundColor
            setBounds(180, 400, 180, 30)
            font = Font(null, Font.PLAIN, 20)
            horizontalAlignment = JLabel.CENTER
            verticalAlignment = JLabel.CENTER
        }
        dataPanel.add(o3Status)


        val backButton = JButton(
            resizeIcon(
                icon = ImageIcon("assets/IMG/back${response.weathers[0].icon.last()}.png"),
                width = 30,
                height = 30
            )
        ).apply {
            isOpaque = false
            isBorderPainted = false
            isContentAreaFilled = false
            setBounds(0, 0, 50, 50)
            addActionListener { eventListener.previousPage() }
        }
        dataPanel.add(backButton)

        this.add(dataPanel)
        this.setComponentZOrder(dataPanel, 0)

        return dataPanel
    }

}