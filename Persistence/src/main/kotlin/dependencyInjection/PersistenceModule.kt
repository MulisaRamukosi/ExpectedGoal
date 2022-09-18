package dependencyInjection

import dao.*
import dao.CommentaryDaoImpl
import dao.TeamDaoImpl
import uy.kohesive.injekt.api.InjektModule
import uy.kohesive.injekt.api.InjektRegistrar
import uy.kohesive.injekt.api.addSingletonFactory

object PersistenceModule : InjektModule {

    override fun InjektRegistrar.registerInjectables() {
        addSingletonFactory<CommentaryDao> { CommentaryDaoImpl() }
        addSingletonFactory<EventDao> { EventDaoImpl() }
        addSingletonFactory<LineUpDao> { LineUpDaoImpl() }
        addSingletonFactory<LocationDao> { LocationDaoImpl() }
        addSingletonFactory<MatchDao> { MatchDaoImpl() }
        addSingletonFactory<PlayerDao> { PlayerDaoImpl() }
        addSingletonFactory<StatDao> { StatDaoImpl() }
        addSingletonFactory<TeamDao> { TeamDaoImpl() }
    }
}