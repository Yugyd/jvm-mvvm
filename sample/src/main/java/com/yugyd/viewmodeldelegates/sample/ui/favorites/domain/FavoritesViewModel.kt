package com.yugyd.viewmodeldelegates.sample.ui.favorites.domain

import androidx.compose.runtime.Immutable
import com.yugyd.viewmodeldelegates.ViewModel
import com.yugyd.viewmodeldelegates.sample.ui.favorites.domain.FavoritesViewModel.Event
import com.yugyd.viewmodeldelegates.sample.ui.favorites.domain.FavoritesViewModel.State

interface FavoritesViewModel : ViewModel<Event, State> {

    sealed interface Event {
        object LoadData : Event
        object OnActionClicked : Event
        object OnSnackbarDismissed : Event
        object OnNavigationHandled : Event
    }

    @Immutable
    data class State(
        val arguments: Arguments = Arguments(),
        val isLoading: Boolean = false,
        val isWarning: Boolean = false,
        val message: String = "",
        val showSnackbarMessage: Boolean = false,
        val navigationState: NavigationState? = null,
    ) {

        @Immutable
        data class Arguments(
            val userName: String = "",
        )

        @Immutable
        sealed interface NavigationState {
            object Back : NavigationState
        }
    }
}
