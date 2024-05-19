package base.model

class Card(val rank: Rank, val suit: Suit) : Comparable<Card> {
    override fun compareTo(other: Card): Int {
        if (rank == other.rank) {
            return other.suit.ordinal - suit.ordinal
        }
        return other.rank.numeric - rank.numeric
    }

}