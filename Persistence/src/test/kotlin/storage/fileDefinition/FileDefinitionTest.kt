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
    }

    @Test
    fun testColumnsOfCsvFiles(){
        assertEquals("id,name", FileReader(CsvFileNames.TEAM).readLines().first())
        assertEquals("id,name", FileReader(CsvFileNames.LOCATION).readLines().first())

    }
}