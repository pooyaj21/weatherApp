import di.domainDiModule
import org.koin.core.context.startKoin
import ui.MainFrame
import javax.swing.SwingUtilities

fun main() {
    startKoin {
        modules(domainDiModule)
    }


    SwingUtilities.invokeLater { MainFrame() }
}