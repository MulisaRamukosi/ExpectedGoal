package storage.fileDefinition

import constants.CsvFileNames
import java.io.File

class FileDefinition {
    fun setupCsvFiles(){
        setupTeamCsvFile()
    }

    private fun setupTeamCsvFile(){
        val file = File(CsvFileNames.TEAM)
        if(file.exists().not() && file.createNewFile()){
            file.writeText(text = "id,name\n")
        }
    }
}