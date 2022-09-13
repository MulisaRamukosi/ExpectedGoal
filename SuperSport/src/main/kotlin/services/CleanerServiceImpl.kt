package services

import models.CleanSiteScrap
import models.SiteScrap

class CleanerServiceImpl (private val browserService: BrowserService) : CleanerService {

    override fun cleanSiteScrap(siteScrap: SiteScrap): CleanSiteScrap {
        TODO("Not yet implemented")
    }
}