package superSportDI

import SuperSportResultScrapper
import services.WebsiteResultScrapper
import uy.kohesive.injekt.Injekt
import uy.kohesive.injekt.api.InjektModule
import uy.kohesive.injekt.api.InjektRegistrar
import uy.kohesive.injekt.api.addSingletonFactory
import uy.kohesive.injekt.api.get

object SuperSportModule : InjektModule {
    override fun InjektRegistrar.registerInjectables() {
        addSingletonFactory<WebsiteResultScrapper> {
            SuperSportResultScrapper(Injekt.get())
        }
    }
}