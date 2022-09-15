package entities

data class Result(
    val id: String,
    val matchId: String,
    val homeScore: Int,
    val awayScore: Int,
)
