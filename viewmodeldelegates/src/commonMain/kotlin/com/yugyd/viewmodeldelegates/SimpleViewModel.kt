package com.yugyd.viewmodeldelegates

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

abstract class SimpleViewModel<in Event : Any, State : Any>(
    initialState: State,
    mainImmediateCoroutineDispatcher: CoroutineDispatcher,
    private val logger: ViewModelLogger? = null,
    private val name: String? = null,
    private val initEvents: Set<Event> = emptySet(),
) : ViewModel<Event, State> {

    private val _state = MutableStateFlow(initialState)
    final override val state: StateFlow<State> = _state.asStateFlow()

    private val isLoggerEnabled: Boolean = name != null && logger != null

    protected val viewModelScope: CoroutineScope = CoroutineScope(
        SupervisorJob() + mainImmediateCoroutineDispatcher,
    )

    final override fun init() {
        if (isLoggerEnabled) {
            logger?.log("$name: initializing")
        }

        initEvents.forEach { event ->
            accept(event)
        }

        doInit()
    }

    protected open fun doInit() = Unit

    final override fun accept(event: Event) {
        if (isLoggerEnabled) {
            logger?.log("$name: event accepted: $event")
        }

        acceptEvent(event)
    }

    protected abstract fun acceptEvent(event: Event)

    final override fun dispose() {
        viewModelScope.cancel()

        if (isLoggerEnabled) {
            logger?.log("$name: disposed")
        }
    }

    final override fun setState(newState: State) {
        _state.update {
            logNewState(newState)
            newState
        }
    }

    final override fun updateState(transform: (currentState: State) -> State) {
        _state.update { currentState ->
            val newState = transform(currentState)
            logNewState(newState)
            newState
        }
    }

    private fun logNewState(newState: State) {
        if (isLoggerEnabled) {
            logger?.log("$name: state changed: $newState")
        }
    }
}
