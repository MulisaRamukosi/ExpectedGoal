package repo

import constants.*
import dto.*
import mapper.MatchMapper
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import storage.fileWriter.FileWriterImpl
import java.io.File
import java.io.FileReader
import java.util.*

class TestMatchRepo {

    private val matchRepo: MatchRepoImpl = MatchRepoImpl(
        mapper = MatchMapper(),
        fileWriter = FileWriterImpl(fileName = CsvFileNames.MATCH)
    )

    @BeforeEach
    fun createMatchCsvFile(){
        File(CsvFileNames.LOCATION).createNewFile()
    }

    @AfterEach
    fun deleteMatchCsvFile(){
        File(CsvFileNames.LOCATION).delete()
    }

    @Test
    fun testWriteSingleLine(){
        matchRepo.save(dto = MatchDto(date =Date(),location = LocationDto(name="home"), homeTeam = TeamDto(name="Team"),
            awayTeam = TeamDto(name="AwayTeam"), homeScore = 1, awayScore = 2,
            stats = listOf(StatDto(home = 1,away = 2, type = StatType.SHOTS)), awayEvents = listOf(
                EventDto(PlayerDto(name ="Kitlas",), minute = 5, EventType.OWN_GOAL) ),
                awayLineUp = listOf(LineUpDto(PlayerDto(name="Kitlas"),type= LineUpType.SUBSTITUTE,
                    position = PlayerPosition.DEFENDER, positionNumber = 5))
            , commentary = listOf(CommentaryDto(comment = "", minute = 1)), homeEvents = listOf(
                EventDto(PlayerDto(name ="Kitlas",), minute = 5, EventType.OWN_GOAL) ),
            homeLineUp =listOf(LineUpDto(PlayerDto(name="Kitlas"),type= LineUpType.SUBSTITUTE,
                position = PlayerPosition.DEFENDER, positionNumber = 5))

        )
        )

        val fileMatchCsv = FileReader(CsvFileNames.MATCH).readLines()

        assertTrue(fileMatchCsv.size == 1)
    }

    @Test
    fun testWriteMultipleLines(){
        repeat(3){
            matchRepo.save(dto = MatchDto(Date(),LocationDto(name="home"),TeamDto(name="Team"),
                awayTeam = TeamDto(name="AwayTeam"),1,2,listOf(StatDto(home = 1,away = 2, type = StatType.SHOTS))
            ,listOf(CommentaryDto(comment = "", minute = 1)),listOf(LineUpDto(PlayerDto(name="Kitlas"),LineUpType.SUBSTITUTE
                    , position = PlayerPosition.DEFENDER,5))
            ,listOf(LineUpDto(PlayerDto(name="Kitlas"),LineUpType.SUBSTITUTE, position = PlayerPosition.DEFENDER,5)),
                listOf(
                    EventDto(PlayerDto(name ="Kitlas",), minute = 5, EventType.OWN_GOAL) ),
            listOf(EventDto(PlayerDto(name ="Kitlas",), minute = 5, EventType.OWN_GOAL) )))
        }
        val fileMatchCsv = FileReader(CsvFileNames.MATCH).readLines()

        assertTrue(fileMatchCsv.size == 4)
    }
}
