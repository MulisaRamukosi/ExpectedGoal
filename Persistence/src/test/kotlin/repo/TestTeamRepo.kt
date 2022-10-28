package repo

import constants.CsvFileNames
import dto.TeamDto
import mapper.TeamMapper
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import storage.fileWriter.FileWriterImpl
import java.io.File
import java.io.FileReader

class TestTeamRepo {

    private val locationRepo: TeamRepoImpl = TeamRepoImpl(
        mapper = TeamMapper(),
        fileWriter = FileWriterImpl(fileName = CsvFileNames.LOCATION)
    )

    @BeforeEach
    fun createTeamCsvFile(){
        File(CsvFileNames.TEAM).createNewFile()
    }

    @AfterEach
    fun deleteTeamCsvFile(){
        File(CsvFileNames.TEAM).delete()
    }

    @Test
    fun testWriteSingleLine(){
        locationRepo.save(dto = TeamDto("test"))

        val fileTeamCsv = FileReader(CsvFileNames.LOCATION).readLines()

        assertTrue(fileTeamCsv.size == 1)
    }

    @Test
    fun testWriteMultipleLines(){
        repeat(3){
            locationRepo.save(dto = TeamDto("test$it"))
        }
        val fileTeamCsv = FileReader(CsvFileNames.LOCATION).readLines()

        assertTrue(fileTeamCsv.size == 3)
    }
}