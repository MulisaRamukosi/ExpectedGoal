package mapper

import dto.LocationDto
import dto.MatchDto
import dto.TeamDto
import entities.Match
import java.util.*

//TODO: inject services to retrieve the relevant dtos
internal class MatchMapper : BaseMapper<MatchDto, Match> {

    override fun toDto(entity: Match): MatchDto {
        return MatchDto(
            date  = entity.date,
            //TODO: delegate
            location = LocationDto(name = ""),
            homeTeam = TeamDto(name = ""),
            awayTeam = TeamDto(name = ""),
            homeScore  = entity.homeScore,
            awayScore = entity.awayScore,
            stats = emptyList(),
            commentary = emptyList(),
            homeLineUp = emptyList(),
            awayLineUp = emptyList(),
            homeEvents = emptyList(),
            awayEvents = emptyList()
        )
    }

    override fun toEntity(dto: MatchDto): Match {
        return Match(
            id = UUID.randomUUID().toString(),
            locationId = UUID.randomUUID().toString(),
            homeScore  = dto.homeScore,
            awayScore = dto.awayScore,
            date  = dto.date
        )
    }

    override fun toEntityString(entity: Match): String {
        return "${entity.id},${entity.locationId},${entity.homeScore},${entity.awayScore},${entity.date}"
    }
}