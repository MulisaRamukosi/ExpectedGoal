package mapper

interface BaseMapper<dto, entity> {

    fun toDto(entity: entity): dto
    fun toEntity(dto: dto): entity
    fun toEntityString(entity: entity): String
}