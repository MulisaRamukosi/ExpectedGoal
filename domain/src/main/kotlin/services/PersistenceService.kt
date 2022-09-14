package services

import models.CleanSiteScrap
import models.Response

interface PersistenceService {

    suspend fun save(cleanSiteScrap: CleanSiteScrap)

}