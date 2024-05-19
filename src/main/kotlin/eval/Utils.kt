package eval

import base.model.StartingHand
import db.loader.Prob
import java.util.*

class Utils(val probMap: Map<Pair<StartingHand, StartingHand>, Prob>){
    class CompareRangeSummary(val rangeEq: Double,
                              val regret: Double,
                              val allHandsEq: Map<StartingHand, Double>,
                              val regretMap: Map<StartingHand, Regret>
    )
    fun compareRanges(range: Range, range2: Range): Pair<CompareRangeSummary, CompareRangeSummary> {
        val range1AllHandsEq = StartingHand.allHands().map { hand ->
            Pair(hand, calculateEqForHand(hand, range2))
        }.toMap()
        val range2AllHandsEq = StartingHand.allHands().map { hand ->
            Pair(hand, calculateEqForHand(hand, range))
        }.toMap()
        val range1Eq = calculateRangeEq(range, range1AllHandsEq)
        val range2Eq = calculateRangeEq(range2, range2AllHandsEq)
        val range1RegretMap = calculateRegretMap(range, range1AllHandsEq)
        val range2RegretMap = calculateRegretMap(range2, range2AllHandsEq)
        val range1Regret = calculateRegret(range1RegretMap)
        val range2Regret = calculateRegret(range2RegretMap)
        return Pair(CompareRangeSummary(range1Eq, range1Regret, range1AllHandsEq, range1RegretMap),
            CompareRangeSummary(range2Eq, range2Regret, range2AllHandsEq, range2RegretMap))
    }
    private fun calculateEqForHand(hand: StartingHand, range: Range): Double {
        var totalCombos = 0
        var totalEq = 0.0
        range.hands.forEach { hand2 ->
            val prob = probMap[Pair(hand, hand2)]!!
            val combos = hand2.combosWithBlocker(hand)
            totalEq += (range.winEq * prob.win + range.tieEq * prob.tie + range.loseEq * prob.lose) * combos
            totalCombos += combos
        }
        return totalEq / totalCombos
    }

    private fun calculateRangeEq(range: Range, eqMap: Map<StartingHand, Double>): Double {
        return range.hands.sumByDouble {
            eqMap[it]!!
        }
    }

    data class Regret(val quantity: Double, val direction: RegretType){
        enum class RegretType {
            REGRET_NOT_TAKEN_ACTION,
            REGRET_TAKING_ACTION
        }
    }

    private fun calculateRegretMap(range: Range, eqMap: Map<StartingHand, Double>): Map<StartingHand, Regret> {
        val rangeHands = range.hands.toSet()
        return eqMap.keys.map { startingHand ->
            val eq = eqMap[startingHand]!!
            if (rangeHands.contains(startingHand) && eq < 0.0) {
                Optional.of(Pair(startingHand, Regret(Math.abs(eq), Regret.RegretType.REGRET_TAKING_ACTION)))
            }
            if (!rangeHands.contains(startingHand) && eq > 0.0) {
                Optional.of(Pair(startingHand, Regret(eq, Regret.RegretType.REGRET_NOT_TAKEN_ACTION)))
            } else {
                Optional.empty()
            }
        }.filter {
            it.isPresent
        }.map {
            it.get()
        }.toMap()
    }

    private fun calculateRegret(regretMap: Map<StartingHand, Regret>): Double  {
        return regretMap.values.sumByDouble {
            it.quantity
        }
    }
}