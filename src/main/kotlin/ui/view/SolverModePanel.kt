package ui.view

import javax.swing.BoxLayout
import javax.swing.JButton
import javax.swing.JPanel

class SolverModePanel: JPanel() {
    companion object {
        fun create(): SolverModePanel {
            val panel = SolverModePanel()
            panel.layout = BoxLayout(panel, BoxLayout.X_AXIS)
            panel.add(JButton("Range Calc"))
            panel.add(JButton("Solver2"))
            return panel
        }
    }
}