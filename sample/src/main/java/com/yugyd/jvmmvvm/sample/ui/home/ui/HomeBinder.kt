package com.yugyd.jvmmvvm.sample.ui.home.ui

import com.yugyd.jvmmvvm.sample.ui.home.domain.HomeViewModel
import com.yugyd.jvmmvvm.sample.ui.home.domain.HomeViewModel.Event
import com.yugyd.jvmmvvm.sample.ui.home.domain.HomeViewModel.State
import com.yugyd.jvmmvvm.sample.ui.home.ui.HomeBinder.Model
import com.yugyd.jvmmvvm.ui.ModelViewModelBinder

class HomeBinder(
    private val viewModel: HomeViewModel,
    mapper: HomeMapper,
) : ModelViewModelBinder<Event, State, Model>(
    viewModel = viewModel,
    initialModel = Model(),
    stateToModelMapper = mapper,
) {

    fun onActionClicked() {
        viewModel.accept(Event.OnActionClicked)
    }

    fun onSnackbarDismissed() {
        viewModel.accept(Event.OnSnackbarDismissed)
    }

    fun onNavigationHandled() {
        viewModel.accept(Event.OnNavigationHandled)
    }

    data class Model(
        val isLoading: Boolean = false,
        val isWarning: Boolean = false,
        val message: String = "",
        val showErrorMessage: Boolean = false,
        val navigationState: NavigationUiState? = null,
    ) {

        sealed interface NavigationUiState {
            object NavigateToFavourites : NavigationUiState
        }
    }
}
