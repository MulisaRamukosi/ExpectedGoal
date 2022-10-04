package services

import models.SiteScrap

class ScrapperServiceImpl (private val browserService: BrowserService): ScrapperService {

    override suspend fun scrapPage(): SiteScrap {
        val matdet = browserService.findElementByClass(className = "match-details-header").extractInnerText(i=0)
        val events = browserService.findElementByClass(className = "events-container").extractInnerText(i=0)

        //Click stats

        val stats = browserService.findElementByClass(className = "component-wrapper").extractInnerText(i=0)
        // Click LineUp

        val homelineup = browserService.findElementByClass(className = "line-up").extractInnerText(i=0)//Have same name
        val awaylineup = browserService.findElementByClass(className = "line-up").extractInnerText(i=0)//Have same name

        return SiteScrap(
            matchDetails = matdet,
            events = events,
            stats = stats,
            homeLineUp = homelineup,
            awayLineUp = awaylineup,
            commentary = ""
        )
    }

    private suspend fun clickTab(pos: Int, ClassName: String){
        browserService.findElementByClass(className = ClassName).clickElement(i=pos)
    }
}