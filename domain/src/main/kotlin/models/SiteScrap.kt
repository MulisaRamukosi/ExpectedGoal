package models

data class SiteScrap(
    val matchId: String,
    val homeLineUp: String,
    val awayLineUp: String,
    val result: String,
    val commentary: String,
    val matchStats: String,
    val matchEvents: String
)