package services

import models.SiteScrap

class ScrapperServiceImpl (private val browserService: BrowserService): ScrapperService {

    override suspend fun scrapPage(): SiteScrap {
        val matchDetails = extractMatchDetails()

        //click events
        clickTab(8,"menu-item")
        val events = extractEvents()

        //Click stats
        clickTab(9,"menu-item")
        val stats = extractStats()

        // Click LineUp
        clickTab(10,"menu-item")
        val homelineup = extractHomeLineup()
        val awaylineup = extractAwayLineup()


        return SiteScrap(
            matchDetails = matchDetails,
            events = events,
            stats = stats,
            homeLineUp = homelineup,
            awayLineUp = awaylineup,
            commentary = ""
        )
    }
     suspend fun extractMatchDetails():String {
        return browserService.findElementByClass(className = "match-details-header").extractInnerText(i=0)
    }

    suspend fun extractEvents():String{
        return browserService.findElementByClass(className = "events-container").extractInnerText(i=0)
    }

    suspend fun extractStats():String{
        return browserService.findElementByClass(className = "component-wrapper").extractInnerText(i=0)
    }

    suspend fun extractHomeLineup():String{
        return browserService.findElementByClass(className = "line-up").extractInnerText(i=0)//Have same name
    }

    suspend fun extractAwayLineup():String{
        return browserService.findElementByClass(className = "line-up").extractInnerText(i=2)//Have same name
    }

    private suspend fun clickTab(pos: Int, ClassName: String){
        browserService.findElementByClass(className = ClassName).clickElement(i=pos)
    }
}