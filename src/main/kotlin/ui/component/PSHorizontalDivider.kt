package ui.component

import java.awt.BasicStroke
import java.awt.Color
import java.awt.Graphics
import java.awt.Graphics2D
import javax.swing.JPanel

class PSHorizontalDivider: JPanel() {
    var panelWidth: Int = 100
    var panelHeight: Int = 100
    var stroke: Float = 2F
    var dividerColor: Color = Color.BLACK
    override fun paintComponent(g: Graphics) {
        super.paintComponent(g)
        val g2d = g as Graphics2D
        g2d.stroke = BasicStroke(stroke)
        g2d.color = dividerColor
        g2d.drawLine(panelWidth / 2, 0, panelWidth / 2, panelHeight)
    }
}

