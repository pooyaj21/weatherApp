package ui.startedPanel

import core.ApiManager
import core.ApiWeatherData
import domain.GetCityWeatherUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import retrofit2.Response
import ui.UiState
import ui.util.RoundedTextField
import java.awt.Color
import java.awt.Font
import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import java.lang.Exception
import javax.swing.ImageIcon
import javax.swing.JLabel
import javax.swing.JPanel

val errorSearchBox = JLabel("*please enter a valid city name")

class StartedPanelView(nextPageLoader: EventListener) : JPanel() {
    val startedPanelController=StartedPanelController(CoroutineScope(Dispatchers.IO), GetCityWeatherUseCase(ApiManager))
    init {
        startedPanelController.callBack = {
            when(it) {
                is UiState.Loading -> println("Loading")
                is UiState.Data -> {
                    this@StartedPanelView.isVisible = false
                    nextPageLoader.nextPage(it.model)
                }
                is UiState.Error -> errorSearchBox.isVisible =true
            }
        }

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

        val searchBox = RoundedTextField(23, 25, Color(0xE5ECF4), Color(0x1E1E1E), 16)
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
        add(searchBox)


    }
}


fun interface EventListener {
    fun nextPage(response: ApiWeatherData)
}
