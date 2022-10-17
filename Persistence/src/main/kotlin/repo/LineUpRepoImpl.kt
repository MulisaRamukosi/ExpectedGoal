package repo

import dto.LineUpDto
import entities.LineUp
import mapper.BaseMapper
import repositories.LineUpRepo
import storage.fileWriter.FileWriter

internal class LineUpRepoImpl(
    private val mapper: BaseMapper<LineUpDto, LineUp>,
    private val fileWriter: FileWriter
) : LineUpRepo{

    override fun save(dto: LineUpDto): Boolean {
        val entity = mapper.toEntity(dto = dto)
        val csvValue = mapper.toEntityString(entity = entity)
        return fileWriter.writeRow(value = csvValue)
    }
}