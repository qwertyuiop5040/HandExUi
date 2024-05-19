package base.model

class Hand private constructor(players: Map<Position, Player>, roundActions: List<RoundAction>, community: Community, metadata: Metadata){
    companion object {
        fun create(players: List<Player>, roundActions: List<RoundAction>, community: Community, metadata: Metadata): Hand {
            val map = mutableMapOf<Position, Player>()
            players.forEach {
                map[it.position] = it
            }
            return Hand(map, roundActions, community, metadata)
        }
    }

    data class Metadata(val id: Long, val sb: Int, val bb: Int)
}