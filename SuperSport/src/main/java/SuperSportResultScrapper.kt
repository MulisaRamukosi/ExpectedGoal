import constants.Constants
import models.CollectionDescription
import services.BrowserService
import services.WebsiteResultScrapper

class SuperSportResultScrapper (private val browserService: BrowserService) : WebsiteResultScrapper {

    override suspend fun scrap(collectionDescription: CollectionDescription) {
        browserService.openUrl(Constants.superSportUrl)
    }
}