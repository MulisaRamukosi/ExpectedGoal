package dependencyInjection

import constants.CsvFileNames
import dao.*
import dao.implementation.*
import mapper.CommentaryMapper
import mapper.TeamMapper
import repo.CommentaryRepoImpl
import repo.TeamRepoImpl
import repositories.CommentaryRepo
import repositories.TeamRepo
import storage.fileDefinition.FileDefinition
import storage.fileWriter.FileWriterImpl
import uy.kohesive.injekt.api.InjektModule
import uy.kohesive.injekt.api.InjektRegistrar
import uy.kohesive.injekt.api.addSingletonFactory

object PersistenceModule : InjektModule {

    override fun InjektRegistrar.registerInjectables() {
        FileDefinition().setupCsvFiles()

        addSingletonFactory<TeamRepo> {
            TeamRepoImpl(
                mapper = TeamMapper(),
                fileWriter = FileWriterImpl(fileName = CsvFileNames.TEAM)
            )
        }

        addSingletonFactory <CommentaryRepo> {
            CommentaryRepoImpl(
                mapper = CommentaryMapper(),
                fileWriter = FileWriterImpl(fileName = CsvFileNames.COMMENTARY)
            )
        }

    }
}