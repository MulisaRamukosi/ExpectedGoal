package mapper

import dto.LineupDto
import entities.Lineup
import java.util.*

internal class LineupMapper : BaseMapper<LineupDto, Lineup> {

    override fun toDto(entity: Lineup): LineupDto {
        return LineupDto(
            type = entity.type,
            position = entity.position,
            positionNumber = entity.positionNumber

        )
    }
    //"id,matchId,teamId,playerId,type,position,positionNumber

    override fun toEntity(dto: LineupDto): Lineup {
        return Lineup(
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
    override fun toEntityString(entity: Lineup): String {
        return "${entity.id},${entity.matchId},${entity.teamId},${entity.playerId},${entity.type},${entity.position},${entity.positionNumber}"
    }
}