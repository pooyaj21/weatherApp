package ui.util
import java.awt.*
import java.awt.geom.RoundRectangle2D
import javax.swing.JTextField
import javax.swing.SwingConstants

class RoundedTextField(
    columns: Int,
    private val cornerRadius: Int,
    private val backgroundColor: Color,
    private val foregroundColor: Color,
    fontSize: Int
) : JTextField(columns) {

    init {
        font = Font("Arial", Font.TRUETYPE_FONT, fontSize)
        isOpaque = false
        foreground = foregroundColor
        caretColor = foregroundColor
        border = RoundedBorder(Color(0x1E1E1E),cornerRadius,4,10)
        horizontalAlignment = SwingConstants.LEFT // Align text to the right
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
        g2.color = backgroundColor
        g2.draw(shape)
        super.paintComponent(g2)
        g2.dispose()
    }
}
