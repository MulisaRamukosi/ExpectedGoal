package repo

import dto.MatchDto
import entities.Match
import mapper.BaseMapper
import repositories.MatchRepo
import storage.fileWriter.FileWriter

internal class MatchRepoImpl(
    private val mapper: BaseMapper<MatchDto, Match>,
    private val fileWriter: FileWriter
) : MatchRepo{

    override fun save(dto: MatchDto): Boolean {
        val entity = mapper.toEntity(dto = dto)
        val csvValue = mapper.toEntityString(entity = entity)
        return fileWriter.writeRow(value = csvValue)
    }
}