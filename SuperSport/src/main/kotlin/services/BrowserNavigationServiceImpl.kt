package services

import constants.Month
import models.CollectionDescription
import models.NavigationState
import models.TargetDate
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import java.util.*
import kotlin.collections.ArrayList

class BrowserNavigationServiceImpl(private val browserService: BrowserService) : BrowserNavigationService {

    private var matchUrls: ArrayList<String> = arrayListOf()
    private lateinit var navigationState: NavigationState
    private var endIndexExist: Boolean = false
    private var indexTracker: Int = 0

    override suspend fun navigateToState(navigationState: NavigationState) {
        browserService.maximizeBrowser()
        this.navigationState = navigationState
        resetValues()

        if (navigationState.url.isNullOrBlank().not()) {
            browserService.openUrl(url = navigationState.url!!)
        } else {
            navigateToTargetDate(navigationState = navigationState)
            closeCookiesBannerIfAvailable()
            expandResults()
            try {
                val matchDays = findMatchDays()
                val rangeIndexes = findStartAndEndIndex(navigationState = navigationState, matchDays = matchDays)
                extractMatchLinks(rangeIndex = rangeIndexes, matchDays = matchDays)
                loadStateForCurrentIndex()
            }
            catch (ex: Exception) {
                ex.printStackTrace()
                val nextDate = loadNextNavigationState()
                if (nextDate == null) throw Exception("there's no upcoming an event to collect from")
                else navigateToState(nextDate)
            }

        }
    }

    private fun resetValues() {
        this.endIndexExist = false
        this.indexTracker = 0
        this.matchUrls = arrayListOf()
    }

    private suspend fun closeCookiesBannerIfAvailable(){
        try {
            browserService.findElementById(id = "onetrust-close-btn-container").clickElement()
        }
        catch (ex: Exception){
            ex.printStackTrace()
        }
    }
    private suspend fun expandResults() {
        try {
            browserService.findElementByClass("view-more-link").clickElement(i = 0)
            expandResults()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    private fun extractMatchLinks(rangeIndex: Pair<Int, Int>, matchDays: Elements) {
        for (i in rangeIndex.first downTo 0) {
            val matchDayNode = matchDays[i]
            val matches = matchDayNode.getElementsByClass("listing-item")

            matches.forEach { match ->
                val matchLinkRef = match.getElementsByTag("a")[0]
                val matchResultLink = "https://supersport.com${matchLinkRef.attr("href")}"
                matchUrls.add(matchResultLink)
            }

            if (i == rangeIndex.second) break
        }
    }

    override suspend fun loadNextState(): NavigationState? {
        if (indexTracker < matchUrls.size) {
            loadStateForCurrentIndex()
            return NavigationState(
                collectionDescription = CollectionDescription(
                    startDate = extractDate(matchUrl = matchUrls[indexTracker]),
                    endDate = navigationState.collectionDescription.endDate
                ),
                url = matchUrls[indexTracker]
            )
        }

        return loadNextNavigationState()
    }

    private fun loadNextNavigationState(): NavigationState? {
        val startDate = navigationState.collectionDescription.startDate
        val endDate = navigationState.collectionDescription.endDate
        val newDate = getNextDayAfterDate(date = startDate)

        if (endDate != null
            && ((newDate.month > endDate.month)
                    || (newDate.month == endDate.month && newDate.day > endDate.day))
        ) {
            return null
        } else {
            val calendar: Calendar = Calendar.getInstance()
            if (newDate.month.ordinal > calendar.get(Calendar.MONTH)) return null
        }

        return NavigationState(
            collectionDescription = CollectionDescription(startDate = newDate, endDate = endDate),
            url = null
        )
    }

    private fun getNextDayAfterDate(date: TargetDate): TargetDate {
        val calendar: Calendar = Calendar.getInstance()

        calendar.set(Calendar.DAY_OF_MONTH, date.day)
        calendar.set(Calendar.MONTH, date.month.ordinal)
        calendar.set(Calendar.YEAR, date.year)
        calendar.add(Calendar.DAY_OF_MONTH, 1)

        return TargetDate(
            day = calendar.get(Calendar.DAY_OF_MONTH),
            month = Month.values()[calendar.get(Calendar.MONTH)],
            year = calendar.get(Calendar.YEAR)
        )
    }

    private fun extractDate(matchUrl: String): TargetDate {
        val doc: Document = Jsoup.connect(matchUrl).get()
        val dateElement: Element = doc.getElementsByClass("has-text-centered match-timestamp").first()
            ?: throw Exception("date element was not found")
        val dateText: String = dateElement.text().replace("'", "") // "19 October 2021 | 17:30"
        val datePieces: List<String> = dateText.split(" ")
        val day: Int = datePieces[0].toInt()
        val month: Month = Month.valueOf(value = datePieces[1].trim())
        val year: Int = datePieces[2].toInt()

        return TargetDate(day = day, month = month, year = year)
    }

    private suspend fun loadStateForCurrentIndex() {
        browserService.openUrl(url = matchUrls[indexTracker])
        indexTracker++
    }

    private suspend fun findMatchDays(): Elements {
        val matchDaysSection = browserService.findElementByClass(className = "week-panel").extractInnerHtml(i = 0)
        val doc = Jsoup.parse(matchDaysSection)
        return doc.select("ul.panel-body > div")
    }

    private fun findStartAndEndIndex(
        matchDays: Elements,
        navigationState: NavigationState
    ): Pair<Int, Int> {
        val targetStartMonth = navigationState.collectionDescription.startDate.month.name.lowercase()
        val targetStartDay = navigationState.collectionDescription.startDate.day
        val targetEndMonth = navigationState.collectionDescription.endDate?.month?.name?.lowercase()
        val targetEndDay = navigationState.collectionDescription.endDate?.day
        var startIndex = matchDays.size - 1
        var endIndex = -1

        for (i in matchDays.indices) {
            val matchDateSection = matchDays[i]
            val matchDateTimeDivs = matchDateSection.getElementsByClass("card-date")
            val matchDateTime = matchDateTimeDivs[0].text().lowercase().replace(" ", "")

            if (matchDateTime.contains("$targetStartDay$targetStartMonth")) {
                startIndex = i
            }
            if (targetEndDay != null && targetEndMonth != null && matchDateTime.contains("$targetEndDay$targetEndMonth")
            ) {
                endIndex = i
                endIndexExist = true
            }
        }

        if (startIndex == -1) throw Exception("the target day was not found")

        return Pair(first = startIndex, second = endIndex)
    }

    private suspend fun navigateToTargetDate(navigationState: NavigationState) {
        browserService.findElementByClass(className = "dropdown-toggle").clickElement(i = 0)
        val months = browserService.findElementByClass(className = "dropdown-link")
        val targetMonth = navigationState.collectionDescription.startDate.month.name.lowercase()
        val targetYear = navigationState.collectionDescription.startDate.year
        val total = months.elementsCount()

        var targetDateFound = false

        for (i in 0 until total) {
            val text = browserService.extractInnerText(i = i).lowercase()
            if (text.contains("$targetMonth $targetYear")) {
                browserService.clickElement(i = i)
                targetDateFound = true
                break
            }
        }

        if (targetDateFound.not()) throw Exception("the target month and year was not found")
    }
}