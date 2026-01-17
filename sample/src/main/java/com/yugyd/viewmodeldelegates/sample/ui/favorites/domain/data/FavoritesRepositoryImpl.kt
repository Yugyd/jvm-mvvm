package com.yugyd.viewmodeldelegates.sample.ui.favorites.domain.data

import com.yugyd.viewmodeldelegates.sample.ui.favorites.domain.FavoritesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class FavoritesRepositoryImpl(
    private val ioCoroutineDispatcher: CoroutineDispatcher = Dispatchers.IO,
) : FavoritesRepository {

    override fun getData(userName: String) = flow {
        delay(5000L) // Simulate network delay
        emit("Sample Data from Repository for user: $userName")
    }
        .flowOn(ioCoroutineDispatcher)
}
