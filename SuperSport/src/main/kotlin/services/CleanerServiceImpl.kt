package services

import constants.*
import dto.*
import ext.extractAlphabets
import ext.extractAlphabetsWithoutQuotations
import ext.extractNumbersToInt
import models.CleanSiteScrap
import models.SiteScrap
import org.jsoup.Jsoup
import java.text.SimpleDateFormat
import java.util.Date

class CleanerServiceImpl : CleanerService {

    override fun cleanSiteScrap(siteScrap: SiteScrap): CleanSiteScrap {
        val matchDetails = siteScrap.matchDetails.split("\n")
        val events = extractEvents(eventsHtml = siteScrap.events)

        return CleanSiteScrap(
            match = MatchDto(
                date = extractDate(matchDetails = matchDetails),
                location = extractLocation(matchDetails = matchDetails),
                homeTeam = extractHomeTeam(matchDetails = matchDetails),
                awayTeam = extractAwayTeam(matchDetails = matchDetails),
                homeScore = extractHomeScore(matchDetails = matchDetails),
                awayScore = extractAwayScore(matchDetails = matchDetails),
                stats = extractStats(matchStats = siteScrap.stats),
                commentary = extractCommentary(matchCommentary = siteScrap.commentary),
                homeLineUp = extractLineUp(matchLineUp = siteScrap.homeLineUp),
                awayLineUp = extractLineUp(matchLineUp = siteScrap.awayLineUp),
                homeEvents = events.first,
                awayEvents = events.second
            )
        )
    }

    private fun extractDate(matchDetails: List<String>): Date {
        val date = matchDetails[6]
        val smdf = SimpleDateFormat(Constants.DATE_FORMAT)
        return smdf.parse(date)
    }

    private fun extractHomeTeam(matchDetails: List<String>): TeamDto {
        return TeamDto(name = matchDetails[1])
    }

    private fun extractAwayTeam(matchDetails: List<String>): TeamDto {
        return TeamDto(name = matchDetails[5])
    }

    private fun extractHomeScore(matchDetails: List<String>): Int {
        return matchDetails[2].toInt()
    }

    private fun extractAwayScore(matchDetails: List<String>): Int {
        return matchDetails[4].toInt()
    }

    private fun extractLocation(matchDetails: List<String>): LocationDto {
        return LocationDto(name = matchDetails[7].extractAlphabetsWithoutQuotations())
    }

    private fun extractCommentary(matchCommentary: String): List<CommentaryDto> {
        val commentaries = matchCommentary.split("\n")
        val commentaryList = arrayListOf<CommentaryDto>()

        for (i in 0..commentaries.size - 2 step 2) {
            val commentary = CommentaryDto(
                comment = commentaries[i + 1].extractAlphabetsWithoutQuotations(),
                minute = commentaries[i].extractNumbersToInt()
            )
            commentaryList.add(commentary)
        }

        return commentaryList
    }

    private fun extractLineUp(matchLineUp: String): List<LineUpDto> {
        val lineUps = matchLineUp.split("\n")
        val playersLineUp = arrayListOf<LineUpDto>()

        var i = 0

        while (i <= lineUps.size - 3) {

            if (lineUps[i].lowercase() == "substitutes") i++

            val positionNumber = lineUps[i].extractNumbersToInt()
            val player = PlayerDto(name = lineUps[i + 1])
            val playerPosition = lineUps[i + 2].extractAlphabets()
            val position = PlayerPosition.valueOf(playerPosition.uppercase())
            val lineUpType = if (position == PlayerPosition.SUBSTITUTE) LineUpType.SUBSTITUTE else LineUpType.MAIN

            playersLineUp.add(
                LineUpDto(
                    player = player,
                    type = lineUpType,
                    position = position,
                    positionNumber = positionNumber
                )
            )

            i += 3
        }

        return playersLineUp
    }

    private fun extractEvents(eventsHtml: String): Pair<List<EventDto>, List<EventDto>> {
        val homeEvents = arrayListOf<EventDto>()
        val awayEvents = arrayListOf<EventDto>()
        val doc = Jsoup.parse(eventsHtml)
        val matchEvents = doc.getElementsByClass("scrollable").first()!!
            .getElementsByTag("ul").first()!!.children()

        matchEvents.forEach { matchEvent ->
            val matchDetailsHome = matchEvent.childNode(0)
            val matchDetailAway = matchEvent.childNode(2)
            val eventMinuteHtml = matchEvent.childNode(1).outerHtml()
            val minute = Jsoup.parse(eventMinuteHtml).text().extractNumbersToInt()

            if (matchDetailsHome.childNodes().isEmpty().not()) {
                homeEvents.addAll(cleanEvent(minute = minute, eventHolderHtml = matchDetailsHome.outerHtml()))
            }

            if (matchDetailAway.childNodes().isEmpty().not()) {
                awayEvents.addAll(cleanEvent(minute = minute, eventHolderHtml = matchDetailAway.outerHtml()))
            }
        }

        return homeEvents to awayEvents
    }

    private fun cleanEvent(minute: Int, eventHolderHtml: String): List<EventDto> {
        val events = arrayListOf<EventDto>()
        val eventItems = Jsoup.parse(eventHolderHtml).getElementsByTag("li")

        eventItems.forEach { eventItem ->
            val itemHtml = eventItem.html()
            val doc = Jsoup.parse(itemHtml)
            val eventType = determineEventType(htmlContent = itemHtml)
            val player = doc.text()

            events.add(
                EventDto(
                    player = PlayerDto(name = player),
                    minute = minute,
                    type = eventType
                )
            )
        }

        return events
    }

    private fun determineEventType(htmlContent: String): EventType {
        val content = htmlContent.lowercase()
        if (content.contains("come_off")) {
            return EventType.SUBSTITUTION_OUT
        }
        if (content.contains("come_on")) {
            return EventType.SUBSTITUTION_IN
        }
        if (content.contains("booking")) {
            return EventType.YELLOW_CARD
        }
        if (content.contains("dismissal")) {
            return EventType.RED_CARD
        }
        if (content.contains("goal")) {
            return EventType.GOAL
        }
        if (content.contains("own-goal")) {
            return EventType.OWN_GOAL
        }

        throw Exception("UNKNOWN EVENT, here's the html $content")
    }

    private fun extractStats(matchStats: String): List<StatDto> {
        val stats = matchStats.split("\n")

        return listOf(
            constructStat(stats = stats, homePos = 2, awayPos = 4, type = StatType.SHOTS),
            constructStat(stats = stats, homePos = 5, awayPos = 7, type = StatType.SHOTS_ON_TARGET),
            constructStat(stats = stats, homePos = 8, awayPos = 10, type = StatType.CORNERS),
            constructStat(stats = stats, homePos = 11, awayPos = 13, type = StatType.OFFSIDES),
            constructStat(stats = stats, homePos = 14, awayPos = 16, type = StatType.FOULS),
            constructStat(stats = stats, homePos = 17, awayPos = 19, type = StatType.YELLOW_CARDS),
            constructStat(stats = stats, homePos = 20, awayPos = 22, type = StatType.RED_CARDS)
        )
    }

    private fun constructStat(stats: List<String>, homePos: Int, awayPos: Int, type: StatType): StatDto {
        return StatDto(
            home = stats[homePos].extractNumbersToInt(),
            away = stats[awayPos].extractNumbersToInt(),
            type = type
        )
    }
}