package com.yugyd.jvmmvvm.domain

import androidx.annotation.MainThread
import kotlinx.coroutines.CoroutineScope

interface ViewModelDelegate<Event : Any, State : Any> {

    @MainThread
    fun accept(
        event: Event,
        viewModel: BaseViewModel<Event, State>,
        scope: CoroutineScope,
        @MainThread getState: () -> State,
    ): Boolean
}
