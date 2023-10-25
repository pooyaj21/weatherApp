package ui.panel.airPollution

import PSIcon
import domain.GetWeatherPollutionUseCase
import model.Pollution
import model.Weather
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import ui.*
import ui.component.PSButton
import ui.component.PSLabel
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
        GetWeatherPollutionUseCase()
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
                        "An error occurred while processing the request"
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


        val pollutionStatus = PSIcon(
            ImageIcon("assets/IMG/${response.icon}.png").resizeIcon(100, 100),
            response.description,
            ).apply {
            setBounds(0, 90, 360, 150)
            setFont(FontEnum.SEMI_BOLD,
                36,)
            foreground = foregroundColor
            background = backgroundColor

        }
        dataPanel.add(pollutionStatus)

        val coStatus = PSLabel().apply {
            setFont(FontEnum.REGULAR,20)
            text="CO:${airPollutionData.co}"
            foreground = foregroundColor
            setBounds(0, 350, 180, 30)
        }
        dataPanel.add(coStatus)


        val no2Status = PSLabel().apply {
            setFont(FontEnum.REGULAR,20)
            text="NO2:${airPollutionData.no2}"
            foreground = foregroundColor
            setBounds(180, 350, 180, 30)
        }
        dataPanel.add(no2Status)


        val noStatus = PSLabel().apply {
            setFont(FontEnum.REGULAR,20)
            text="NO:${airPollutionData.no}"
            foreground = foregroundColor
            setBounds(0, 400, 180, 30)
        }
        dataPanel.add(noStatus)


        val o3Status = PSLabel().apply {
            setFont(FontEnum.REGULAR,20)
            text="O3:${airPollutionData.o3}"
            foreground = foregroundColor
            setBounds(180, 400, 180, 30)
        }
        dataPanel.add(o3Status)


        val backButton = PSButton().apply {
            icon=ImageIcon("assets/IMG/back${response.icon.last()}.png").resizeIcon(30,30)
            setBounds(0, 0, 50, 50)
            addActionListener { navigator.pop() }
        }
        dataPanel.add(backButton)

        this.add(dataPanel)
        this.setComponentZOrder(dataPanel, 0)

        return dataPanel
    }

}