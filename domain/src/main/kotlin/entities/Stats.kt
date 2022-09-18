package entities

data class Stats(
    val id: String,
    val matchId: String,
    val shots: Stat,
    val shotsOnTarget: Stat,
    val corners: Stat,
    val offsides: Stat,
    val fouls: Stat,
    val yellowCards: Stat,
    val redCards: Stat
)
