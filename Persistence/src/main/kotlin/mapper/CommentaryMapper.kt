package mapper

import dto.CommentaryDto
import entities.Commentary
import java.util.*

internal class CommentaryMapper : BaseMapper<CommentaryDto, Commentary> {

    override fun toDto(entity: Commentary): CommentaryDto {
        return CommentaryDto(
            comment = entity.comment,
            minute = entity.minute
        )
    }

    override fun toEntity(dto: CommentaryDto): Commentary {
        return Commentary(
            id = UUID.randomUUID().toString(),
            matchId = UUID.randomUUID().toString(),
            comment = dto.comment,
            minute = dto.minute
        )
    }
//id,matchId,comment,minute
    override fun toEntityString(entity: Commentary): String {
        return "${entity.id},${entity.matchId},${entity.comment},${entity.minute}"
    }
}