package com.yugyd.viewmodeldelegates.sample.ui.home.domain

import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    fun getData(userName: String): Flow<String>
}
