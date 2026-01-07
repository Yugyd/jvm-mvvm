package com.yugyd.jvmmvvm.domain

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

internal class DefaultViewModelImpl<Event : Any, State : Any>(
    initialState: State,
    viewModelDelegates: Set<ViewModelDelegate<Event, State>>,
    mainCoroutineDispatcher: CoroutineDispatcher = Dispatchers.Main.immediate,
    logger: ViewModelLogger? = null,
    name: String? = null,
    initEvents: Set<Event> = emptySet(),
) : BaseViewModel<Event, State>(
    initialState = initialState,
    eventDelegates = viewModelDelegates,
    mainCoroutineDispatcher = mainCoroutineDispatcher,
    logger = logger,
    name = name,
    initEvents = initEvents,
)
