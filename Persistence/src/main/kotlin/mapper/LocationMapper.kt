package mapper

import dto.LocationDto
import entities.Location
import java.util.*

internal class LocationMapper : BaseMapper<LocationDto, Location> {

    override fun toDto(entity: Location): LocationDto {
        return LocationDto(
            name = entity.name
        )
    }

    override fun toEntity(dto: LocationDto): Location {
        return Location(
            id = UUID.randomUUID().toString(),
            name = dto.name
        )
    }

    override fun toEntityString(entity: Location): String {
        return "${entity.id},${entity.name}"
    }
}