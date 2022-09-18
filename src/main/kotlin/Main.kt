import applicationDI.AppModule
import constants.BrowserType
import constants.Month
import constants.Site
import browserServiceDI.BrowserServiceModule
import dependencyInjection.PersistenceModule
import superSportDI.SuperSportModule
import models.Browser
import models.BrowserSize
import models.CollectionDescription
import models.TargetDate
import uy.kohesive.injekt.InjektMain
import uy.kohesive.injekt.api.InjektRegistrar
import uy.kohesive.injekt.api.addSingleton
import uy.kohesive.injekt.injectLazy

suspend fun main() {

    SetUpDependencyInjection()

    val application: Application by injectLazy()
    application.run()
}

class SetUpDependencyInjection : InjektMain(){
    override fun InjektRegistrar.registerInjectables() {

        val browserSpec = Browser(
            browserType = BrowserType.CHROME,
            openInMaximizeView = true,
            waitDurationInSeconds = 30,
            browserSize = BrowserSize(
                width = 800,
                height = 800
            )
        )
        val collectionDescription = CollectionDescription(
            startDate = TargetDate(day = 1, month = Month.JANUARY, year = 2022),
            endDate = TargetDate(day = 2, month = Month.SEPTEMBER, year = 2022)
        )
        val targetSite: Site = Site.SUPER_SPORT

        addSingleton(browserSpec)
        addSingleton(collectionDescription)

        when(targetSite){
            Site.SUPER_SPORT -> importModule(SuperSportModule)
        }

        importModule(BrowserServiceModule)
        importModule(AppModule)
        importModule(PersistenceModule)

    }

}

