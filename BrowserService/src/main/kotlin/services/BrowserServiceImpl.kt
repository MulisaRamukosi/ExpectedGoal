package services

import constants.BrowserType
import io.github.bonigarcia.wdm.WebDriverManager
import models.Browser
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.firefox.FirefoxOptions

class BrowserServiceImpl(private val browser: Browser) : BrowserService {

    private val driver = when(browser.browserType) {
        BrowserType.CHROME -> setUpChromeDriver()
        BrowserType.FIREFOX -> setUpFireFoxDriver()
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
        if(browser.openInMaximizeView) {
            firefoxOptions.addArguments("start-maximized")
        }
        FirefoxDriver(firefoxOptions)
    }

    override suspend fun openUrl(url: String) {
        driver.get(url)
    }

    override suspend fun closeBrowser() {
        driver.close()
    }
}