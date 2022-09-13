package services

import models.SiteScrap

class ScrapperServiceImpl (private val browserService: BrowserService): ScrapperService {

    override suspend fun scrapPage(): SiteScrap {
        TODO("Not yet implemented")
    }

}