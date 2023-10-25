package ui.panel.mainPage


import PSIcon
import model.Weather
import ui.Navigator
import ui.component.PSButton
import ui.component.PSHorizontalDivider
import ui.component.PSLabel
import ui.panel.airPollution.AirPollutionView
import ui.util.*
import java.awt.*
import javax.swing.*
import javax.swing.border.Border
import javax.swing.border.LineBorder


class MainPageView(response: Weather, navigator: Navigator) : JPanel() {
    private val mainPageController = MainPageController(response)
    private val backgroundColor = if (mainPageController.getDayOrNight() == "d") Color(0xE5ECF4)
    else Color(0x1E1E1E)

    private val foregroundColor = if (mainPageController.getDayOrNight() == "d") Color(0x1E1E1E)
    else Color(0xE5ECF4)

    init {
        layout = null
        isVisible = true
        background = backgroundColor
        setBounds(0, 0, width, height)

        val country = PSLabel().apply {
            setFont(FontEnum.BOLD, 36)
            text = mainPageController.getCountry()
            foreground = foregroundColor
            setBounds(0, 90, 360, 50)
        }
        add(country)

        val date = PSLabel().apply {
            text = "${mainPageController.getDay()}/${mainPageController.getTime()}"
            setFont(FontEnum.SEMI_BOLD, 20)
            foreground = foregroundColor
            setBounds(0, 140, 360, 30)
        }
        add(date)

        val tempIcon = PSIcon(
            ImageIcon("assets/IMG/${mainPageController.getIcon()}.png").resizeIcon(100, 100),
            "${mainPageController.getFellingTemp()}°C",
        ).apply {
            setBounds(0, 190, 360, 150)
            setFont(FontEnum.BOLD, 40)

            foreground = foregroundColor
            background = backgroundColor

        }
        add(tempIcon)

        val description = PSButton().apply {
            text = mainPageController.getDescription()
            setFont(FontEnum.SEMI_BOLD, 20)
            foreground = foregroundColor
            setBounds(0, 340, 360, 30)
            addActionListener {
                val airPollutionPanel = AirPollutionView(response, navigator).apply {
                    setBounds(0, 0, width, height)
                }
                navigator.push(airPollutionPanel)
            }
        }
        add(description)


        val sunStatusText = if (mainPageController.getDayOrNight() == "d") mainPageController.getSunSet()
        else mainPageController.getSunRise()

        val sunStatus = PSIcon(
            ImageIcon("assets/IMG/sunStatus${mainPageController.getDayOrNight()}.png").resizeIcon(50, 50),
            sunStatusText).apply {
            setBounds(0, 450, 106, 100)
            setFont(FontEnum.REGULAR, 16)
            foreground = foregroundColor
            background = backgroundColor

        }
        add(sunStatus)

        val leftHorizontalDivider = PSHorizontalDivider(20, 50, 2F, foregroundColor).apply {
            background = backgroundColor
            setBounds(106, 450, 20, 50)
        }
        add(leftHorizontalDivider)

        val windStatus = PSIcon(
            ImageIcon("assets/IMG/windIcon${mainPageController.getDayOrNight()}.png").resizeIcon(50, 50),
            "${mainPageController.getWind()}",
        ).apply {
            setBounds(126, 450, 106, 100)
            setFont(FontEnum.REGULAR, 16)
            foreground = foregroundColor
            background = backgroundColor
        }
        add(windStatus)

        val rightHorizontalDivider = PSHorizontalDivider(20, 50, 2F, foregroundColor).apply {
            background = backgroundColor
            setBounds(232, 450, 20, 50)
        }
        add(rightHorizontalDivider)

        val tempStatus = PSIcon(
            ImageIcon("assets/IMG/tempIcon${mainPageController.getDayOrNight()}.png").resizeIcon(50, 50),
            "${mainPageController.getTemp()}"
        ).apply {
            setBounds(252, 450, 106, 100)
            setFont( FontEnum.REGULAR, 16)
            foreground = foregroundColor
            background = backgroundColor

        }
        add(tempStatus)

        val backButton = PSButton().apply {
            icon = ImageIcon("assets/IMG/back${mainPageController.getDayOrNight()}.png").resizeIcon(30, 30)
            setBounds(0, 0, 50, 50)
            addActionListener { navigator.pop() }
        }
        add(backButton)

        repaint()
        revalidate()
    }
}