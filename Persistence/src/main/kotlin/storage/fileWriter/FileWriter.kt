package storage.fileWriter

interface FileWriter {
    val fileName: String

    fun writeRow(value: String): Boolean
}