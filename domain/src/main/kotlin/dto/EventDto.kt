package dto

import constants.EventType

data class EventDto(
    var Player: PlayerDto,
    var minute: Int,
    var type: EventType
)