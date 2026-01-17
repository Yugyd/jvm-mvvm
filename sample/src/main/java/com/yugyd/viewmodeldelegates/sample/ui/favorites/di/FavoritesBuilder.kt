package com.yugyd.viewmodeldelegates.sample.ui.favorites.di

import com.yugyd.viewmodeldelegates.sample.ui.core.DispatchersProviderImpl
import com.yugyd.viewmodeldelegates.sample.ui.favorites.domain.FavoritesViewModel.State
import com.yugyd.viewmodeldelegates.sample.ui.favorites.domain.FavoritesViewModelImpl
import com.yugyd.viewmodeldelegates.sample.ui.favorites.domain.data.FavoritesRepositoryImpl
import com.yugyd.viewmodeldelegates.sample.ui.favorites.ui.FavoritesBinder
import com.yugyd.viewmodeldelegates.sample.ui.home.di.buildLogger

fun buildFavoritesBinder(): FavoritesBinder {
    val arguments = State.Arguments(
        userName = "Default User",
    )

    val dispatchersProvider = DispatchersProviderImpl()

    val repository = FavoritesRepositoryImpl()

    val viewModel = FavoritesViewModelImpl(
        repository = repository,
        initialState = State(arguments = arguments),
        dispatchersProvider = dispatchersProvider,
        logger = buildLogger(),
    )
        .apply {
            init()
        }

    return FavoritesBinder(
        viewModel = viewModel,
        dispatchersProvider = dispatchersProvider,
    )
}
