package repo

import dto.PlayerDto
import entities.Player
import mapper.BaseMapper
import repositories.PlayerRepo
import storage.fileWriter.FileWriter

internal class PlayerRepoImpl(
    private val mapper: BaseMapper<PlayerDto, Player>,
    private val fileWriter: FileWriter
) : PlayerRepo{

    override fun save(dto: PlayerDto): Boolean {
        val entity = mapper.toEntity(dto = dto)
        val csvValue = mapper.toEntityString(entity = entity)
        return fileWriter.writeRow(value = csvValue)
    }
}