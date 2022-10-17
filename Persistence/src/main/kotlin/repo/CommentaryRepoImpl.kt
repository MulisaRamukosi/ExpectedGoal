package repo

import dto.CommentaryDto
import entities.Commentary
import mapper.BaseMapper
import repositories.CommentaryRepo
import storage.fileWriter.FileWriter

internal class CommentaryRepoImpl(
    private val mapper: BaseMapper<CommentaryDto, Commentary>,
    private val fileWriter: FileWriter
) : CommentaryRepo{

    override fun save(dto: CommentaryDto): Boolean {
        val entity = mapper.toEntity(dto = dto)
        val csvValue = mapper.toEntityString(entity = entity)
        return fileWriter.writeRow(value = csvValue)
    }
}