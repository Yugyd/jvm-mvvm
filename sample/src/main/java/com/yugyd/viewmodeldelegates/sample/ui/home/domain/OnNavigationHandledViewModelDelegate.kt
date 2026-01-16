package com.yugyd.viewmodeldelegates.sample.ui.home.domain

import com.yugyd.viewmodeldelegates.ViewModelDelegates
import com.yugyd.viewmodeldelegates.sample.ui.home.domain.HomeViewModel.Event
import com.yugyd.viewmodeldelegates.sample.ui.home.domain.HomeViewModel.State
import kotlinx.coroutines.CoroutineScope

class OnNavigationHandledViewModelDelegate : HomeViewModelDelegate {

    override fun accept(
        event: Event,
        viewModel: ViewModelDelegates<Event, State>,
        scope: CoroutineScope,
        getState: () -> State
    ): Boolean {
        if (event != Event.OnNavigationHandled) return false

        viewModel.updateState {
            it.copy(navigationState = null)
        }

        return true
    }
}
