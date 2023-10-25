package ui.panel.mainPanel


import model.Weather
import ui.Navigator
import ui.component.PSButton
import ui.component.PSHorizontalDivider
import ui.component.PSIcon
import ui.component.PSLabel
import ui.extension.*
import ui.panel.airPollutionPanel.AirPollutionPanelView
import java.awt.*
import javax.swing.*


class MainPanelView(response: Weather, navigator: Navigator) : JPanel() {

    private var backgroundColor:Color
    private var foregroundColor:Color
    private var sunStatusText:String

    init {
        if (response.icon.last() == 'd'){
            backgroundColor=Color(0xE5ECF4)
            foregroundColor=Color(0x1E1E1E)
            sunStatusText=response.time.sunSet.convertToCurrentHour(response.time.timeZone)
        }else{
            backgroundColor=Color(0x1E1E1E)
            foregroundColor=Color(0xE5ECF4)
            sunStatusText=response.time.sunrise.convertToCurrentHour(response.time.timeZone)
        }
        layout = null
        isVisible = true
        background = backgroundColor
        setBounds(0, 0, width, height)

        val country = PSLabel().apply {
            setFont(FontEnum.BOLD, 36)
            text = response.cityWithLocation.name
            foreground = foregroundColor
            setBounds(0, 90, 360, 50)
        }
        add(country)

        val date = PSLabel().apply {
            text = "${response.time.current.convertToDayOfWeek(response.time.timeZone)}/${
                response.time.current.convertToCurrentHour(response.time.timeZone)
            }"
            setFont(FontEnum.SEMI_BOLD, 20)
            foreground = foregroundColor
            setBounds(0, 140, 360, 30)
        }
        add(date)

        val tempIcon = PSIcon(
            ImageIcon("assets/IMG/${response.icon}.png").resizeIcon(100, 100),
            "${response.temperature.feelsLikeCentigrade}°C",
        ).apply {
            setBounds(0, 190, 360, 150)
            setFont(FontEnum.BOLD, 40)

            foreground = foregroundColor
            background = backgroundColor

        }
        add(tempIcon)

        val description = PSButton().apply {
            text = response.status.long
            setFont(FontEnum.SEMI_BOLD, 20)
            foreground = foregroundColor
            setBounds(0, 340, 360, 30)
            addActionListener {
                val airPollutionPanel = AirPollutionPanelView(response, navigator).apply {
                    setBounds(0, 0, width, height)
                }
                navigator.push(airPollutionPanel)
            }
        }
        add(description)


        val sunStatus = PSIcon(
            ImageIcon("assets/IMG/sunStatus${response.icon.last()}.png").resizeIcon(50, 50),
            sunStatusText
        ).apply {
            setBounds(0, 450, 106, 100)
            setFont(FontEnum.REGULAR, 16)
            foreground = foregroundColor
            background = backgroundColor

        }
        add(sunStatus)

        val leftHorizontalDivider = PSHorizontalDivider().apply {
            panelWidth = 20
            panelHeight = 50
            stroke = 2F
            dividerColor = foregroundColor
            background = backgroundColor
            setBounds(106, 450, 20, 50)
        }
        add(leftHorizontalDivider)

        val windStatus = PSIcon(
            ImageIcon("assets/IMG/windIcon${response.icon.last()}.png").resizeIcon(50, 50),
            "${response.windSpeed}",
        ).apply {
            setBounds(126, 450, 106, 100)
            setFont(FontEnum.REGULAR, 16)
            foreground = foregroundColor
            background = backgroundColor
        }
        add(windStatus)

        val rightHorizontalDivider = PSHorizontalDivider().apply {
            panelWidth = 20
            panelHeight = 50
            stroke = 2F
            dividerColor = foregroundColor
            background = backgroundColor
            setBounds(232, 450, 20, 50)
        }
        add(rightHorizontalDivider)

        val tempStatus = PSIcon(
            ImageIcon("assets/IMG/tempIcon${response.icon.last()}.png").resizeIcon(50, 50),
            "${response.temperature.realCentigrade}"
        ).apply {
            setBounds(252, 450, 106, 100)
            setFont(FontEnum.REGULAR, 16)
            foreground = foregroundColor
            background = backgroundColor

        }
        add(tempStatus)

        val backButton = PSButton().apply {
            icon = ImageIcon("assets/IMG/back${response.icon.last()}.png").resizeIcon(30, 30)
            setBounds(0, 0, 50, 50)
            addActionListener { navigator.pop() }
        }
        add(backButton)

        repaint()
        revalidate()
    }
}