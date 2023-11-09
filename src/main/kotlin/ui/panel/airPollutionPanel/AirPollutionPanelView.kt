package ui.panel.airPollutionPanel

import domain.GetPollutionUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import model.Pollution
import model.Weather
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.component.inject
import ui.Navigator
import ui.UiStatePanel
import ui.component.PSButton
import ui.component.PSIcon
import ui.component.PSLabel
import ui.extension.resizeIcon
import ui.extension.setFont
import ui.model.FontEnum
import ui.model.UiState
import java.awt.Color
import javax.swing.ImageIcon
import javax.swing.JPanel


class AirPollutionPanelView(private val response: Weather, private val navigator: Navigator) :
    UiStatePanel(), KoinComponent {
    private val getWeatherPollutionUseCase: GetPollutionUseCase = get()

    private val airPollutionController = AirPollutionPanelController(
        CoroutineScope(Dispatchers.IO),
        getWeatherPollutionUseCase
    )

    private val backgroundColor = if (response.icon.last() == 'd') Color(0xE5ECF4)
    else Color(0x1E1E1E)

    private val foregroundColor = if (response.icon.last() == 'd') Color(0x1E1E1E)
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


        val pollutionStatus = PSIcon().apply {
            icon = ImageIcon("assets/IMG/${response.icon}.png").resizeIcon(100, 100)
            text = response.status.long
            setBounds(0, 90, 360, 150)
            setFont(
                FontEnum.SEMI_BOLD,
                36,
            )
            foreground = foregroundColor
            background = backgroundColor

        }
        dataPanel.add(pollutionStatus)

        val coStatusTitle = PSLabel().apply {
            text = "CO:"
            foreground = foregroundColor
            setBounds(0, 350, 130, 30)
        }
        dataPanel.add(coStatusTitle)

        val coStatusAmount = PSLabel().apply {
            text = "${airPollutionData.amountOfCo}"
            foreground = foregroundColor
            setBounds(50, 350, 130, 30)
            setFont(FontEnum.REGULAR, 20)
        }
        dataPanel.add(coStatusAmount)


        val no2StatusTitle = PSLabel().apply {
            text = "NO2:"
            foreground = foregroundColor
            setBounds(180, 350, 130, 30)
        }
        dataPanel.add(no2StatusTitle)
        val no2StatusAmount = PSLabel().apply {
            text = "${airPollutionData.amountOfNo2}"
            foreground = foregroundColor
            setBounds(230, 350, 130, 30)
            setFont(FontEnum.REGULAR, 20)
        }
        dataPanel.add(no2StatusAmount)


        val noStatusTitle = PSLabel().apply {
            text = "NO:"
            foreground = foregroundColor
            setBounds(0, 400, 130, 30)
        }
        dataPanel.add(noStatusTitle)

        val noStatusAmount = PSLabel().apply {
            text = "${airPollutionData.amountOfNo}"
            foreground = foregroundColor
            setBounds(50, 400, 130, 30)
            setFont(FontEnum.REGULAR, 20)
        }
        dataPanel.add(noStatusAmount)


        val o3StatusTitle = PSLabel().apply {
            text = "O3:"
            foreground = foregroundColor
            setBounds(180, 400, 130, 30)
        }
        dataPanel.add(o3StatusTitle)

        val o3StatusAmount = PSLabel().apply {
            text = "${airPollutionData.amountOfO3}"
            foreground = foregroundColor
            setBounds(230, 400, 130, 30)
            setFont(FontEnum.REGULAR, 20)
        }
        dataPanel.add(o3StatusAmount)


        val backButton = PSButton().apply {
            icon = ImageIcon("assets/IMG/back${response.icon.last()}.png").resizeIcon(30, 30)
            setBounds(0, 0, 50, 50)
            addActionListener { navigator.pop() }
        }
        dataPanel.add(backButton)

        this.add(dataPanel)
        this.setComponentZOrder(dataPanel, 0)

        return dataPanel
    }

}