package repo

import constants.CsvFileNames
import constants.StatType
import dto.StatDto
import mapper.StatMapper
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import storage.fileWriter.FileWriterImpl
import java.io.File
import java.io.FileReader

class TestStatRepo {

    private val statRepo: StatRepoImpl = StatRepoImpl(
        mapper = StatMapper(),
        fileWriter = FileWriterImpl(fileName = CsvFileNames.STAT)
    )

    @BeforeEach
    fun createStatCsvFile(){
        File(CsvFileNames.TEAM).createNewFile()
    }

    @AfterEach
    fun deleteStatCsvFile(){
        File(CsvFileNames.TEAM).delete()
    }

    @Test
    fun testWriteSingleLine(){
        statRepo.save(dto = StatDto(home = 1,away = 2, type = StatType.SHOTS))

        val fileStatCsv = FileReader(CsvFileNames.STAT).readLines()

        assertTrue(fileStatCsv.size == 1)
    }

    @Test
    fun testWriteMultipleLines(){
        repeat(3){
            statRepo.save(dto = StatDto(home = 1,away = 2, type = StatType.SHOTS))
        }
        val fileStatCsv = FileReader(CsvFileNames.STAT).readLines()

        assertTrue(fileStatCsv.size == 4)
    }
}