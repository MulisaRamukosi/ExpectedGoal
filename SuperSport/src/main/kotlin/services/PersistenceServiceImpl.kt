package services

import models.CleanSiteScrap
import models.Response

class PersistenceServiceImpl (private val browserService: BrowserService) : PersistenceService {

    override suspend fun save(cleanSiteScrap: CleanSiteScrap) {
        TODO("Not yet implemented")
    }
}