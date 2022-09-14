import constants.BrowserType
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import models.Browser
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import services.BrowserServiceImpl

@OptIn(ExperimentalCoroutinesApi::class)
class BrowserServiceImplTests {
    private val testSite = "https://webscraper.io/test-sites/e-commerce/allinone"

    private lateinit var browserServiceImpl: BrowserServiceImpl

    @BeforeEach
    fun createBrowserService() {
        browserServiceImpl = BrowserServiceImpl(
            browser = Browser(
                browserType = BrowserType.CHROME,
                openInMaximizeView = true,
                waitDurationInSeconds = 5
            )
        )
    }

    @Test
    fun openUrl() = runTest {
        browserServiceImpl.openUrl(url = testSite)
        assertTrue(browserServiceImpl.getCurrentUrl().contains("webscraper.io"))
        browserServiceImpl.closeBrowser()
    }

    @Test
    fun closeBrowser() = runTest {
        browserServiceImpl.openUrl(url = testSite)
        browserServiceImpl.closeBrowser()
        assertTrue(browserServiceImpl.driver.toString().contains("null"))
    }

    @Test
    fun findElementById_ElementIdExist_ReturnsBrowserService() = runTest {
        browserServiceImpl.openUrl(url = testSite)
        val ref = browserServiceImpl.findElementById(id = "cookieBanner")
        browserServiceImpl.closeBrowser()
        assertNotNull(ref)
    }

    @Test
    fun findElementById_ElementIdDoesNotExist_ThrowsException() = runTest {
        browserServiceImpl.openUrl(url = testSite)
        try {
            browserServiceImpl.findElementById(id = "dummy")
        } catch (ex: Exception) {
            browserServiceImpl.closeBrowser()
            assertTrue(ex.message!!.contains("Expected condition failed: waiting for presence of element located by: By.id: dummy"))
        }
    }

    @Test
    fun findElementsByClass_ElementsClassExist_ReturnsBrowserService() = runTest {
        browserServiceImpl.openUrl(url = testSite)
        val ref = browserServiceImpl.findElementByClass(className = "col-sm-4")
        browserServiceImpl.closeBrowser()
        assertNotNull(ref)
    }

    @Test
    fun findElementsByClass_ElementsClassDoesNotExist_ThrowsException() = runTest {
        browserServiceImpl.openUrl(url = testSite)
        try {
            browserServiceImpl.findElementByClass(className = "dummy")
        } catch (ex: Exception) {
            browserServiceImpl.closeBrowser()
            assertTrue(ex.message!!.contains("Expected condition failed: waiting for presence of any elements located by By.className: dummy"))
        }
    }

    @Test
    fun findElementByXPath_ElementPathExist_ReturnsBrowserService() = runTest {
        browserServiceImpl.openUrl(url = testSite)
        val ref = browserServiceImpl.findElementByXPath(path = "/html/body/header/div/div[1]/div/a")
        browserServiceImpl.closeBrowser()
        assertNotNull(ref)
    }

    @Test
    fun findElementByXPath_ElementPathDoesNotExist_ThrowsException() = runTest {
        browserServiceImpl.openUrl(url = testSite)
        try {
            browserServiceImpl.findElementByXPath(path = "dummy")
        } catch (ex: Exception) {
            browserServiceImpl.closeBrowser()
            assertTrue(ex.message!!.contains("Expected condition failed: waiting for presence of element located by: By.xpath: dummy"))
        }
    }

    @Test
    fun findElementsByTag_ElementsByTagExist_ReturnsBrowserService() = runTest {
        browserServiceImpl.openUrl(url = testSite)
        val ref = browserServiceImpl.findElementsByTag(tag = "p")
        browserServiceImpl.closeBrowser()
        assertNotNull(ref)
    }

    @Test
    fun findElementsByTag_ElementsByTagDoNotExist_ThrowsException() = runTest {
        browserServiceImpl.openUrl(url = testSite)
        try {
            browserServiceImpl.findElementsByTag(tag = "dummy")
        } catch (ex: Exception) {
            browserServiceImpl.closeBrowser()
            assertTrue(ex.message!!.contains("Expected condition failed: waiting for presence of any elements located by By.tagName: dummy"))
        }
    }

    @Test
    fun elementsCount_ElementsExist_ReturnsCount() = runTest {
        browserServiceImpl.openUrl(url = testSite)
        val ref = browserServiceImpl.findElementByClass(className = "col-sm-4")
        val count = ref.elementsCount()

        browserServiceImpl.closeBrowser()
        assertEquals(3, count)
    }

    @Test
    fun clickElement_ElementExist_NavigatesToNewPage() = runTest {
        browserServiceImpl.openUrl(url = testSite)
        val ref = browserServiceImpl.findElementByXPath(path = "//*[@id=\"side-menu\"]/li[2]/a")
        ref.clickElement()
        val newUrl = browserServiceImpl.getCurrentUrl()

        browserServiceImpl.closeBrowser()
        assertTrue(newUrl.contains("allinone/computers"))
    }

    @Test
    fun clickElementByIndex_ElementsExist_NavigatesToNewPage() = runTest {
        browserServiceImpl.openUrl(url = testSite)
        val ref = browserServiceImpl.findElementByClass(className = "category-link")
        ref.clickElement(i = 1)
        val newUrl = browserServiceImpl.getCurrentUrl()

        browserServiceImpl.closeBrowser()
        assertTrue(newUrl.contains("allinone/phones"))
    }

    @Test
    fun extractInnerText_ElementExist_ReturnsInnerText() = runTest {
        browserServiceImpl.openUrl(url = testSite)
        val ref = browserServiceImpl.findElementByXPath(path = "//*[@id=\"side-menu\"]/li[2]/a")
        val innerText = ref.extractInnerText()
        browserServiceImpl.closeBrowser()
        assertTrue(innerText.contains("Computers"))
    }

    @Test
    fun extractInnerTextByIndex_ElementsExist_ReturnsInnerText() = runTest {
        browserServiceImpl.openUrl(url = testSite)
        val ref = browserServiceImpl.findElementByClass(className = "category-link")
        val innerText = ref.extractInnerText(i = 1)

        browserServiceImpl.closeBrowser()
        assertTrue(innerText.contains("Phones"))
    }

    @Test
    fun extractInnerHtml_ElementExist_ReturnsInnerHtml() = runTest {
        browserServiceImpl.openUrl(url = testSite)
        val ref = browserServiceImpl.findElementById(id = "side-menu")
        val innerHtml = ref.extractInnerHtml()
        browserServiceImpl.closeBrowser()
        assertTrue(innerHtml.contains("<li>"))
    }

    @Test
    fun extractInnerHtmlByIndex_ElementExist_ReturnsInnerHtml() = runTest {
        browserServiceImpl.openUrl(url = testSite)
        val ref = browserServiceImpl.findElementByClass(className = "col-sm-4")
        val innerHtml = ref.extractInnerHtml(i = 1)
        browserServiceImpl.closeBrowser()
        assertTrue(innerHtml.contains("<div class=\"thumbnail\">"))
    }
}