import constants.BrowserType
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import models.Browser
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import services.BrowserServiceImpl

@OptIn(ExperimentalCoroutinesApi::class)
class BrowserServiceImplTests {
    private lateinit var browserServiceImpl: BrowserServiceImpl

    @BeforeEach
    fun createBrowserService(){
        browserServiceImpl = BrowserServiceImpl(
            browser = Browser(
                browserType = BrowserType.CHROME,
                openInMaximizeView = true
            )
        )
    }

    @Test
    fun openUrl() = runTest{
        browserServiceImpl.openUrl("https://www.google.com/")
        assertTrue(browserServiceImpl.driver.currentUrl.contains("www.google.com"))
        browserServiceImpl.closeBrowser()
    }

    @Test
    fun closeBrowser() = runTest {
        browserServiceImpl.openUrl("https://www.google.com/")
        browserServiceImpl.closeBrowser()
        assertTrue(browserServiceImpl.driver.toString().contains("null"))
    }
}