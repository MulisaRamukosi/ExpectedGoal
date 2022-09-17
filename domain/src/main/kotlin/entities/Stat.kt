package entities

import constants.StatType

data class Stat(
    val id: String,
    val matchId: String,
    val home: Int,
    val away: Int,
    val type: StatType
)