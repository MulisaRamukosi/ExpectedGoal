package dependencyInjection

import constants.CsvFileNames
import dao.*
import dao.implementation.CommentaryDaoImpl
import dao.implementation.*
import dao.implementation.EventDaoImpl
import dao.implementation.LocationDaoImpl
import dao.implementation.MatchDaoImpl
import dao.implementation.PlayerDaoImpl
import dao.implementation.TeamDaoImpl
import mapper.TeamMapper
import repo.TeamRepoImpl
import repositories.TeamRepo
import storage.fileDefinition.FileDefinition
import storage.fileWriter.TeamFileWriterImpl
import uy.kohesive.injekt.api.InjektModule
import uy.kohesive.injekt.api.InjektRegistrar
import uy.kohesive.injekt.api.addSingletonFactory

object PersistenceModule : InjektModule {

    override fun InjektRegistrar.registerInjectables() {
        FileDefinition().setupCsvFiles()

        addSingletonFactory<TeamRepo> {
            TeamRepoImpl(
                mapper = TeamMapper(),
                fileWriter = TeamFileWriterImpl(fileName = CsvFileNames.TEAM)
            )
        }
    }
}