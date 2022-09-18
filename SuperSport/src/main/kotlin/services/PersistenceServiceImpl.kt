package services

import dao.*
import models.CleanSiteScrap
import models.Response
import uy.kohesive.injekt.Injekt
import uy.kohesive.injekt.api.get

class PersistenceServiceImpl(
    private val commentaryDao: CommentaryDao = Injekt.get(),
    private val eventDao: EventDao = Injekt.get(),
    private val lineUpDao: LineUpDao = Injekt.get(),
    private val locationDao: LocationDao = Injekt.get(),
    private val matchDao: MatchDao = Injekt.get(),
    private val playerDao: PlayerDao = Injekt.get(),
    private val statDao: StatDao = Injekt.get(),
    private val teamDao: TeamDao = Injekt.get()
) : PersistenceService {

    override suspend fun save(cleanSiteScrap: CleanSiteScrap) {
        TODO("Not yet implemented")
    }
}