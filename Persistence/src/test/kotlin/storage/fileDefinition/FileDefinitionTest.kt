package storage.fileDefinition

import constants.CsvFileNames
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.File
import java.io.FileReader

internal class FileDefinitionTest {

    @BeforeEach
    fun createCsvFiles() {
        FileDefinition().setupCsvFiles()
    }

    @AfterEach
    fun cleanUp(){
        FileDefinition().deleteAllCsvFiles()
    }

    @Test
    fun testSetupCsvFiles() {
        assertTrue(File(CsvFileNames.TEAM).exists())
        assertTrue(File(CsvFileNames.LOCATION).exists())
        assertTrue(File(CsvFileNames.COMMENTARY).exists())
        assertTrue(File(CsvFileNames.MATCH).exists())
        assertTrue(File(CsvFileNames.LINEUP).exists())
        assertTrue(File(CsvFileNames.EVENT).exists())
        assertTrue(File(CsvFileNames.STAT).exists())
        assertTrue(File(CsvFileNames.PLAYER).exists())
    }

    @Test
    fun testColumnsOfCsvFiles(){
        assertEquals("id,name", FileReader(CsvFileNames.TEAM).readLines().first())
        assertEquals("id,name", FileReader(CsvFileNames.LOCATION).readLines().first())
        assertEquals("id,matchId,comment,minute", FileReader(CsvFileNames.COMMENTARY).readLines().first())
        assertEquals("id,locationId,homeScore,awayScore,date", FileReader(CsvFileNames.MATCH).readLines().first())
        assertEquals("id,matchId,teamId,playerId,type,position,positionNumber", FileReader(CsvFileNames.LINEUP).readLines().first())
        assertEquals("id,matchId,teamId,playerId,minute,type", FileReader(CsvFileNames.EVENT).readLines().first())
        assertEquals("id,matchId,home,away,type", FileReader(CsvFileNames.STAT).readLines().first())
        assertEquals("id,name", FileReader(CsvFileNames.PLAYER).readLines().first())

    }
}