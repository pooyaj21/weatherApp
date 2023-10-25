package ui.component

import java.awt.BasicStroke
import java.awt.Color
import java.awt.Graphics
import java.awt.Graphics2D
import javax.swing.JPanel

class PSHorizontalDivider(
    private val panelWidth: Int,
    private val panelHeight: Int,
    private val stroke: Float,
    private val color: Color
) : JPanel() {
    override fun paintComponent(g: Graphics) {
        super.paintComponent(g)
        val g2d = g as Graphics2D
        g2d.stroke = BasicStroke(stroke)
        g2d.color = color
        g2d.drawLine(panelWidth / 2, 0, panelWidth / 2, panelHeight)
    }
}

