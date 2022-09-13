package services

import models.Response
import models.SiteScrap

interface ScrapperService {
    suspend fun scrapPage(): SiteScrap
}