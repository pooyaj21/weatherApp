package ui.component

import java.awt.*
import javax.swing.border.Border

class PSRoundedBorder : Border {
    var borderColor: Color = Color.BLACK
    var cornerRadius: Int = 14
    var strokeWidth: Int = 4
    var padding: Int = 10
    override fun paintBorder(c: Component, g: Graphics, x: Int, y: Int, width: Int, height: Int) {
        val g2d = g as Graphics2D
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
        val oldStroke = g2d.stroke
        g2d.stroke = BasicStroke(strokeWidth.toFloat())
        val arc = cornerRadius * 2
        g2d.color = borderColor
        g2d.drawRoundRect(x + strokeWidth / 2, y + strokeWidth / 2, width - strokeWidth, height - strokeWidth, arc, arc)
        g2d.stroke = oldStroke
    }

    override fun getBorderInsets(c: Component): Insets {
        return Insets(strokeWidth + padding, strokeWidth + padding, strokeWidth + padding, strokeWidth + padding)
    }

    override fun isBorderOpaque(): Boolean {
        return false
    }
}
