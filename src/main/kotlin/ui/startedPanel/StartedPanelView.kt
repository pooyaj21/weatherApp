package ui.startedPanel

import core.ApiManager
import domain.GetCityBaseOnIp
import domain.GetCityWeatherUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import retrofit2.HttpException
import ui.EventListener
import ui.UiState
import ui.UiStatePanel
import ui.util.RoundedTextField
import java.awt.Color
import java.awt.Font
import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import javax.swing.ImageIcon
import javax.swing.JButton
import javax.swing.JLabel
import javax.swing.JPanel


class StartedPanelView(nextPageLoader: EventListener) : UiStatePanel() {
    private val errorSearchBox = JLabel("*please enter a valid city name")
    private val startedPanelController = StartedPanelController(
        CoroutineScope(Dispatchers.IO), GetCityWeatherUseCase(ApiManager), GetCityBaseOnIp(ApiManager)
    )
    private var visibilityChangeListener: ((Boolean) -> Unit)? = null
    private val searchBox = RoundedTextField(23, 25, Color(0xE5ECF4), Color(0x1E1E1E), 16)
    private val locationIcon = ImageIcon("assets/location.png")
    private val locationButton = JButton(locationIcon)

    init {

        layout = null
        isVisible = true
        background = Color(0xE5ECF4)

        val title = JLabel("SkyCast")
        title.foreground = Color(0x1E1E1E)
        title.setBounds(0, 70, 360, 86)
        title.font = Font(null, Font.BOLD, 36)
        title.horizontalAlignment = JLabel.CENTER
        title.verticalAlignment = JLabel.CENTER
        add(title)

        val icon = ImageIcon("assets/SkyCast.png")
        val imageLabel = JLabel(icon)
        imageLabel.setBounds(130, 166, 100, 100)
        add(imageLabel)

        val placeHolder = JLabel("Enter your location:")
        placeHolder.foreground = Color(0x1E1E1E)
        placeHolder.setBounds(60, 300, 240, 30)
        placeHolder.font = Font(null, Font.BOLD, 16)
        add(placeHolder)

        errorSearchBox.foreground = Color.red
        errorSearchBox.setBounds(70, 375, 240, 30)
        errorSearchBox.font = Font(null, Font.ITALIC, 12)
        errorSearchBox.isVisible = false
        add(errorSearchBox)


        locationButton.isOpaque = false
        locationButton.isBorderPainted = false
        locationButton.isContentAreaFilled = false
        locationButton.setBounds(0, 0, 50, 50)
        locationButton.addActionListener {
            startedPanelController.city()
            startedPanelController.callBack = {
                when (it) {
                    is UiState.Loading -> {
                        onLoading()
                    }

                    is UiState.Data -> {
                        onData()
                        this@StartedPanelView.isVisible = false
                        nextPageLoader.nextPage(it.model)
                    }

                    is UiState.Error -> {
                        if (it.throwable is HttpException) {
                            errorSearchBox.isVisible = true
                            onData()
                        } else {
                            onError(
                                "An error occurred while processing the request",
                                null,
                                null
                            )
                            Thread.sleep(5000)
                            onData()
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
                    onLoading()
                }

                is UiState.Data -> {
                    onData()
                    this@StartedPanelView.isVisible = false
                    nextPageLoader.nextPage(it.model)
                }

                is UiState.Error -> {
                    if (it.throwable is HttpException) {
                        errorSearchBox.isVisible = true
                        onData()
                    } else {
                        onError(
                            "An error occurred while processing the request",
                            null,
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
