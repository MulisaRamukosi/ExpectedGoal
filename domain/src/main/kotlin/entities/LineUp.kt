package entities

import constants.LineUpType
import constants.PlayerPosition

data class LineUp (
    val id: String,
    val matchId: String,
    val teamId: String,
    val playerId: String,
    val type: LineUpType,
    val position: PlayerPosition,
    val positionNumber: Int
)