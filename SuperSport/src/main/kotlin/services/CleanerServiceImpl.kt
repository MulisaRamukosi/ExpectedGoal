package services

import constants.LineUpType
import constants.PlayerPosition
import constants.StatType
import dto.*
import models.CleanSiteScrap
import models.SiteScrap

class CleanerServiceImpl : CleanerService {

    override fun cleanSiteScrap(siteScrap: SiteScrap): CleanSiteScrap {
        val matchDetails= siteScrap.matchDetails.split("\n")

        TODO("not yet implemented")
    }

    fun extractTeam(team:String): TeamDto {
        return TeamDto(name = team)
    }
    fun extractScore(score: Int): Int {
        return score
    }
    fun extractVenue(venue: String): LocationDto{
        return LocationDto(name=venue)
    }

    fun extractCommentary(siteScrap: SiteScrap): List<CommentaryDto>{
        val commentary=siteScrap.commentary.split("\n")

        var commentaryList= arrayListOf<CommentaryDto>()

        for (i in commentary.indices-1){
            commentaryList.add(CommentaryDto(commentary[i+1],commentary[i].filter { it.isDigit() }.toInt()))
        }

        return commentaryList.toList()
    }



    fun extractHomeLineUp(siteScrap: SiteScrap): List<LineUpDto>{

        val lineUp=siteScrap.homeLineUp.split("\n")
        var  lineUpList= arrayListOf<LineUpDto>()

        //Starting lineUp has 11 players
        lineUpList.add(LineUpDto(PlayerDto(lineUp[1]),LineUpType.MAIN,PlayerPosition.valueOf(lineUp[2].uppercase()),lineUp[0].filter{ it.isDigit()}.toInt()))
        lineUpList.add(LineUpDto(PlayerDto(lineUp[4]),LineUpType.MAIN,PlayerPosition.valueOf(lineUp[5].uppercase()),lineUp[3].filter{ it.isDigit()}.toInt()))
        lineUpList.add(LineUpDto(PlayerDto(lineUp[7]),LineUpType.MAIN,PlayerPosition.valueOf(lineUp[8].uppercase()),lineUp[6].filter{ it.isDigit()}.toInt()))
        lineUpList.add(LineUpDto(PlayerDto(lineUp[10]),LineUpType.MAIN,PlayerPosition.valueOf(lineUp[11].uppercase()),lineUp[9].filter{ it.isDigit()}.toInt()))
        lineUpList.add(LineUpDto(PlayerDto(lineUp[13]),LineUpType.MAIN,PlayerPosition.valueOf(lineUp[14].uppercase()),lineUp[12].filter{ it.isDigit()}.toInt()))
        lineUpList.add(LineUpDto(PlayerDto(lineUp[16]),LineUpType.MAIN,PlayerPosition.valueOf(lineUp[17].uppercase()),lineUp[15].filter{ it.isDigit()}.toInt()))
        lineUpList.add(LineUpDto(PlayerDto(lineUp[19]),LineUpType.MAIN,PlayerPosition.valueOf(lineUp[20].uppercase()),lineUp[18].filter{ it.isDigit()}.toInt()))
        lineUpList.add(LineUpDto(PlayerDto(lineUp[22]),LineUpType.MAIN,PlayerPosition.valueOf(lineUp[23].uppercase()),lineUp[21].filter{ it.isDigit()}.toInt()))
        lineUpList.add(LineUpDto(PlayerDto(lineUp[25]),LineUpType.MAIN,PlayerPosition.valueOf(lineUp[26].uppercase()),lineUp[24].filter{ it.isDigit()}.toInt()))
        lineUpList.add(LineUpDto(PlayerDto(lineUp[28]),LineUpType.MAIN,PlayerPosition.valueOf(lineUp[29].uppercase()),lineUp[27].filter{ it.isDigit()}.toInt()))
        lineUpList.add(LineUpDto(PlayerDto(lineUp[31]),LineUpType.MAIN,PlayerPosition.valueOf(lineUp[32].uppercase()),lineUp[30].filter{ it.isDigit()}.toInt()))

        //Number of Subs vary, May need to change this code in future

        /*for(i in 34 until lineUp.size-4){
            lineUpList.add(LineUpDto(PlayerDto(lineUp[i+1]),LineUpType.SUBSTITUTE,PlayerPosition.SUBSTITUTION,lineUp[i].filter{ it.isDigit()}.toInt()))
        }*/

        lineUpList.add(LineUpDto(PlayerDto(lineUp[35]),LineUpType.SUBSTITUTE,PlayerPosition.SUBSTITUTION,lineUp[34].filter{ it.isDigit()}.toInt()))
        lineUpList.add(LineUpDto(PlayerDto(lineUp[38]),LineUpType.SUBSTITUTE,PlayerPosition.SUBSTITUTION,lineUp[37].filter{ it.isDigit()}.toInt()))
        lineUpList.add(LineUpDto(PlayerDto(lineUp[41]),LineUpType.SUBSTITUTE,PlayerPosition.SUBSTITUTION,lineUp[40].filter{ it.isDigit()}.toInt()))
        lineUpList.add(LineUpDto(PlayerDto(lineUp[44]),LineUpType.SUBSTITUTE,PlayerPosition.SUBSTITUTION,lineUp[43].filter{ it.isDigit()}.toInt()))
        lineUpList.add(LineUpDto(PlayerDto(lineUp[47]),LineUpType.SUBSTITUTE,PlayerPosition.SUBSTITUTION,lineUp[46].filter{ it.isDigit()}.toInt()))
        lineUpList.add(LineUpDto(PlayerDto(lineUp[50]),LineUpType.SUBSTITUTE,PlayerPosition.SUBSTITUTION,lineUp[49].filter{ it.isDigit()}.toInt()))
        lineUpList.add(LineUpDto(PlayerDto(lineUp[53]),LineUpType.SUBSTITUTE,PlayerPosition.SUBSTITUTION,lineUp[52].filter{ it.isDigit()}.toInt()))
        lineUpList.add(LineUpDto(PlayerDto(lineUp[56]),LineUpType.SUBSTITUTE,PlayerPosition.SUBSTITUTION,lineUp[55].filter{ it.isDigit()}.toInt()))
        lineUpList.add(LineUpDto(PlayerDto(lineUp[59]),LineUpType.SUBSTITUTE,PlayerPosition.SUBSTITUTION,lineUp[58].filter{ it.isDigit()}.toInt()))


        return  lineUpList.toList()
    }

