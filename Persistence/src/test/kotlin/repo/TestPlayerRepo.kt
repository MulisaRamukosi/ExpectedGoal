package repo

import constants.CsvFileNames
import dto.PlayerDto
import mapper.PlayerMapper
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import storage.fileWriter.FileWriterImpl
import java.io.File
import java.io.FileReader

class TestPlayerRepo {

    private val playerRepo: PlayerRepoImpl = PlayerRepoImpl(
        mapper = PlayerMapper(),
        fileWriter = FileWriterImpl(fileName = CsvFileNames.PLAYER)
    )

    @BeforeEach
    fun createPlayerCsvFile(){
        File(CsvFileNames.PLAYER).createNewFile()
    }

    @AfterEach
    fun deletePlayerCsvFile(){
        File(CsvFileNames.PLAYER).delete()
    }

    @Test
    fun testWriteSingleLine(){
        playerRepo.save(dto = PlayerDto("test"))

        val filePlayerCsv = FileReader(CsvFileNames.PLAYER).readLines()

        assertTrue(filePlayerCsv.size == 1)
    }

    @Test
    fun testWriteMultipleLines(){
        repeat(3){
            playerRepo.save(dto = PlayerDto("test$it"))
        }
        val filePlayerCsv = FileReader(CsvFileNames.PLAYER).readLines()

        assertTrue(filePlayerCsv.size == 3)
    }
}
