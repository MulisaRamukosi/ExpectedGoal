package services

import models.CleanSiteScrap
import models.Response
import models.SiteScrap

interface CleanerService {
    fun cleanSiteScrap(siteScrap: SiteScrap) : Response<CleanSiteScrap>
}