    fun extractAwayLineUp(siteScrap: SiteScrap): List<LineUpDto>{

        val lineUp=siteScrap.awayLineUp.split("\n")
        var  lineUpList= arrayListOf<LineUpDto>()

        //Starting lineUp has 11 players
        lineUpList.add(LineUpDto(PlayerDto(lineUp[1]),LineUpType.MAIN,PlayerPosition.valueOf(lineUp[2].uppercase()),lineUp[0].filter{ it.isDigit()}.toInt()))
        lineUpList.add(LineUpDto(PlayerDto(lineUp[4]),LineUpType.MAIN,PlayerPosition.valueOf(lineUp[5].uppercase()),lineUp[3].filter{ it.isDigit()}.toInt()))
        lineUpList.add(LineUpDto(PlayerDto(lineUp[7]),LineUpType.MAIN,PlayerPosition.valueOf(lineUp[8].uppercase()),lineUp[6].filter{ it.isDigit()}.toInt()))
        lineUpList.add(LineUpDto(PlayerDto(lineUp[10]),LineUpType.MAIN,PlayerPosition.valueOf(lineUp[11].uppercase()),lineUp[9].filter{ it.isDigit()}.toInt()))
        lineUpList.add(LineUpDto(PlayerDto(lineUp[13]),LineUpType.MAIN,PlayerPosition.valueOf(lineUp[14].uppercase()),lineUp[12].filter{ it.isDigit()}.toInt()))
        lineUpList.add(LineUpDto(PlayerDto(lineUp[16]),LineUpType.MAIN,PlayerPosition.valueOf(lineUp[17].uppercase()),lineUp[15].filter{ it.isDigit()}.toInt()))
        lineUpList.add(LineUpDto(PlayerDto(lineUp[19]),LineUpType.MAIN,PlayerPosition.valueOf(lineUp[20].uppercase()),lineUp[18].filter{ it.isDigit()}.toInt()))
        lineUpList.add(LineUpDto(PlayerDto(lineUp[22]),LineUpType.MAIN,PlayerPosition.valueOf(lineUp[23].uppercase()),lineUp[21].filter{ it.isDigit()}.toInt()))
        lineUpList.add(LineUpDto(PlayerDto(lineUp[25]),LineUpType.MAIN,PlayerPosition.valueOf(lineUp[26].uppercase()),lineUp[24].filter{ it.isDigit()}.toInt()))
        lineUpList.add(LineUpDto(PlayerDto(lineUp[28]),LineUpType.MAIN,PlayerPosition.valueOf(lineUp[29].uppercase()),lineUp[27].filter{ it.isDigit()}.toInt()))
        lineUpList.add(LineUpDto(PlayerDto(lineUp[31]),LineUpType.MAIN,PlayerPosition.valueOf(lineUp[32].uppercase()),lineUp[30].filter{ it.isDigit()}.toInt()))


        //Number of Subs vary, May need to change this code in future

        /*for(i in 34 until lineUp.size){
            lineUpList.add(LineUpDto(PlayerDto(lineUp[i+1]),LineUpType.SUBSTITUTE,PlayerPosition.SUBSTITUTION,lineUp[i].filter{ it.isDigit()}.toInt()))
        }
        */

        lineUpList.add(LineUpDto(PlayerDto(lineUp[35]),LineUpType.SUBSTITUTE,PlayerPosition.SUBSTITUTION,lineUp[34].filter{ it.isDigit()}.toInt()))
        lineUpList.add(LineUpDto(PlayerDto(lineUp[38]),LineUpType.SUBSTITUTE,PlayerPosition.SUBSTITUTION,lineUp[37].filter{ it.isDigit()}.toInt()))
        lineUpList.add(LineUpDto(PlayerDto(lineUp[41]),LineUpType.SUBSTITUTE,PlayerPosition.SUBSTITUTION,lineUp[40].filter{ it.isDigit()}.toInt()))
        lineUpList.add(LineUpDto(PlayerDto(lineUp[44]),LineUpType.SUBSTITUTE,PlayerPosition.SUBSTITUTION,lineUp[43].filter{ it.isDigit()}.toInt()))
        lineUpList.add(LineUpDto(PlayerDto(lineUp[47]),LineUpType.SUBSTITUTE,PlayerPosition.SUBSTITUTION,lineUp[46].filter{ it.isDigit()}.toInt()))
        lineUpList.add(LineUpDto(PlayerDto(lineUp[50]),LineUpType.SUBSTITUTE,PlayerPosition.SUBSTITUTION,lineUp[49].filter{ it.isDigit()}.toInt()))
        lineUpList.add(LineUpDto(PlayerDto(lineUp[53]),LineUpType.SUBSTITUTE,PlayerPosition.SUBSTITUTION,lineUp[52].filter{ it.isDigit()}.toInt()))
        lineUpList.add(LineUpDto(PlayerDto(lineUp[56]),LineUpType.SUBSTITUTE,PlayerPosition.SUBSTITUTION,lineUp[55].filter{ it.isDigit()}.toInt()))
        lineUpList.add(LineUpDto(PlayerDto(lineUp[59]),LineUpType.SUBSTITUTE,PlayerPosition.SUBSTITUTION,lineUp[58].filter{ it.isDigit()}.toInt()))

        return  lineUpList.toList()
    }


