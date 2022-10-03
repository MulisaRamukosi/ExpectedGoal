package services

interface BrowserService {
    suspend fun openUrl(url: String)
    suspend fun maximizeBrowser()
    suspend fun closeBrowser()
    suspend fun findElementById(id: String): BrowserService
    suspend fun findElementByClass(className: String): BrowserService
    suspend fun findElementByXPath(path: String): BrowserService
    suspend fun findElementsByTag(tag: String): BrowserService
    suspend fun elementsCount(): Int
    suspend fun clickElement(i: Int? = null)
    suspend fun extractInnerText(i: Int? = null): String
    suspend fun extractInnerHtml(i: Int? = null): String
    suspend fun getCurrentUrl(): String
    suspend fun navigateBack()
    suspend fun copy(): BrowserService
}