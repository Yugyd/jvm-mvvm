package com.yugyd.jvmmvvm.sample.ui.home.domain

import com.yugyd.jvmmvvm.domain.JvmViewModel
import com.yugyd.jvmmvvm.domain.ViewModelDelegate
import com.yugyd.jvmmvvm.sample.ui.home.domain.HomeViewModel.Event
import com.yugyd.jvmmvvm.sample.ui.home.domain.HomeViewModel.State

interface HomeViewModel : JvmViewModel<Event, State> {

    sealed interface Event {
        object LoadData : Event
        object OnActionClicked : Event
        object OnSnackbarDismissed : Event
        object OnNavigationHandled : Event
    }

    data class State(
        val arguments: Arguments = Arguments(),
        val isLoading: Boolean = false,
        val isWarning: Boolean = false,
        val message: String = "",
        val showErrorMessage: Boolean = false,
        val navigationState: NavigationState? = null,
    ) {

        data class Arguments(
            val userName: String = "",
        )

        sealed interface NavigationState {
            object NavigateToFavourites :
                NavigationState
        }
    }
}

typealias HomeViewModelDelegate = ViewModelDelegate<Event, State>
