package mapper

import dto.EventDto
import dto.PlayerDto
import entities.Event
import java.util.*

//TODO: Inject service to retrieve player
internal class EventMapper : BaseMapper<EventDto, Event> {

    override fun toDto(entity: Event): EventDto {
        return EventDto(
            minute = entity.minute,
            type = entity.type,
            //TODO: delegate initialization to another class
            player = PlayerDto(name = "")
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