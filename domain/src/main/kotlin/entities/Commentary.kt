package entities

data class Commentary(
    val id: String,
    val matchId: String,
    val comment: String,
    val minute: Int
)
