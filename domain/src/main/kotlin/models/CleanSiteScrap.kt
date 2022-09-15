package models

import entities.*

data class CleanSiteScrap (
    val match: Match,
    val result: Result,
    val commentary: Commentary,
    val homeLineUp: LineUp,
    val awayLineUp: LineUp,
    val stats: Stats,
    val events: Event,
    val homeTeam: Team,
    val awayTeam: Team
)