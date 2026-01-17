package com.yugyd.viewmodeldelegates.sample.ui.favorites.ui

import com.yugyd.viewmodeldelegates.sample.ui.core.DispatchersProvider
import com.yugyd.viewmodeldelegates.sample.ui.favorites.domain.FavoritesViewModel
import com.yugyd.viewmodeldelegates.sample.ui.favorites.domain.FavoritesViewModel.Event
import com.yugyd.viewmodeldelegates.sample.ui.favorites.domain.FavoritesViewModel.State
import com.yugyd.viewmodeldelegates.ui.StateViewModelBinder

class FavoritesBinder(
    private val viewModel: FavoritesViewModel,
    dispatchersProvider: DispatchersProvider,
) : StateViewModelBinder<Event, State>(
    viewModel = viewModel,
    mainImmediateCoroutineDispatcher = dispatchersProvider.main,
) {
    fun onEvent(event: Event) = viewModel.accept(event)
}
