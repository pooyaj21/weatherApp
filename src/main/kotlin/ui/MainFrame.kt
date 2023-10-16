import ui.loading.LoadingPanelController
import ui.loading.LoadingPanelView
import ui.mainPage.MainPageController
import ui.mainPage.MainPageView
import ui.startedPanel.EventListener
import ui.startedPanel.StartedPanelController
import ui.startedPanel.StartedPanelView
import java.awt.Dimension
import javax.swing.JFrame

class MainFrame : JFrame("SkyCast") {
    private lateinit var loadingPanel: LoadingPanelView
    private val startedPanel: StartedPanelView
    private lateinit var mainPage: MainPageView

    init {
        defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        size = Dimension(360, 640)  // /3
        setLocationRelativeTo(null)
        isVisible = true


        startedPanel = StartedPanelView(StartedPanelController(), object : EventListener {
            override fun nextPage() {
                loadingPanel = LoadingPanelView(LoadingPanelController(), object : EventListener {
                    override fun nextPage() {
                        mainPage = MainPageView(MainPageController())
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


