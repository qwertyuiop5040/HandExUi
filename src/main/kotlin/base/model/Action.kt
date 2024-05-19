package base.model

open class Action {
    /**
     * Requires all other non-folded players to respond with an action.
     */
    interface ReopensAction

    /**
     * Indicates that the player is still in the hand.
     */
    interface Continuing

    data class Raise(val player: Player, val raiseTo:Int, val previousBet: Int): Action(), ReopensAction, Continuing

    data class Bet(val Player: Player, val amount:Int): Action(), ReopensAction, Continuing

    data class Call(val player: Player, val amount: Int): Action(), Continuing

    data class Check(val Player: Player): Action(), Continuing

    data class Fold(val Player: Player): Action()
}