import core.ApiManager
import kotlinx.coroutines.runBlocking
import ui.loading.LoadingPanelController
import ui.loading.LoadingPanelView
import ui.startedPanel.StartedPanelController
import ui.startedPanel.StartedPanelView
import java.awt.Dimension
import java.awt.event.ActionListener
import javax.swing.JFrame

class MainFrame : JFrame("SkyCast") {
    private lateinit var loadingPanel: LoadingPanelView
    private val startedPanel: StartedPanelView

    init {
        defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        size = Dimension(360, 640)  // /3
        setLocationRelativeTo(null)
        isVisible = true


        startedPanel = StartedPanelView(StartedPanelController(), object:StartedPanelView.EventListener{
            override fun nextPage() {
                loadingPanel = LoadingPanelView(LoadingPanelController())
                loadingPanel.setBounds(0, 0, width, height)
                add(loadingPanel)

            }
        })

        startedPanel.setBounds(0, 0, width, height)
        add(startedPanel)


    }
}

