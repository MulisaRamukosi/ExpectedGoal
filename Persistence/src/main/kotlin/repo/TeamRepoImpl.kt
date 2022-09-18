package repo

import dto.TeamDto
import entities.Team
import mapper.BaseMapper
import repositories.TeamRepo
import storage.fileWriter.FileWriter

internal class TeamRepoImpl(
    private val mapper: BaseMapper<TeamDto, Team>,
    private val fileWriter: FileWriter
) : TeamRepo{

    override fun save(dto: TeamDto): Boolean {
        val entity = mapper.toEntity(dto = dto)
        val csvValue = mapper.toEntityString(entity = entity)
        return fileWriter.writeRow(value = csvValue)
    }
}