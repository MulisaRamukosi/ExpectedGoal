package entities

import java.util.Date

data class Match (
    val id: String,
    val locationId: String,
    val homeScore: Int,
    val awayScore: Int,
    val date: Date,
)