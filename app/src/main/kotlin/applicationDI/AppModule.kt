package applicationDI

import Application
import ApplicationImpl
import uy.kohesive.injekt.Injekt
import uy.kohesive.injekt.api.InjektModule
import uy.kohesive.injekt.api.InjektRegistrar
import uy.kohesive.injekt.api.addSingletonFactory
import uy.kohesive.injekt.api.get

object AppModule : InjektModule {
    override fun InjektRegistrar.registerInjectables() {
        addSingletonFactory <Application> {
            ApplicationImpl(
                websiteResultScrapper = Injekt.get(),
                collectionDescription = Injekt.get()
            )
        }
    }
}