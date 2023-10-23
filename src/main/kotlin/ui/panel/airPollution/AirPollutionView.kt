package ui.panel.airPollution

import core.Manager.*
import core.data.PollutionRepository
import core.domain.GetWeatherPollutionUseCase
import core.model.Pollution
import core.model.Weather
import core.service.PollutionService
import core.service.response.PollutionResponse
import core.service.response.WeatherResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import ui.*
import ui.model.UiState
import ui.util.FontEnum
import ui.util.resizeIcon
import ui.util.setFont
import java.awt.Color
import javax.swing.*


class AirPollutionView(private val response: Weather, private val navigator: Navigator) :
    UiStatePanel() {
    private val airPollutionController = AirPollutionController(
        CoroutineScope(Dispatchers.IO),
        GetWeatherPollutionUseCase(PollutionRepository(PollutionService()))
    )
    private val backgroundColor = if (response.icon.last().toString() == "d") Color(0xE5ECF4)
    else Color(0x1E1E1E)

    private val foregroundColor = if (response.icon.last().toString() == "d") Color(0x1E1E1E)
    else Color(0xE5ECF4)

    private lateinit var airPollutionData: Pollution

    init {
        layout = null
        isVisible = true
        background = Color(0xE5ECF4)

        airPollutionController.callBack = {
            when (it) {
                is UiState.Loading -> {
                    onLoading(backgroundColor)
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
                    navigator.pop()
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
                icon = ImageIcon("assets/IMG/${response.icon}.png"),
                width = 100,
                height = 100
            )
        )
        pollutionIconLabel.setBounds(130, 90, 100, 100)
        dataPanel.add(pollutionIconLabel)

        val description = JLabel(response.description).apply {
            foreground = foregroundColor
            setBounds(0, 200, 360, 30)
            setFont(FontEnum.BOLD, 20)
            horizontalAlignment = JLabel.CENTER
            verticalAlignment = JLabel.CENTER
        }
        dataPanel.add(description)


        val coStatus = JLabel("CO:${airPollutionData.co}").apply {
            foreground = foregroundColor
            setBounds(0, 350, 180, 30)
            setFont(FontEnum.SEMI_BOLD, 20)
            horizontalAlignment = JLabel.CENTER
            verticalAlignment = JLabel.CENTER
        }
        dataPanel.add(coStatus)


        val no2Status = JLabel("NO2:${airPollutionData.no2}").apply {
            foreground = foregroundColor
            setBounds(180, 350, 180, 30)
            setFont(FontEnum.SEMI_BOLD, 20)
            horizontalAlignment = JLabel.CENTER
            verticalAlignment = JLabel.CENTER
        }
        dataPanel.add(no2Status)


        val noStatus = JLabel("NO:${airPollutionData.no}").apply {
            foreground = foregroundColor
            setBounds(0, 400, 180, 30)
            setFont(FontEnum.SEMI_BOLD, 20)
            horizontalAlignment = JLabel.CENTER
            verticalAlignment = JLabel.CENTER
        }
        dataPanel.add(noStatus)


        val o3Status = JLabel("O3:${airPollutionData.o3}").apply {
            foreground = foregroundColor
            setBounds(180, 400, 180, 30)
            setFont(FontEnum.SEMI_BOLD, 20)
            horizontalAlignment = JLabel.CENTER
            verticalAlignment = JLabel.CENTER
        }
        dataPanel.add(o3Status)


        val backButton = JButton(
            resizeIcon(
                icon = ImageIcon("assets/IMG/back${response.icon.last()}.png"),
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
        dataPanel.add(backButton)

        this.add(dataPanel)
        this.setComponentZOrder(dataPanel, 0)

        return dataPanel
    }

}