package dto

import constants.LineUpType
import constants.PlayerPosition

data class LineUpDto (
    var player: PlayerDto,
    val type: LineUpType,
    val position: PlayerPosition,
    val positionNumber: Int
)