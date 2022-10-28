package repo

import constants.CsvFileNames
import constants.EventType
import dto.EventDto
import dto.PlayerDto
import mapper.EventMapper
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import storage.fileWriter.FileWriterImpl
import java.io.File
import java.io.FileReader

class TestEventRepo {

    private val eventRepo: EventRepoImpl = EventRepoImpl(
        mapper = EventMapper(),
        fileWriter = FileWriterImpl(fileName = CsvFileNames.EVENT)
    )

    @BeforeEach
    fun createEventCsvFile(){
        File(CsvFileNames.TEAM).createNewFile()
    }

    @AfterEach
    fun deleteEventCsvFile(){
        File(CsvFileNames.TEAM).delete()
    }

    @Test
    fun testWriteSingleLine(){
        eventRepo.save(dto = EventDto(PlayerDto(name ="Kitlas",), minute = 5, EventType.OWN_GOAL))

        val fileEventCsv = FileReader(CsvFileNames.EVENT).readLines()

        assertTrue(fileEventCsv.size == 1)
    }

    @Test
    fun testWriteMultipleLines(){
        repeat(3){
            eventRepo.save(dto = EventDto(PlayerDto(name ="Kitlas",), minute = 5, EventType.OWN_GOAL))
        }
        val fileEventCsv = FileReader(CsvFileNames.EVENT).readLines()

        assertTrue(fileEventCsv.size == 4)
    }
}