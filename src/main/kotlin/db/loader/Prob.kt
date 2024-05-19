package db.loader

/**
 * The probability of certain outcomes that one hand will have against another.
 */
class Prob(val win: Double, val tie: Double, val lose: Double) {
    fun calculateEquity() : Double {
        return win + tie/2.0f;
    }

    override fun toString(): String {
        return "$win/$tie/$lose"
    }
}