package ui.view

import java.awt.BorderLayout
import java.awt.Dimension
import javax.swing.JButton
import javax.swing.JFrame
import javax.swing.SwingUtilities




class SolverView private constructor(title: String): JFrame(title) {
    companion object {
        fun createWithDefaultSettings(): SolverView {
            val view = SolverView("Solver")
            view.layout = BorderLayout()
            view.defaultCloseOperation = EXIT_ON_CLOSE;
            view.size = Dimension(800, 600)
            view.add(SolverModePanel.create(), BorderLayout.PAGE_START)
            return view
        }
    }

    fun makeVisibleLater() {
        SwingUtilities.invokeLater { isVisible = true }
    }
}