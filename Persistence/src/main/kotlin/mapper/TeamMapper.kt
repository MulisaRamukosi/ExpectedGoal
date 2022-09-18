package mapper

import dto.TeamDto
import entities.Team
import java.util.*

internal class TeamMapper : BaseMapper<TeamDto, Team> {

    override fun toDto(entity: Team): TeamDto {
        return TeamDto(
            name = entity.name
        )
    }

    override fun toEntity(dto: TeamDto): Team {
        return Team(
            id = UUID.randomUUID().toString(),
            name = dto.name
        )
    }

    override fun toEntityString(entity: Team): String {
        return "${entity.id},${entity.name}"
    }
}