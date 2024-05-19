package ui.view

import base.model.Rank
import ui.model.RangeCell
import java.awt.BorderLayout
import java.awt.Dimension
import java.awt.GridLayout
import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.JPanel

class RangeCellPanel : JPanel() {
    companion object {
        fun create(cell: RangeCell): RangeCellPanel {
            val panel = RangeCellPanel()
            panel.layout = BorderLayout()
            panel.updateWithCell(cell)
            return panel
        }
    }

    fun updateWithCell(cell: RangeCell) {
        add(JLabel(cell.hand.toString()), BorderLayout.CENTER)
        add(JLabel(createTextDetails(cell)), BorderLayout.LINE_END)
        repaint()
    }

    fun createTextDetails(cell: RangeCell) : String {
        return "<html>EV: ${cell.ev}<br>Selected: ${cell.selected}"
    }
}