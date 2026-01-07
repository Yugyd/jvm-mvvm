package com.yugyd.jvmmvvm.sample.ui.home.domain

import com.yugyd.jvmmvvm.domain.BaseViewModel
import com.yugyd.jvmmvvm.sample.ui.home.domain.HomeViewModel.Event
import com.yugyd.jvmmvvm.sample.ui.home.domain.HomeViewModel.State
import com.yugyd.jvmmvvm.sample.ui.home.domain.HomeViewModel.State.NavigationState
import kotlinx.coroutines.CoroutineScope

class OnActionClickedViewModelDelegate : HomeViewModelDelegate {

    override fun accept(
        event: Event,
        viewModel: BaseViewModel<Event, State>,
        scope: CoroutineScope,
        getState: () -> State
    ): Boolean {
        if (event != Event.OnActionClicked) return false

        viewModel.updateState {
            it.copy(navigationState = NavigationState.NavigateToFavourites)
        }

        return true
    }
}
