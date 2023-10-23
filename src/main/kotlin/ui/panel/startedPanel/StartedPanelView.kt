package ui.panel.startedPanel

import core.Manager.ApiManager
import core.data.LocationRepository
import core.data.WeatherRepository
import core.domain.GetWeatherBaseOnIp
import core.domain.GetCityWeatherUseCase
import core.domain.GetIpUseCase
import core.model.Weather
import core.service.LocationService
import core.service.WeatherService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import retrofit2.HttpException
import ui.Navigator
import ui.UiStatePanel
import ui.model.UiState
import ui.panel.loading.LoadingPanelView
import ui.util.FontEnum
import ui.util.RoundedTextField
import ui.util.setFont
import java.awt.Color
import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import javax.swing.*


class StartedPanelView(navigator: Navigator) : UiStatePanel() {
    private val locationRepository = LocationRepository(LocationService())
    private val ip = GetIpUseCase()
    private val getWeatherPollutionUseCase = GetCityWeatherUseCase(WeatherRepository(WeatherService()))
    private val startedPanelController = StartedPanelController(
        CoroutineScope(Dispatchers.IO),
        GetCityWeatherUseCase(WeatherRepository(WeatherService())),
        GetWeatherBaseOnIp(locationRepository, ip, getWeatherPollutionUseCase)
    )
    private var visibilityChangeListener: ((Boolean) -> Unit)? = null
    private val searchBox = RoundedTextField(23, 25, Color(0xE5ECF4), Color(0x1E1E1E), 16)


    init {

        layout = null
        isVisible = true
        background = Color(0xE5ECF4)

        val title = JLabel("SkyCast").apply {
            foreground = Color(0x1E1E1E)
            setBounds(0, 70, 360, 86)
            setFont(FontEnum.BOLD, 36)
            horizontalAlignment = JLabel.CENTER
            verticalAlignment = JLabel.CENTER
        }
        add(title)


        val imageLabel = JLabel(ImageIcon("assets/IMG/SkyCast.png")).apply {
            setBounds(130, 166, 100, 100)
        }
        add(imageLabel)

        val placeHolder = JLabel("Enter your location:").apply {
            foreground = Color(0x1E1E1E)
            setBounds(60, 300, 240, 30)
            setFont(FontEnum.SEMI_BOLD, 16)
        }
        add(placeHolder)


        val errorSearchBox = JLabel("*please enter a valid city name").apply {
            foreground = Color.red
            setBounds(70, 375, 240, 30)
            setFont(FontEnum.LIGHT, 12)
            isVisible = false
        }
        add(errorSearchBox)


        val locationButton = JButton(ImageIcon("assets/IMG/location.png")).apply {
            isOpaque = false
            isBorderPainted = false
            isContentAreaFilled = false
            setBounds(0, 0, 50, 50)
            addActionListener {
                startedPanelController.city()
                startedPanelController.callBack = {
                    when (it) {
                        is UiState.Loading -> {
                            onLoading(Color(0xE5ECF4))
                        }

                        is UiState.Data -> {
                            onData()
                            val loadingPanel = LoadingPanelView(it.model, navigator).apply {
                                setBounds(0, 0, width, height)
                            }
                            navigator.push(loadingPanel)
                        }

                        is UiState.Error -> {
                            if (it.throwable is HttpException) {
                                errorSearchBox.isVisible = true
                                onData()
                            } else {
                                onError(
                                    "An error occurred while processing the request",
                                    null
                                )
                                Thread.sleep(5000)
                                onData()
                            }
                        }
                    }
                }
            }

        }
        add(locationButton)


        searchBox.setBounds(55, 330, 240, 50)
        searchBox.addKeyListener(object : KeyListener {
            override fun keyTyped(e: KeyEvent?) {}

            override fun keyPressed(e: KeyEvent?) {
                if (e?.keyCode == KeyEvent.VK_ENTER) {
                    errorSearchBox.isVisible = false
                    startedPanelController.city(searchBox.text)
                }
            }

            override fun keyReleased(e: KeyEvent?) {}
        })
        setVisibilityChangeListener { searchBox.text = "" }
        add(searchBox)


        startedPanelController.callBack = {
            when (it) {
                is UiState.Loading -> {
                    onLoading(Color(0xE5ECF4))
                }

                is UiState.Data -> {
                    onData()
                    val loadingPanel = LoadingPanelView(it.model, navigator).apply {
                        setBounds(0, 0, width, height)
                    }
                    navigator.push(loadingPanel)
                }

                is UiState.Error -> {
                    if (it.throwable is HttpException) {
                        errorSearchBox.isVisible = true
                        onData()
                    } else {
                        onError(
                            "An error occurred while processing the request",
                            null
                        )
                        Thread.sleep(5000)
                        onData()
                    }
                }
            }
        }

    }

    private fun setVisibilityChangeListener(listener: (Boolean) -> Unit) {
        visibilityChangeListener = listener
    }

    override fun createDataPanel(): JPanel {
        return JPanel()
    }

    override fun setVisible(visible: Boolean) {
        super.setVisible(visible)
        if (visible) {
            visibilityChangeListener?.invoke(visible)
        }
    }
}
