package services

import models.Browser
import models.NavigationState
import models.Response

interface BrowserService {
    suspend fun openUrl(url: String)
    suspend fun closeBrowser()
}