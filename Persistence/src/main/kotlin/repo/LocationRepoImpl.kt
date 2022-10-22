package repo

import dto.LocationDto
import entities.Location
import mapper.BaseMapper
import repositories.LocationRepo
import storage.fileWriter.FileWriter

internal class LocationRepoImpl(
    private val mapper: BaseMapper<LocationDto, Location>,
    private val fileWriter: FileWriter
) : LocationRepo{

    override fun save(dto: LocationDto): Boolean {
        val entity = mapper.toEntity(dto = dto)
        val csvValue = mapper.toEntityString(entity = entity)
        return fileWriter.writeRow(value = csvValue)
    }
}