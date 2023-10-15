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
    private val loadingPanel: LoadingPanelView
    private val startedPanel: StartedPanelView

    init {
        defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        size = Dimension(360, 640)  // /3
        setLocationRelativeTo(null)
        isVisible = true

        loadingPanel = LoadingPanelView(LoadingPanelController())
        startedPanel = StartedPanelView(StartedPanelController(), ActionListener {
            loadingPanel.isVisible = true
        })

        startedPanel.setBounds(0, 0, width, height)
        add(startedPanel)

        loadingPanel.setBounds(0, 0, width, height)
        loadingPanel.isVisible = false
        add(loadingPanel)
    }
}
