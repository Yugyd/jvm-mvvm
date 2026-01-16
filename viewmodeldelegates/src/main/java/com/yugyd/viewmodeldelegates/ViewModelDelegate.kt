package com.yugyd.viewmodeldelegates

import androidx.annotation.MainThread
import kotlinx.coroutines.CoroutineScope

interface ViewModelDelegate<Event : Any, State : Any> {

    @MainThread
    fun accept(
        event: Event,
        viewModel: ViewModelDelegates<Event, State>,
        scope: CoroutineScope,
        @MainThread getState: () -> State,
    ): Boolean
}
