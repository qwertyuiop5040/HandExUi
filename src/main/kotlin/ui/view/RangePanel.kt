package ui.view

import base.model.Rank
import java.awt.GridLayout
import javax.swing.BoxLayout
import javax.swing.JButton
import javax.swing.JPanel

class RangePanel(): JPanel() {
    val
    companion object {
        fun create(): RangePanel {
            val panel = RangePanel()
            panel.layout = GridLayout(Rank.values().size, Rank.values().size)
            return panel
        }
    }
}