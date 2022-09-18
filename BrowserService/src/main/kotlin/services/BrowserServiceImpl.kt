package services

import constants.BrowserType
import io.github.bonigarcia.wdm.WebDriverManager
import models.Browser
import org.openqa.selenium.By
import org.openqa.selenium.Dimension
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.firefox.FirefoxOptions
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration

class BrowserServiceImpl(private val browser: Browser) : BrowserService {

    private var webElement: WebElement? = null
    private var webElements: MutableList<WebElement>? = null

    private var driver: WebDriver

    init {
        driver = when (browser.browserType) {
            BrowserType.CHROME -> setUpChromeDriver()
            BrowserType.FIREFOX -> setUpFireFoxDriver()
        }

        browser.browserSize?.let {
            driver.manage().window().size = Dimension(it.width, it.height)
        }
    }

    private fun setUpChromeDriver(): WebDriver = run {
        WebDriverManager.chromedriver().setup()
        val chromeOptions = ChromeOptions()
        if (browser.openInMaximizeView) {
            chromeOptions.addArguments("start-maximized")
        }
        ChromeDriver(chromeOptions)
    }

    private fun setUpFireFoxDriver(): WebDriver = run {
        WebDriverManager.firefoxdriver().setup()
        val firefoxOptions = FirefoxOptions()
        if (browser.openInMaximizeView) {
            firefoxOptions.addArguments("start-maximized")
        }
        FirefoxDriver(firefoxOptions)
    }

    override suspend fun openUrl(url: String) {
        driver.get(url)
    }

    override suspend fun closeBrowser() {
        driver.quit()
    }

    override suspend fun findElementById(id: String): BrowserService {
        webElement = wait().until(ExpectedConditions.presenceOfElementLocated(By.id(id)))
        return this
    }

    override suspend fun findElementByClass(className: String): BrowserService {
        webElements = wait().until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className(className)))
        return this
    }

    override suspend fun findElementByXPath(path: String): BrowserService {
        webElement = wait().until(ExpectedConditions.presenceOfElementLocated(By.xpath(path)))
        return this
    }

    override suspend fun findElementsByTag(tag: String): BrowserService {
        webElements = wait().until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName(tag)))
        return this
    }

    override suspend fun elementsCount(): Int {
        return webElements!!.size
    }

    private fun wait(): WebDriverWait {
        return WebDriverWait(driver, Duration.ofSeconds(browser.waitDurationInSeconds))
    }

    override suspend fun clickElement(i: Int?) {
        i?.let {
            webElements!![it].click()
            return
        }
        webElement!!.click()
    }

    override suspend fun extractInnerText(i: Int?): String {
        return extractDataFromElement(attr = "innerText", i = i)
    }

    override suspend fun extractInnerHtml(i: Int?): String {
        return extractDataFromElement(attr = "innerHTML", i = i)
    }

    override suspend fun getCurrentUrl(): String {
        return driver.currentUrl
    }

    private fun extractDataFromElement(attr: String, i: Int?) : String{
        i?.let {
            val text = webElements!![it].getAttribute(attr)
            return text ?: ""
        }
        return webElement!!.getAttribute(attr)
    }

}