    fun extractStats(siteScrap: SiteScrap): List<StatDto>{
        val stats=siteScrap.stats.split("\n")
        var statsList= arrayListOf<StatDto>()

        statsList.add(StatDto(stats[2].filter { it.isDigit() }.toInt(),stats[4].filter { it.isDigit() }.toInt(),StatType.SHOTS))
        statsList.add(StatDto(stats[5].filter { it.isDigit() }.toInt(),stats[7].filter { it.isDigit() }.toInt(),StatType.SHOTS_ON_TARGET))
        statsList.add(StatDto(stats[8].filter { it.isDigit() }.toInt(),stats[10].filter { it.isDigit() }.toInt(),StatType.CORNERS))
        statsList.add(StatDto(stats[11].filter { it.isDigit() }.toInt(),stats[13].filter { it.isDigit() }.toInt(),StatType.OFFSIDES))
        statsList.add(StatDto(stats[14].filter { it.isDigit() }.toInt(),stats[16].filter { it.isDigit() }.toInt(),StatType.FOULS))
        statsList.add(StatDto(stats[17].filter { it.isDigit() }.toInt(),stats[19].filter { it.isDigit() }.toInt(),StatType.YELLOW_CARDS))
        statsList.add(StatDto(stats[20].filter { it.isDigit() }.toInt(),stats[22].filter { it.isDigit() }.toInt(),StatType.RED_CARDS))

        return statsList.toList()
    }
}