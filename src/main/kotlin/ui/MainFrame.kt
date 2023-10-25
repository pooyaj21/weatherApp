package ui

import ui.panel.searchPanel.SearchPanelView
import java.awt.Dimension
import java.util.*
import javax.swing.JFrame
import javax.swing.JPanel

class MainFrame : JFrame("SkyCast") {

    private var startedPanel: SearchPanelView
    private val navigator = StackNavigator(this)

    init {
        defaultCloseOperation = EXIT_ON_CLOSE
        size = Dimension(370, 640)
        setLocationRelativeTo(null)
        isVisible = true

        startedPanel = SearchPanelView(navigator)

        navigator.push(startedPanel)
    }

    private class StackNavigator(private val frame: JFrame) : Navigator {

        private val stack = Stack<JPanel>()

        override fun pop() {
            stack.lastOrNull()?.let { frame.remove(it) }
            if (stack.isNotEmpty()) stack.pop()
            stack.lastOrNull()?.isVisible = true
        }

        override fun push(panel: JPanel) {
            stack.lastOrNull()?.isVisible = false
            stack.push(panel)
            frame.add(panel)
        }
    }

}

interface Navigator {
    fun pop()
    fun push(panel: JPanel)
}
