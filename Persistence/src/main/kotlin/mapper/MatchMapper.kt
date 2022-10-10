package mapper

import dto.MatchDto
import entities.Match
import java.util.*

internal class MatchMapper : BaseMapper<MatchDto, Match> {

    override fun toDto(entity: Match): MatchDto {
        return MatchDto(
            homeScore  = entity.homeScore,
            awayScore = entity.awayScore,
            date  = entity.date
        )
    }

    override fun toEntity(dto: MatchDto): Match {
        return Match(
            id = UUID.randomUUID().toString(),
            locationId = UUID.randomUUID().toString(),
            name = dto.name,
            homeScore  = dto.homeScore,
            awayScore = dto.awayScore,
            date  = dto.date
        )
    }

    override fun toEntityString(entity: Match): String {
        return "${entity.id},${entity.locationId},${entity.homeScore},${entity.awayScore},${entity.date}"
    }
}