package mapper

import dto.LineUpDto
import dto.PlayerDto
import entities.LineUp
import java.util.*

//TODO: inject service to retrieve player
internal class LineupMapper : BaseMapper<LineUpDto, LineUp> {

    override fun toDto(entity: LineUp): LineUpDto {
        return LineUpDto(
            type = entity.type,
            position = entity.position,
            positionNumber = entity.positionNumber,
            //TODO: delegate initialization to another class
            player = PlayerDto("")
        )
    }
    //"id,matchId,teamId,playerId,type,position,positionNumber

    override fun toEntity(dto: LineUpDto): LineUp {
        return LineUp(
            id = UUID.randomUUID().toString(),
            matchId = UUID.randomUUID().toString(),
            teamId = UUID.randomUUID().toString(),
            playerId = UUID.randomUUID().toString(),
            type = dto.type,
            position = dto.position,
            positionNumber = dto.positionNumber

        )
    }
//id,matchId,comment,minute
    override fun toEntityString(entity: LineUp): String {
        return "${entity.id},${entity.matchId},${entity.teamId},${entity.playerId},${entity.type},${entity.position},${entity.positionNumber}"
    }
}