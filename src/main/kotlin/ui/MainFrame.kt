import ui.airPollution.AirPollutionView
import ui.loading.LoadingPanelView
import ui.mainPage.MainPageView
import ui.startedPanel.StartedPanelView
import ui.EventListener
import java.awt.Dimension
import javax.swing.JFrame

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


        startedPanel = StartedPanelView({
            loadingPanel = LoadingPanelView(it, EventListener {
                mainPage = MainPageView(it, EventListener {
                    airPollution = AirPollutionView(it)
                    airPollution.setBounds(0, 0, width, height)
                    add(airPollution)
                })
                mainPage.setBounds(0, 0, width, height)
                add(mainPage)
            })
            loadingPanel.setBounds(0, 0, width, height)
            add(loadingPanel)
            loadingPanel.repaint()
            loadingPanel.revalidate()
        },
            {
                loadingPanel = LoadingPanelView(it, EventListener {
                    mainPage = MainPageView(it, EventListener {
                        airPollution = AirPollutionView(it)
                        airPollution.setBounds(0, 0, width, height)
                        add(airPollution)
                    })
                    mainPage.setBounds(0, 0, width, height)
                    add(mainPage)
                })
                loadingPanel.setBounds(0, 0, width, height)
                add(loadingPanel)
                loadingPanel.repaint()
                loadingPanel.revalidate()
            })
        startedPanel.setBounds(0, 0, width, height)
        add(startedPanel)
    }
}


