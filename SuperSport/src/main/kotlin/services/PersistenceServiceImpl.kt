package services

import models.CleanSiteScrap
import repositories.*
import uy.kohesive.injekt.Injekt
import uy.kohesive.injekt.api.get

class PersistenceServiceImpl(
    private val commentaryRepo: CommentaryRepo = Injekt.get(),
    private val eventRepo: EventRepo = Injekt.get(),
    private val lineUpRepo: LineUpRepo = Injekt.get(),
    private val locationRepo: LocationRepo = Injekt.get(),
    private val matchRepo: MatchRepo = Injekt.get(),
    private val playerRepo: PlayerRepo = Injekt.get(),
    private val statRepo: StatRepo = Injekt.get(),
    private val teamRepo: TeamRepo = Injekt.get()
) : PersistenceService {

    override suspend fun save(cleanSiteScrap: CleanSiteScrap) {
        TODO("Not yet implemented")
    }
}