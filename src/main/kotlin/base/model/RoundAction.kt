package base.model

class RoundAction(val players:List<Player>, val actions:List<Action>, val roundType: RoundType) {
    enum class RoundType {
        PREFLOP,
        FLOP,
        TURN,
        RIVER
    }
}