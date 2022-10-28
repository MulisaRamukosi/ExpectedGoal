package repo

import constants.CsvFileNames
import dto.LocationDto
import mapper.LocationMapper
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import storage.fileWriter.FileWriterImpl
import java.io.File
import java.io.FileReader

class TestLocationRepo {

    private val locationRepo: LocationRepoImpl = LocationRepoImpl(
        mapper = LocationMapper(),
        fileWriter = FileWriterImpl(fileName = CsvFileNames.LOCATION)
    )

    @BeforeEach
    fun createLocationCsvFile(){
        File(CsvFileNames.LOCATION).createNewFile()
    }

    @AfterEach
    fun deleteLocationCsvFile(){
        File(CsvFileNames.LOCATION).delete()
    }

    @Test
    fun testWriteSingleLine(){
        locationRepo.save(dto = LocationDto("test"))

        val fileLocationCsv = FileReader(CsvFileNames.LOCATION).readLines()

        assertTrue(fileLocationCsv.size == 1)
    }

    @Test
    fun testWriteMultipleLines(){
        repeat(3){
            locationRepo.save(dto = LocationDto("test$it"))
        }
        val fileLocationCsv = FileReader(CsvFileNames.LOCATION).readLines()

        assertTrue(fileLocationCsv.size == 4)
    }
}