package base.model

import java.lang.IllegalArgumentException

enum class Rank (val shortName:Char, val numeric: Int){
    ACE('A', 14),
    KING('K', 13),
    QUEEN('Q', 12),
    JACK('J', 11),
    TEN('T', 10),
    NINE('9', 9),
    EIGHT('8', 8),
    SEVEN('7', 7),
    SIX('6', 6),
    FIVE('5', 5),
    FOUR('4', 4),
    THREE('3', 3),
    TWO('2', 2);

    companion object {
        val allRanksSorted = listOf(
            ACE,
            KING,
            QUEEN,
            JACK,
            TEN,
            NINE,
            EIGHT,
            SEVEN,
            SIX,
            FIVE,
            FOUR,
            THREE,
            TWO
        )
        private val map: Map<Char, Rank> = mapOf(
            Pair('A', ACE),
            Pair('K', KING),
            Pair('Q', QUEEN),
            Pair('J', JACK),
            Pair('T', TEN),
            Pair('9', NINE),
            Pair('8', EIGHT),
            Pair('7', SEVEN),
            Pair('6', SIX),
            Pair('5', FIVE),
            Pair('4', FOUR),
            Pair('3', THREE),
            Pair('2', TWO),
        )
        fun from(rank: String): Rank {
            if(rank.length != 1) {
                throw IllegalArgumentException("Rank length must be 1.");
            }
            return from(rank[0])
        }
        fun from(rank: Char): Rank {
            return map[rank]!!
        }
    }
}