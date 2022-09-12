package services

import models.NavigationState
import models.Response

class BrowserNavigationServiceImpl(private val browserService: BrowserService) : BrowserNavigationService {

    private var navigationState: NavigationState? = null

    override suspend fun navigateToState(navigationState: NavigationState?) {
        TODO("Not yet implemented")
    }

    override suspend fun loadNextState(): NavigationState? {
        TODO("Not yet implemented")
    }
}