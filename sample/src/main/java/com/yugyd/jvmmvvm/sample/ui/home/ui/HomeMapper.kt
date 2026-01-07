package com.yugyd.jvmmvvm.sample.ui.home.ui

import com.yugyd.jvmmvvm.sample.ui.home.domain.HomeViewModel.State
import com.yugyd.jvmmvvm.sample.ui.home.domain.HomeViewModel.State.NavigationState
import com.yugyd.jvmmvvm.sample.ui.home.ui.HomeBinder.Model
import com.yugyd.jvmmvvm.sample.ui.home.ui.HomeBinder.Model.NavigationUiState
import com.yugyd.jvmmvvm.ui.StateToModelMapper

class HomeMapper : StateToModelMapper<State, Model> {

    override fun map(state: State): Model {
        return Model(
            isLoading = state.isLoading,
            isWarning = state.isWarning,
            message = state.message,
            showErrorMessage = state.showErrorMessage,
            navigationState = mapNavigationState(state.navigationState),
        )
    }

    private fun mapNavigationState(navigationState: NavigationState?): NavigationUiState? {
        return when (navigationState) {
            is NavigationState.NavigateToFavourites -> {
                NavigationUiState.NavigateToFavourites
            }

            null -> null
        }
    }
}
