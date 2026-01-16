package com.yugyd.viewmodeldelegates

import androidx.annotation.MainThread
import kotlinx.coroutines.flow.StateFlow

interface JvmViewModel<in Event : Any, State : Any> {

    val state: StateFlow<State>

    @MainThread
    fun accept(event: Event)

    @MainThread
    fun init()

    @MainThread
    fun dispose()

    @MainThread
    fun setState(newState: State)

    @MainThread
    fun updateState(transform: (currentState: State) -> State)
}
