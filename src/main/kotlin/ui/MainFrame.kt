import core.ApiWeatherData
import ui.EventListener
import ui.airPollution.AirPollutionView
import ui.loading.LoadingPanelView
import ui.mainPage.MainPageView
import ui.startedPanel.StartedPanelView
import java.awt.Dimension
import javax.swing.JFrame

class MainFrame : JFrame("SkyCast") {
    private lateinit var loadingPanel: LoadingPanelView
    private lateinit var startedPanel: StartedPanelView
    private lateinit var mainPage: MainPageView
    private lateinit var airPollution: AirPollutionView

    init {
        defaultCloseOperation = EXIT_ON_CLOSE
        size = Dimension(370, 640)  // /3
        setLocationRelativeTo(null)
        isVisible = true


        startedPanel = StartedPanelView(object : EventListener {
            override fun nextPage(response: ApiWeatherData) {
                loadingPanel = LoadingPanelView(response, object : EventListener {
                    override fun nextPage(response: ApiWeatherData) {
                        mainPage = MainPageView(response, object : EventListener {
                            override fun nextPage(response: ApiWeatherData) {
                                airPollution = AirPollutionView(response, object : EventListener {
                                    override fun nextPage(response: ApiWeatherData) {}
                                    override fun previousPage() {
                                        airPollution.isVisible = false
                                        mainPage.isVisible = true
                                    }
                                })
                                airPollution.setBounds(0, 0, width, height)
                                add(airPollution)
                            }

                            override fun previousPage() {
                                mainPage.isVisible = false
                                loadingPanel.isVisible = true
                            }
                        })
                        mainPage.setBounds(0, 0, width, height)
                        add(mainPage)
                    }

                    override fun previousPage() {
                        loadingPanel.isVisible = false
                        startedPanel.isVisible = true
                    }

                })
                loadingPanel.setBounds(0, 0, width, height)
                add(loadingPanel)
                loadingPanel.repaint()
                loadingPanel.revalidate()
            }

            override fun previousPage() {}

        },
            object : EventListener {
                override fun nextPage(response: ApiWeatherData) {
                    loadingPanel = LoadingPanelView(response, object : EventListener {
                        override fun nextPage(response: ApiWeatherData) {
                            mainPage = MainPageView(response, object : EventListener {
                                override fun nextPage(response: ApiWeatherData) {
                                    airPollution = AirPollutionView(response, object : EventListener {
                                        override fun nextPage(response: ApiWeatherData) {}
                                        override fun previousPage() {
                                            airPollution.isVisible = false
                                            mainPage.isVisible = true
                                        }
                                    })
                                    airPollution.setBounds(0, 0, width, height)
                                    add(airPollution)
                                }

                                override fun previousPage() {
                                    mainPage.isVisible = false
                                    loadingPanel.isVisible = true
                                }
                            })
                            mainPage.setBounds(0, 0, width, height)
                            add(mainPage)
                        }

                        override fun previousPage() {
                            loadingPanel.isVisible = false
                            startedPanel.isVisible = true
                        }

                    })
                    loadingPanel.setBounds(0, 0, width, height)
                    add(loadingPanel)
                    loadingPanel.repaint()
                    loadingPanel.revalidate()
                }

                override fun previousPage() {}
            })

        startedPanel.setBounds(0, 0, width, height)
        add(startedPanel)
    }
}


