package base.model

open class Community {
    class Preflop: Community()
    data class Flop(val first: Card, val second: Card, val third: Card): Community()
    data class Turn (val first: Card, val second: Card, val third: Card, val fourth: Card): Community()
    data class River (val first: Card, val second: Card, val third: Card, val fourth: Card, val fifth: Card): Community()
}

