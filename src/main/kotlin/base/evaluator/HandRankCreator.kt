package base.evaluator

import base.model.Card
import base.model.HoleCards
import base.model.Rank
import base.model.Community.River
import java.util.*

object HandRankCreator {
//    fun create(holeCards: HoleCards, river: River): HandRank {
//        val list: List<Card> = mutableListOf(holeCards.first, holeCards.second, river.first, river.second, river.third, river.fourth, river.fifth);
//        return HandRank.create();
//    }
//    fun createStraightFlush(list: List<Card>):Optional<HandRank> {
//        sortBySuit(list)
//        val suitLengths = getSuitLengths(list)
//        if (suitLengths[0] >= 5) {
//            val len = suitLengths[0]
//            if (isStraightForStraightFlush(list, 0)) {
//                return Optional.of(HandRank.create(HandRankType.STRAIGHT_FLUSH, list[0].rank))
//            }
//            if (isWheelStraightForStraightFlush(list, 0)) {
//                return Optional.of(HandRank.create(HandRankType.STRAIGHT_FLUSH, Rank.FIVE))
//            }
//            if (len >= 6 && isStraightForStraightFlush(list, 1)) {
//                return Optional.of(HandRank.create(HandRankType.STRAIGHT_FLUSH, list[1].rank))
//            }
//            if (len >= 7 && isStraightForStraightFlush(list, 1)) {
//                return Optional.of(HandRank.create(HandRankType.STRAIGHT_FLUSH, list[2].rank))
//            }
//        }
////        if(suitLengths[0] == 1 && suitLengths[1] >= 5) ||
////            (suitLengths[0] == 2 && suitLengths[1] == 5) ||
////            (suitLengths[0] == 1 && suitLengths [1] == 1 && suitLengths[2] == 5))
//    }

    fun getSuitLengths(list:List<Card>): List<Int> {
        val breaks: MutableList<Int> = mutableListOf();
        list.forEachIndexed { index, card ->
            if (index != 0) {
                if (card.suit != list[index-1].suit) {
                    breaks.add(index);
                }
            }
        }
        val adj: MutableList<Int> = mutableListOf()
        var last = 0
        breaks.forEach {
            adj.add(it-last)
            last = it
        }
        val potentialLastAdj = list.size - 1 - last;
        if (potentialLastAdj != 0) {
            adj.add(potentialLastAdj)
        }
        return adj;
    }

    fun isStraightForStraightFlush(list: List<Card>, startIdx:Int): Boolean {
        return list[startIdx].rank.numeric == list[startIdx+1].rank.numeric + 1 &&
                list[startIdx+1].rank.numeric == list[startIdx+2].rank.numeric + 1 &&
                list[startIdx+2].rank.numeric == list[startIdx+3].rank.numeric + 1 &&
                list[startIdx+3].rank.numeric == list[startIdx+4].rank.numeric + 1
    }
    fun isWheelStraightForStraightFlush(list: List<Card>, aceIdx:Int, startIdx:Int): Boolean {
        return list[aceIdx].rank == Rank.ACE &&
            list[startIdx].rank == Rank.FIVE &&
            list[startIdx].rank.numeric == list[startIdx+1].rank.numeric + 1 &&
            list[startIdx+1].rank.numeric == list[startIdx+2].rank.numeric + 1 &&
            list[startIdx+2].rank.numeric == list[startIdx+3].rank.numeric + 1
    }
    // TODO: Maybe sort beforehand and use same list.
    fun sortByRank(list: List<Card>) {
        Collections.sort(list) { a, b ->
            if (a.rank == b.rank) {
                b.suit.ordinal - a.suit.ordinal
            }
            b.rank.numeric - a.rank.numeric
        }
    }

    fun sortBySuit(list: List<Card>) {
        Collections.sort(list) { a, b ->
            if (a.suit == b.suit) {
                b.rank.numeric - a.rank.numeric
            }
            b.suit.ordinal - a.suit.ordinal
        }
    }
}