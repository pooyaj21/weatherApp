package ui.util
import java.awt.*
import java.awt.geom.RoundRectangle2D
import javax.swing.JButton

class RoundedButton(
    text: String?, // TODO: remove this
    private val cornerRadius: Int,
    private val backgroundColor: Color,
    private val foregroundColor: Color,
    fontSize: Int
) : JButton(text) {

    init {
        font = Font("Arial", Font.TRUETYPE_FONT, fontSize)
        isContentAreaFilled = false
        isFocusPainted = false
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
            cornerRadius.toDouble(),
            cornerRadius.toDouble()
        )
        g2.color = backgroundColor
        g2.fill(shape)
        g2.color = foregroundColor
        val fontMetrics = g2.fontMetrics
        val textX = (width - fontMetrics.stringWidth(text)) / 2
        val textY = (height - fontMetrics.height) / 2 + fontMetrics.ascent
        g2.drawString(text, textX, textY)
        g2.dispose()
    }
}