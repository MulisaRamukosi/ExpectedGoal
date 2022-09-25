package storage.fileDefinition

import constants.CsvFileNames
import java.io.File

class FileDefinition {
    fun setupCsvFiles(){
        setupTeamCsvFile()
        setupLocationCsvFile()
    }

    private fun setupTeamCsvFile(){
        generateCsvFile(fileName = CsvFileNames.TEAM, columns = "id,name\n")
    }

    private fun setupLocationCsvFile(){
        generateCsvFile(fileName = CsvFileNames.LOCATION, columns = "id,name\n")
    }

    private fun generateCsvFile(fileName: String, columns String){
        val file = File(filename)
        if (file.exists().not() && file.createNewFile()){
            file.WriteText(text = columns)
        }
    }
}