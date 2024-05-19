package base.evaluator

import base.model.Rank

class HandRank private constructor(val type:HandRankType, vararg kickers: Rank) {
    companion object {
        fun create(type:HandRankType, vararg kickers: Rank): HandRank {
            val requiredKickers:Int = when (type) {
                HandRankType.STRAIGHT_FLUSH,
                HandRankType.STRAIGHT -> 1
                HandRankType.FOUR_OF_A_KIND, HandRankType.FULL_HOUSE -> 2
                HandRankType.THREE_OF_A_KIND, HandRankType.TWO_PAIR -> 3
                HandRankType.PAIR -> 4
                HandRankType.FLUSH, HandRankType.HIGH_CARD -> 5
            }
            if (kickers.size != requiredKickers) {
                throw IllegalStateException(String.format("Incorrect number of kickers with hand rank type of %s and kickers %s", type, kickers));
            }
            return HandRank(type, *kickers);
        }
    }
}