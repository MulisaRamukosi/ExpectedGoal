package storage.fileDefinition

import constants.CsvFileNames
import java.io.File

class FileDefinition {
    fun setupCsvFiles(){
        setupTeamCsvFile()
        setupLocationCsvFile()
        setupCommentaryCsvFile()
        setupMatchCsvFile()
        setupLineupCsvFile()
        setupEventCsvFile()
        setupStatCsvFile()
        setupPlayerCsvFile()
    }
    private fun setupTeamCsvFile(){
        generateCsvFile(fileName = CsvFileNames.TEAM, columns = "id,name\n")
    }

    private fun setupLocationCsvFile(){
        generateCsvFile(fileName = CsvFileNames.LOCATION, columns = "id,name\n")
    }

    private fun setupCommentaryCsvFile(){
        generateCsvFile(fileName = CsvFileNames.COMMENTARY, columns = "id,matchId,comment,minute\n")
    }

    private fun setupMatchCsvFile(){
        generateCsvFile(fileName = CsvFileNames.MATCH, columns = "id,locationId,homeScore,awayScore,date\n")
    }

    private fun setupLineupCsvFile(){
        generateCsvFile(fileName = CsvFileNames.LINEUP, columns = "id,matchId,teamId,playerId,type,position,positionNumber\n")
    }

    private fun setupEventCsvFile(){
        generateCsvFile(fileName = CsvFileNames.EVENT, columns = "id,matchId,teamId,playerId,minute,type\n")
    }

    private fun setupStatCsvFile(){
        generateCsvFile(fileName = CsvFileNames.STAT, columns = "id,matchId,home,away,type\n")
    }

    private fun setupPlayerCsvFile(){
        generateCsvFile(fileName = CsvFileNames.PLAYER, columns = "id,name\n")
    }

    private fun generateCsvFile(fileName: String, columns String){
        val file = File(filename)
        if (file.exists().not() && file.createNewFile()){
            file.WriteText(text = columns)
        }
    }
}