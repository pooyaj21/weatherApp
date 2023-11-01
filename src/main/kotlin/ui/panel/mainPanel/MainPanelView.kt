package ui.panel.mainPanel


import model.Weather
import ui.Navigator
import ui.component.PSButton
import ui.component.PSHorizontalDivider
import ui.component.PSIcon
import ui.component.PSLabel
import ui.extension.*
import ui.model.FontEnum
import ui.panel.airPollutionPanel.AirPollutionPanelView
import java.awt.*
import javax.swing.*


class MainPanelView(response: Weather, navigator: Navigator) : JPanel() {

    private var backgroundColor: Color
    private var foregroundColor: Color
    private var sunStatusText: String
    private val mainPanelController =MainPanelController()

    init {
        if (response.icon.last() == 'd') {
            backgroundColor = Color(0xE5ECF4)
            foregroundColor = Color(0x1E1E1E)
            sunStatusText = response.time.sunSet.convertToCurrentHour(response.time.timeZone)
        } else {
            backgroundColor = Color(0x1E1E1E)
            foregroundColor = Color(0xE5ECF4)
            sunStatusText = response.time.sunrise.convertToCurrentHour(response.time.timeZone)
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
            foreground = foregroundColor
            setBounds(0, 140, 360, 30)
        }
        add(date)


        val tempIcon = PSIcon().apply {
            icon = ImageIcon("assets/IMG/${response.icon}.png").resizeIcon(100, 100)
            text = "${response.temperature.feelsLikeCentigrade}°C"
            setBounds(0, 190, 360, 150)
            setFont(FontEnum.BOLD, 40)
            foreground = foregroundColor
            background = backgroundColor

        }
        add(tempIcon)

        val description = PSButton().apply {
            text = response.status.long
            foreground = foregroundColor
            setFont(FontEnum.SEMI_BOLD, 20)
            setBounds(0, 340, 360, 30)
            addActionListener {
                val airPollutionPanel = AirPollutionPanelView(response, navigator).apply {
                    setBounds(0, 0, width, height)
                }
                navigator.push(airPollutionPanel)
            }
        }
        add(description)


        val sunStatus = PSIcon().apply {
            icon = ImageIcon("assets/IMG/sunStatus${response.icon.last()}.png").resizeIcon(50, 50)
            text = sunStatusText
            setBounds(0, 400, 106, 100)
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
            setBounds(106, 420, 20, 50)
        }
        add(leftHorizontalDivider)

        val windStatus = PSIcon().apply {
            icon = ImageIcon("assets/IMG/windIcon${response.icon.last()}.png").resizeIcon(50, 50)
            text = "${response.windSpeed}"
            setBounds(126, 400, 106, 100)
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
            setBounds(232, 420, 20, 50)
        }
        add(rightHorizontalDivider)

        val tempStatus = PSIcon().apply {
            icon = ImageIcon("assets/IMG/tempIcon${response.icon.last()}.png").resizeIcon(50, 50)
            text = "${response.temperature.realCentigrade}"
            setBounds(252, 400, 106, 100)
            setFont(FontEnum.REGULAR, 16)
            foreground = foregroundColor
            background = backgroundColor

        }
        add(tempStatus)

        val qrCode = PSIcon().apply {
            icon = mainPanelController.generateQRCode(
                url = "https://openweathermap.org/city/${response.cityWithLocation.id}",
                width = 90,
                height = 90,
                backgroundColor = backgroundColor,
                foregroundColor = foregroundColor
            )
            text = "Scan Me!!"
            setBounds(126, 485, 106, 140)
            setFont(FontEnum.REGULAR, 16)
            foreground = foregroundColor
            background = backgroundColor
        }
        add(qrCode)

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