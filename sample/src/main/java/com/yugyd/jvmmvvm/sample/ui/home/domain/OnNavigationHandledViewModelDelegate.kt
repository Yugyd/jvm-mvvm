package com.yugyd.jvmmvvm.sample.ui.home.domain

import com.yugyd.jvmmvvm.domain.BaseViewModel
import com.yugyd.jvmmvvm.sample.ui.home.domain.HomeViewModel.Event
import com.yugyd.jvmmvvm.sample.ui.home.domain.HomeViewModel.State
import kotlinx.coroutines.CoroutineScope

class OnNavigationHandledViewModelDelegate : HomeViewModelDelegate {

    override fun accept(
        event: Event,
        viewModel: BaseViewModel<Event, State>,
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
