package ui.component

import java.awt.*
import java.awt.geom.RoundRectangle2D
import javax.swing.JTextField
import javax.swing.SwingConstants

open class PSTextField : JTextField() {
    var textFieldCornerRadius: Int = 14
    var backgroundColor: Color = Color.WHITE
    var foregroundColor: Color = Color.BLACK

    init {
        isOpaque = false
        foreground = foregroundColor
        caretColor = foregroundColor
        border = PSRoundedBorder().apply {
            borderColor=Color(0x1E1E1E)
            cornerRadius=textFieldCornerRadius
            strokeWidth=4
            padding=10
        }
        horizontalAlignment = SwingConstants.LEFT
    }

    override fun paintComponent(g: Graphics) {
        val g2 = g.create() as Graphics2D
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
        val width = width
        val height = height
        val shape: Shape = RoundRectangle2D.Double(
            0.0,
            0.0,
            (width - 1).toDouble(),
            (height - 1).toDouble(),
            textFieldCornerRadius.toDouble(),
            textFieldCornerRadius.toDouble()
        )
        g2.color = backgroundColor
        g2.fill(shape)
        g2.color = backgroundColor
        g2.draw(shape)
        super.paintComponent(g2)
        g2.dispose()
    }
}
