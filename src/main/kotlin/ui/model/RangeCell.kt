package ui.model

import base.model.StartingHand
import java.util.*

open class RangeCell(val hand: StartingHand, val selected: Optional<Boolean> = Optional.empty(), val ev: Optional<Double> = Optional.empty()) {
}