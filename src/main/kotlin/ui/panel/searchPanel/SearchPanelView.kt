package ui.panel.searchPanel


import domain.GetWeatherBaseOnIpUseCase
import domain.GetCityWeatherUseCase
import domain.GetIpUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import ui.Navigator
import ui.UiStatePanel
import ui.component.PSButton
import ui.component.PSLabel
import ui.component.PSTextField
import ui.model.UiState
import ui.panel.homepanel.LoadingPanelView
import ui.extension.*
import ui.model.FontEnum
import java.awt.Color
import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import javax.swing.*


class SearchPanelView(navigator: Navigator) : UiStatePanel() {
    private val ip = GetIpUseCase()
    private val getWeatherPollutionUseCase = GetCityWeatherUseCase()
    private val startedPanelController = SearchPanelController(
        CoroutineScope(Dispatchers.IO),
        GetCityWeatherUseCase(),
        GetWeatherBaseOnIpUseCase(ip, getWeatherPollutionUseCase)
    )
    private var visibilityChangeListener: ((Boolean) -> Unit)? = null
    private val searchBox = PSTextField().apply {
        columns = 23
        textFieldCornerRadius = 25
        backgroundColor = Color(0xE5ECF4)
        foregroundColor = Color(0x1E1E1E)
        setFont(FontEnum.REGULAR, 16)
    }

    init {
        layout = null
        isVisible = true
        background = Color(0xE5ECF4)

        val title = PSLabel().apply {
            setFont(FontEnum.BOLD, 36)
            text = "SkyCast"
            foreground = Color(0x1E1E1E)
            setBounds(0, 70, 360, 86)
        }
        add(title)


        val imageLabel = PSLabel().apply {
            icon = ImageIcon("assets/IMG/SkyCast.png")
            setBounds(130, 166, 100, 100)
        }
        add(imageLabel)

        val placeHolder = PSLabel().apply {
            setFont(FontEnum.SEMI_BOLD, 16)
            text = "Enter your location:"
            foreground = Color(0x1E1E1E)
            setBounds(60, 300, 240, 30)
        }
        add(placeHolder)


        val errorSearchBox = PSLabel().apply {
            text = "*please enter a valid city name"
            setFont(FontEnum.LIGHT, 12)
            foreground = Color.red
            setBounds(70, 375, 240, 30)
            isVisible = false
        }
        add(errorSearchBox)


        val locationButton = PSButton().apply {
            icon = ImageIcon("assets/IMG/location.png")
            isOpaque = false
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

                            onError(
                                "An error occurred while processing the request"
                            )
                            Thread.sleep(5000)
                            onData()

                        }
                    }
                }
            }
        }
        add(locationButton)


        searchBox.apply {
            setBounds(55, 330, 240, 50)
            addKeyListener(object : KeyListener {
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

        }
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
                    onError(
                        "An error occurred while processing the request"
                    )
                    Thread.sleep(5000)
                    onData()
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
