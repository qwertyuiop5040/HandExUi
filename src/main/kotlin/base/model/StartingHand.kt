package base.model

import java.lang.IllegalArgumentException

/**
 * Same as {@link HoleCards} except there are no specific suits.
 */
data class StartingHand(val first: Rank, val second: Rank, val suitedness: Suitedness){
    enum class Suitedness(val combos: Int, val suffix: String) {
        SUITED(4, "s"),
        UNSUITED(12,"o"),
        PAIR(6, "");
    }
    companion object {
        fun create(hand: String): StartingHand {
            if (hand.length > 3) {
                throw IllegalArgumentException("Hand string cannot be longer than 3.")
            }
            if (hand.length == 2 && hand[0] != hand[1]) {
                throw IllegalArgumentException(
                    "Hand strings of length two can only be pocket pairs (two of the same char).")
            }
            val rank1 = Rank.from(hand[0])
            val rank2 = Rank.from(hand[1])
            if (rank1.numeric < rank2.numeric) {
                throw IllegalArgumentException("Hand strings must contain the higher rank first.")
            }
            val suitedness = if (hand.length == 3) {
                if (hand[2] == 's') Suitedness.SUITED
                else Suitedness.UNSUITED
            } else Suitedness.PAIR
            return StartingHand(rank1, rank2, suitedness)
        }

        fun allHands(): List<StartingHand> {
            val allHands = mutableListOf<StartingHand>()
            Rank.allRanksSorted.forEach { rank ->
                for (rank2 in Rank.allRanksSorted) {
                    if(rank.numeric < rank2.numeric) {
                        continue
                    }
                    if (rank == rank2) {
                        allHands.add(StartingHand(rank, rank2, Suitedness.PAIR))
                        continue
                    }
                    allHands.add(StartingHand(rank, rank2, Suitedness.UNSUITED))
                    allHands.add(StartingHand(rank, rank2, Suitedness.SUITED))
                }
            }
            assert(allHands.size == 169)
            return allHands
        }
    }

    /**
     * Pair 3/1
     * Suited 3/3/2
     * Unsuited 9/6/7
     */
    fun combosWithBlocker(blocker: StartingHand): Int {
        if (suitedness == Suitedness.PAIR) {
            if (blocker == this) {
                return 1
            }
            return if (blocker.first == first || blocker.second == first) {
                 3
            } else 6
        }
        if (blocker.suitedness == Suitedness.PAIR) {
            if(blocker.first == first || blocker.first == second) {
                return if(suitedness == Suitedness.SUITED) 2 else 6
            }
        }
        if (blocker.first == first && blocker.second == second) {
            return if (blocker.suitedness == Suitedness.SUITED) {
                if(suitedness == Suitedness.SUITED) 3 else 6
            } else {
                if(suitedness == Suitedness.SUITED) 2 else 7
            }
        }
        if (blocker.first == first || blocker.second == second) {
            return if(suitedness == Suitedness.SUITED) 3 else 9
        }
        return suitedness.combos
    }

    override fun toString(): String {
        return "${first.shortName}${second.shortName}${suitedness.suffix}"
    }
}