package mapper

import dto.StatDto
import entities.Stat
import java.util.*

internal class StatMapper : BaseMapper<StatDto, Stat> {
    //"id,matchId,home,away,type
    override fun toDto(entity: Stat): StatDto {
        return StatDto(
            home = entity.home,
            away = entity.away,
            type = entity.type
        )
    }
    //"id,matchId,home,away,type

    override fun toEntity(dto: StatDto): Stat {
        return Stat(
            id = UUID.randomUUID().toString(),
            matchId = UUID.randomUUID().toString(),
            home = dto.home,
            away = dto.away,
            type = dto.type
        )
    }

    override fun toEntityString(entity: Stat): String {
        return "${entity.id},${entity.matchId},${entity.home},${entity.away},${entity.type}"
    }
}