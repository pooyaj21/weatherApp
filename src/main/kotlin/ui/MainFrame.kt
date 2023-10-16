import core.ApiManager
import kotlinx.coroutines.runBlocking
import ui.airPollution.AirPollutionController
import ui.airPollution.AirPollutionView
import ui.loading.LoadingPanelController
import ui.loading.LoadingPanelView
import ui.mainPage.MainPageController
import ui.mainPage.MainPageView
import ui.startedPanel.EventListener
import ui.startedPanel.StartedPanelController
import ui.startedPanel.StartedPanelView
import java.awt.Dimension
import java.awt.event.ActionEvent
import java.awt.event.KeyEvent
import javax.swing.*
import javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW

class MainFrame : JFrame("SkyCast") {
    private lateinit var loadingPanel: LoadingPanelView
    private val startedPanel: StartedPanelView
    private lateinit var mainPage: MainPageView
    private lateinit var airPollution: AirPollutionView

    init {
        defaultCloseOperation = EXIT_ON_CLOSE
        size = Dimension(370, 640)  // /3
        setLocationRelativeTo(null)
        isVisible = true


        startedPanel = StartedPanelView(StartedPanelController(), object : EventListener {
            override fun nextPage() {
                loadingPanel = LoadingPanelView(LoadingPanelController(), object : EventListener {
                    override fun nextPage() {
                        mainPage = MainPageView(MainPageController(), object : EventListener {
                            override fun nextPage() {
                                airPollution = AirPollutionView(AirPollutionController())
                                airPollution.setBounds(0, 0, width, height)
                                add(airPollution)
                            }
                        })
                        add(mainPage)
                    }
                })
                loadingPanel.setBounds(0, 0, width, height)
                add(loadingPanel)
            }
        })
        startedPanel.setBounds(0, 0, width, height)
        add(startedPanel)
    }
}


