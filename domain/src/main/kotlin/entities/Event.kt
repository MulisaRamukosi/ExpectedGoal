package entities

import constants.EventType

data class Event (
    var id: String,
    var matchId: String,
    var teamId: String,
    var playerId: Int,
    var minute: Int,
    var type: EventType
)


