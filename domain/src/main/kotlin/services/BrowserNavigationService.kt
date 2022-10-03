package services

import models.NavigationState
import models.Response

interface BrowserNavigationService {
    suspend fun navigateToState(navigationState: NavigationState)
    suspend fun loadNextState(): NavigationState?
}