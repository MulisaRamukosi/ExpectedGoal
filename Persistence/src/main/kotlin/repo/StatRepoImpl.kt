package repo

import dto.StatDto
import entities.Stat
import mapper.BaseMapper
import repositories.StatRepo
import storage.fileWriter.FileWriter

internal class StatRepoImpl(
    private val mapper: BaseMapper<StatDto, Stat>,
    private val fileWriter: FileWriter
) : StatRepo{

    override fun save(dto: StatDto): Boolean {
        val entity = mapper.toEntity(dto = dto)
        val csvValue = mapper.toEntityString(entity = entity)
        return fileWriter.writeRow(value = csvValue)
    }
}