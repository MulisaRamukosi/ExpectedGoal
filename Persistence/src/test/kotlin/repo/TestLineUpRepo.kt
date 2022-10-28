package repo

import constants.CsvFileNames
import constants.LineUpType
import constants.PlayerPosition
import dto.LineUpDto
import dto.PlayerDto
import mapper.LineupMapper
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import storage.fileWriter.FileWriterImpl
import java.io.File
import java.io.FileReader

class TestLineUpRepo {

    private val lineUpRepo: LineUpRepoImpl = LineUpRepoImpl(
        mapper = LineupMapper(),
        fileWriter = FileWriterImpl(fileName = CsvFileNames.LINEUP)
    )

    @BeforeEach
    fun createLineUpCsvFile(){
        File(CsvFileNames.LINEUP).createNewFile()
    }

    @AfterEach
    fun deleteLineUpCsvFile(){
        File(CsvFileNames.LINEUP).delete()
    }

    @Test
    fun testWriteSingleLine(){
        lineUpRepo.save(dto = LineUpDto(
            PlayerDto(name="Kitlas"),
            LineUpType.SUBSTITUTE, position = PlayerPosition.DEFENDER, positionNumber = 5))

        val fileLineUpCsv = FileReader(CsvFileNames.LINEUP).readLines()

        assertTrue(fileLineUpCsv.size == 1)
    }

    @Test
    fun testWriteMultipleLines(){
        repeat(3){
            lineUpRepo.save(dto = LineUpDto(PlayerDto(name="Kitlas"), type = LineUpType.SUBSTITUTE,
                position = PlayerPosition.DEFENDER, positionNumber = 5))
        }
        val fileLineUpCsv = FileReader(CsvFileNames.LINEUP).readLines()

        assertTrue(fileLineUpCsv.size == 4)
    }
}