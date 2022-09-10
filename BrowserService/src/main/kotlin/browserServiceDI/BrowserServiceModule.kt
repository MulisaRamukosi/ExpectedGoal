package browserServiceDI

import services.BrowserService
import services.BrowserServiceImpl
import uy.kohesive.injekt.Injekt
import uy.kohesive.injekt.api.InjektModule
import uy.kohesive.injekt.api.InjektRegistrar
import uy.kohesive.injekt.api.addSingletonFactory
import uy.kohesive.injekt.api.get

object BrowserServiceModule : InjektModule {
    override fun InjektRegistrar.registerInjectables() {
        addSingletonFactory<BrowserService> {
            BrowserServiceImpl(Injekt.get())
        }
    }
}