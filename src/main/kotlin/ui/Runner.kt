package ui

import db.loader.ProbConverter
import ui.view.SolverView
import java.io.File
import java.io.InputStream

fun main() {
    val view = SolverView.createWithDefaultSettings();
    view.makeVisibleLater()
}