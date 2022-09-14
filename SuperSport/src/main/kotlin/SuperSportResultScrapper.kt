import constants.Constants
import models.CollectionDescription
import models.NavigationState
import services.*

class SuperSportResultScrapper (private val browserService: BrowserService) : WebsiteResultScrapper {

    private val navigationService : BrowserNavigationService = BrowserNavigationServiceImpl(browserService = browserService)
    private val scrapperService: ScrapperService = ScrapperServiceImpl(browserService = browserService)
    private val cleanerService: CleanerService = CleanerServiceImpl(browserService = browserService)
    private val persistenceService: PersistenceService = PersistenceServiceImpl(browserService = browserService)

    override suspend fun scrap(collectionDescription: CollectionDescription) {
        try {
            var navigationState: NavigationState? = NavigationState(collectionDescription = collectionDescription, url = "")

            browserService.openUrl(Constants.superSportUrl)
            navigationService.navigateToState(navigationState)

            do{
                val siteScrap = scrapperService.scrapPage()
                val cleanSiteScrap = cleanerService.cleanSiteScrap(siteScrap = siteScrap)
                persistenceService.save(cleanSiteScrap = cleanSiteScrap)
                navigationState = navigationService.loadNextState()
            }
            while (navigationState != null)
        }
        catch (ex: Exception){
            println("============================ERROR============================")
            println(ex.message)
            println("=================STACK-TRACE=================")
            ex.printStackTrace()
            println("=============================================================")
        }
    }
}