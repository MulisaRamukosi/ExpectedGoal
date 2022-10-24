package storage.fileWriter

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File
import java.io.FileReader

internal class FileWriterTest {

    @Test
    fun testWriteRow(){
        val fileName = "dummy.csv"
        val file = File(fileName)
        file.createNewFile()

        FileWriterImpl(fileName = fileName).writeRow("something")

        assertEquals("something", FileReader(fileName).readLines().first())

        file.delete()
    }

}