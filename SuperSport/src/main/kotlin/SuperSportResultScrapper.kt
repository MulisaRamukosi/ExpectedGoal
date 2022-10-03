import constants.Constants
import models.CollectionDescription
import models.NavigationState
import services.*

class SuperSportResultScrapper (private val browserService: BrowserService) : WebsiteResultScrapper {

    private val navigationService : BrowserNavigationService = BrowserNavigationServiceImpl(browserService = browserService)
    private val scrapperService: ScrapperService = ScrapperServiceImpl(browserService = browserService)
    private val cleanerService: CleanerService = CleanerServiceImpl()
    private val persistenceService: PersistenceService = PersistenceServiceImpl()

    override suspend fun scrap(collectionDescription: CollectionDescription) {
        try {
            val currentNavigationState = NavigationState(collectionDescription = collectionDescription, url = "")
            var navigationState: NavigationState?

            browserService.openUrl(Constants.superSportUrl)
            navigationService.navigateToState(currentNavigationState)

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