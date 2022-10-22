package mapper

import dto.PlayerDto
import entities.Player
import java.util.*

internal class PlayerMapper : BaseMapper<PlayerDto, Player> {

    override fun toDto(entity: Player): PlayerDto {
        return PlayerDto(
            name = entity.name
        )
    }

    override fun toEntity(dto: PlayerDto): Player {
        return Player(
            id = UUID.randomUUID().toString(),
            name = dto.name
        )
    }

    override fun toEntityString(entity: Player): String {
        return "${entity.id},${entity.name}"
    }
}