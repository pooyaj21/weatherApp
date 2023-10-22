//import core.ApiWeatherData
//import ui.EventListener
//import ui.airPollution.AirPollutionView
//import ui.loading.LoadingPanelView
//import ui.mainPage.MainPageView
//import ui.startedPanel.StartedPanelView
//import java.awt.Dimension
//import javax.swing.JFrame
//
//class MainFrame : JFrame("SkyCast") {
//    private lateinit var loadingPanel: LoadingPanelView
//    private lateinit var startedPanel: StartedPanelView
//    private lateinit var mainPage: MainPageView
//    private lateinit var airPollution: AirPollutionView
//
//    init {
//        defaultCloseOperation = EXIT_ON_CLOSE
//        size = Dimension(370, 640)  // /3
//        setLocationRelativeTo(null)
//        isVisible = true
//
//
//        startedPanel = StartedPanelView(object : EventListener {
//            override fun nextPage(response: ApiWeatherData) {
//                loadingPanel = LoadingPanelView(response, object : EventListener {
//                    override fun nextPage(response: ApiWeatherData) {
//                        mainPage = MainPageView(response, object : EventListener {
//                            override fun nextPage(response: ApiWeatherData) {
//                                airPollution = AirPollutionView(response, object : EventListener {
//                                    override fun nextPage(response: ApiWeatherData) {}
//                                    override fun previousPage() {
//                                        airPollution.isVisible = false
//                                        mainPage.isVisible = true
//                                    }
//                                })
//                                airPollution.setBounds(0, 0, width, height)
//                                add(airPollution)
//                            }
//
//                            override fun previousPage() {
//                                mainPage.isVisible = false
//                                loadingPanel.isVisible = true
//                            }
//                        })
//                        mainPage.setBounds(0, 0, width, height)
//                        add(mainPage)
//                    }
//
//                    override fun previousPage() {
//                        loadingPanel.isVisible = false
//                        startedPanel.isVisible = true
//                    }
//
//                })
//                loadingPanel.setBounds(0, 0, width, height)
//                add(loadingPanel)
//                loadingPanel.repaint()
//                loadingPanel.revalidate()
//            }
//
//            override fun previousPage() {}
//
//        })
//
//        startedPanel.setBounds(0, 0, width, height)
//        add(startedPanel)
//    }
//}
//
//
import core.ApiWeatherData
import ui.EventListener
import ui.airPollution.AirPollutionView
import ui.loading.LoadingPanelView
import ui.mainPage.MainPageView
import ui.startedPanel.StartedPanelView
import java.awt.Dimension
import javax.swing.JFrame
import javax.swing.JPanel

class MainFrame : JFrame("SkyCast") {
    private lateinit var loadingPanel: LoadingPanelView
    private lateinit var startedPanel: StartedPanelView
    private lateinit var mainPage: MainPageView
    private lateinit var airPollution: AirPollutionView

    init {
        defaultCloseOperation = EXIT_ON_CLOSE
        size = Dimension(370, 640)
        setLocationRelativeTo(null)
        isVisible = true

        initializeStartedPanel()
    }

    private fun initializeStartedPanel() {
        startedPanel = StartedPanelView(object : EventListener {
            override fun nextPage(response: ApiWeatherData) {
                loadingPanel = LoadingPanelView(response, loadingPanelListener())
                addPanel(loadingPanel)

                loadingPanel.setBounds(0, 0, width, height)
            }

            override fun previousPage() {
                // No previous action in this panel
            }
        })

        startedPanel.setBounds(0, 0, width, height)
        addPanel(startedPanel)
    }

    private fun loadingPanelListener(): EventListener {
        return object : EventListener {
            override fun nextPage(response: ApiWeatherData) {
                mainPage = MainPageView(response, mainPageListener())
                addPanel(mainPage)

                mainPage.setBounds(0, 0, width, height)
            }

            override fun previousPage() {
                startedPanel.isVisible = true
                loadingPanel.isVisible = false
            }
        }
    }

    private fun mainPageListener(): EventListener {
        return object : EventListener {
            override fun nextPage(response: ApiWeatherData) {
                airPollution = AirPollutionView(response, airPollutionListener())
                addPanel(airPollution)

                airPollution.setBounds(0, 0, width, height)
            }

            override fun previousPage() {
                mainPage.isVisible = true
                loadingPanel.isVisible = false
            }
        }
    }

    private fun airPollutionListener(): EventListener {
        return object : EventListener {
            override fun nextPage(response: ApiWeatherData) {
                // No next action in this panel
            }

            override fun previousPage() {
                airPollution.isVisible = false
                mainPage.isVisible = true
            }
        }
    }

    private fun addPanel(panel: JPanel) {
        add(panel)
        panel.repaint()
        panel.revalidate()
    }
}
