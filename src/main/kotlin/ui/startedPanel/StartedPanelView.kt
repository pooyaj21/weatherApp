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
    private val startedPanelController = StartedPanelController(
        CoroutineScope(Dispatchers.IO), GetCityWeatherUseCase(ApiManager), GetCityBaseOnIp(ApiManager)
    )
    private var visibilityChangeListener: ((Boolean) -> Unit)? = null
    private val searchBox = RoundedTextField(23, 25, Color(0xE5ECF4), Color(0x1E1E1E), 16)


    init {

        layout = null
        isVisible = true
        background = Color(0xE5ECF4)

        //Title
        JLabel("SkyCast").apply {
            foreground = Color(0x1E1E1E)
            setBounds(0, 70, 360, 86)
            font = Font(null, Font.BOLD, 36)
            horizontalAlignment = JLabel.CENTER
            verticalAlignment = JLabel.CENTER
        }.also {
            add(it)
        }

        //Lego
        JLabel(ImageIcon("assets/SkyCast.png")).apply {
            setBounds(130, 166, 100, 100)

        }.also { add(it) }

        //PlaceHolder
        JLabel("Enter your location:").apply {
            foreground = Color(0x1E1E1E)
            setBounds(60, 300, 240, 30)
            font = Font(null, Font.BOLD, 16)
        }.also {
            add(it)
        }

        //ErrorSearchBoX
        val errorSearchBox = JLabel("*please enter a valid city name").apply {
            foreground = Color.red
            setBounds(70, 375, 240, 30)
            font = Font(null, Font.ITALIC, 12)
            isVisible = false
        }.also {
            add(it)
        }

        //Location Icon
        JButton(ImageIcon("assets/location.png")).apply {
            isOpaque = false
            isBorderPainted = false
            isContentAreaFilled = false
            setBounds(0, 0, 50, 50)
            addActionListener {
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
                                    null
                                )
                                Thread.sleep(5000)
                                onData()
                            }
                        }
                    }
                }
            }

        }.also {
            add(it)
        }

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
