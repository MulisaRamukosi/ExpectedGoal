package entities

data class LineUp (
    val id: String,
    val matchId: String,
    val teamId: String,
    val teamName: String,
    val mainLine: List<Player>,
    val substitutes: List<Player>
)