package ui.airPollution

import core.ApiManager
import core.ApiPollutionData
import core.ApiWeatherData
import domain.GetWeatherPollutionUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import retrofit2.HttpException
import ui.EventListener
import ui.UiState
import ui.UiStatePanel
import ui.util.resizeIcon
import java.awt.Color
import java.awt.Font
import java.awt.event.ActionListener
import java.net.SocketException
import java.net.UnknownHostException
import javax.swing.*

class AirPollutionView(response: ApiWeatherData, eventListener: EventListener) : UiStatePanel() {
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

        val loadingPanel = onLoading()
        loadingPanel.setBounds(0, 0, 370, 640)
        loadingPanel.background=backgroundColor
        add(loadingPanel)
        setComponentZOrder(loadingPanel,0)
        loadingPanel.isVisible=false

        airPollutionController.callBack = {
            when (it) {
                is UiState.Loading -> {
                    loadingPanel.isVisible=true
                }
                is UiState.Data -> {
                    loadingPanel.isVisible=false
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

                    val backIcon = ImageIcon("assets/back${response.weathers[0].icon.last()}.png")
                    val backButton = JButton(resizeIcon(backIcon, 30, 30))
                    backButton.isOpaque = false
                    backButton.isBorderPainted = false
                    backButton.isContentAreaFilled = false
                    backButton.setBounds(0, 0, 50, 50)
                    backButton.addActionListener(ActionListener { eventListener.previousPage() })
                    add(backButton)

                    repaint()
                    revalidate()
                }

                is UiState.Error -> {
                    loadingPanel.isVisible=false
                    if (it.throwable is UnknownHostException || it.throwable is HttpException||it.throwable is SocketException) {
                        val options = arrayOf("Try Again")
                        val choice = JOptionPane.showOptionDialog(
                            null,
                            "Something went wrong",
                            "Confirmation",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            options,
                            options[0]
                        )
                        when (choice) {
                            0 -> {
                                airPollutionController.pollution(response)
                            }
                        }
                    }
                }
            }
        }
        airPollutionController.pollution(response)
    }

    override fun createDataPanel() {
        TODO("Not yet implemented")
    }

}