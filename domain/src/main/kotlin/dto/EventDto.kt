package dto

import constants.EventType

data class EventDto(
    var player: PlayerDto,
    var minute: Int,
    var type: EventType
)