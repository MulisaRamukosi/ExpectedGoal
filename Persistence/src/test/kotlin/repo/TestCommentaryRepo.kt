package repo

import constants.CsvFileNames
import dto.CommentaryDto
import mapper.CommentaryMapper
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import storage.fileWriter.FileWriterImpl
import java.io.File
import java.io.FileReader

class TestCommentaryRepo {

    private val commentaryRepo: CommentaryRepoImpl = CommentaryRepoImpl(
        mapper = CommentaryMapper(),
        fileWriter = FileWriterImpl(fileName = CsvFileNames.COMMENTARY)
    )

    @BeforeEach
    fun createCommentaryCsvFile(){
        File(CsvFileNames.COMMENTARY).createNewFile()
    }

    @AfterEach
    fun deleteCommentaryCsvFile(){
        File(CsvFileNames.COMMENTARY).delete()
    }

    @Test
    fun testWriteSingleLine(){
        commentaryRepo.save(dto = CommentaryDto("test",1))

        val fileCommentaryCsv = FileReader(CsvFileNames.COMMENTARY).readLines()

        assertTrue(fileCommentaryCsv.size == 1)
    }

    @Test
    fun testWriteMultipleLines(){
        repeat(3){
            commentaryRepo.save(dto = CommentaryDto("test$it",1))
        }
        val fileCommentaryCsv = FileReader(CsvFileNames.COMMENTARY).readLines()

        assertTrue(fileCommentaryCsv.size == 4)
    }
}