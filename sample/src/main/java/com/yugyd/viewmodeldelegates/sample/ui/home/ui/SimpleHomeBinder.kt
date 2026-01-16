package com.yugyd.viewmodeldelegates.sample.ui.home.ui

import com.yugyd.viewmodeldelegates.sample.ui.home.domain.HomeViewModel
import com.yugyd.viewmodeldelegates.sample.ui.home.domain.HomeViewModel.Event
import com.yugyd.viewmodeldelegates.sample.ui.home.domain.HomeViewModel.State
import com.yugyd.viewmodeldelegates.ui.StateViewModelBinder

class SimpleHomeBinder(
    private val viewModel: HomeViewModel,
) : StateViewModelBinder<Event, State>(viewModel) {

    fun onEvent(event: Event) {
        viewModel.accept(event)
    }
}
