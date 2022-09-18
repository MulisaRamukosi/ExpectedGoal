package dto

import constants.StatType

data class StatDto(
    val home: Int,
    val away: Int,
    val type: StatType
)