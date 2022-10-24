@file:OptIn(ExperimentalCoroutinesApi::class)

package services

import constants.BrowserType
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import models.Browser
import models.BrowserSize
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ScraperServiceImplTest {
    private val testSite = "https://supersport.com/football/dstv-premiership/match/a033af5c-6d01-491c-8e7a-39df12ae2b95/"

    private lateinit var browserServiceImpl: BrowserServiceImpl
    private lateinit var scrapperServiceImpl: ScrapperServiceImpl

    @BeforeEach
    fun createBrowserService() {
        browserServiceImpl = BrowserServiceImpl(
            browser = Browser(
                browserType = BrowserType.CHROME,
                openInMaximizeView = true,
                waitDurationInSeconds = 5
            )
        )

        scrapperServiceImpl = ScrapperServiceImpl(browserService = browserServiceImpl)
    }
    @Test
    fun extractMatchDetails() = runTest {
        browserServiceImpl.openUrl(url = testSite)
        val expectedMatchDetails = "RESULT\nMaritzburg Utd\n1\nFT\n1\nAmaZulu\n28 November 2021 | 17:30\nHarry Gwala Stadium, Pietermaritzburg"

        val matchDetails = scrapperServiceImpl.extractMatchDetails()

        browserServiceImpl.closeBrowser()

        assertEquals(expectedMatchDetails,matchDetails)
    }

}