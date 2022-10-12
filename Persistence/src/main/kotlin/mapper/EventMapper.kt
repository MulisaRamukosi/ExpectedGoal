package mapper

import dto.EventDto
import entities.Event
import java.util.*

internal class EventMapper : BaseMapper<EventDto, Event> {

    override fun toDto(entity: Event): EventDto {
        return EventDto(
            minute = entity.minute,
            type = entity.type
        )
    }
    //id,matchId,teamId,playerId,minute,type

    override fun toEntity(dto: EventDto): Event {
        return Event(
            id = UUID.randomUUID().toString(),
            matchId = UUID.randomUUID().toString(),
            teamId = UUID.randomUUID().toString(),
            playerId = UUID.randomUUID().toString(),
            minute = dto.minute,
            type = dto.type

        )
    }
    override fun toEntityString(entity: Event): String {
        return "${entity.id},${entity.matchId},${entity.teamId},${entity.playerId},${entity.minute},${entity.type}"
    }
}