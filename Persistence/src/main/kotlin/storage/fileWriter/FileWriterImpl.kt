package storage.fileWriter

import java.io.FileOutputStream
import java.lang.Exception

class FileWriterImpl(override val fileName: String) : FileWriter {

    override fun writeRow(value: String): Boolean {
        return try {
            FileOutputStream(fileName, true).apply {
                val writer = bufferedWriter()
                writer.write(value)
                writer.newLine()
                writer.flush()
            }
            true
        } catch (ex: Exception){
            println(ex.message)
            false
        }
    }
}





















