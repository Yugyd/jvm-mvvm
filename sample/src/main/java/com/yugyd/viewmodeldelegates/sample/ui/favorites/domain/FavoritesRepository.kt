package com.yugyd.viewmodeldelegates.sample.ui.favorites.domain

import kotlinx.coroutines.flow.Flow

interface FavoritesRepository {
    fun getData(userName: String): Flow<String>
}
