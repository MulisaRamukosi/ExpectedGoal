package repo

import dto.EventDto
import entities.Event
import mapper.BaseMapper
import repositories.EventRepo
import storage.fileWriter.FileWriter

internal class EventRepoImpl(
    private val mapper: BaseMapper<EventDto, Event>,
    private val fileWriter: FileWriter
) : EventRepo{

    override fun save(dto: EventDto): Boolean {
        val entity = mapper.toEntity(dto = dto)
        val csvValue = mapper.toEntityString(entity = entity)
        return fileWriter.writeRow(value = csvValue)
    }
}