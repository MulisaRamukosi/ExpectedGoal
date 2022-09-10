package services

import models.NavigationState
import models.Response

interface BrowserNavigationService {
    suspend fun navigateToState(navigationState: NavigationState? = null)
    suspend fun loadNextState(): Response<NavigationState>
}