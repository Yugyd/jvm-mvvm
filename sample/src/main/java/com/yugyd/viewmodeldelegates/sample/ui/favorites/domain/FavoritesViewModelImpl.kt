package com.yugyd.viewmodeldelegates.sample.ui.favorites.domain

import com.yugyd.viewmodeldelegates.SimpleViewModel
import com.yugyd.viewmodeldelegates.ViewModelLogger
import com.yugyd.viewmodeldelegates.sample.ui.core.DispatchersProvider
import com.yugyd.viewmodeldelegates.sample.ui.favorites.domain.FavoritesViewModel.Event
import com.yugyd.viewmodeldelegates.sample.ui.favorites.domain.FavoritesViewModel.State
import com.yugyd.viewmodeldelegates.sample.ui.favorites.domain.FavoritesViewModel.State.NavigationState
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class FavoritesViewModelImpl(
    private val repository: FavoritesRepository,
    initialState: State,
    dispatchersProvider: DispatchersProvider,
    logger: ViewModelLogger?,
) :
    SimpleViewModel<Event, State>(
        initialState = initialState,
        mainImmediateCoroutineDispatcher = dispatchersProvider.main,
        logger = logger,
        name = "FavoritesViewModel",
        initEvents = setOf(Event.LoadData),
    ),
    FavoritesViewModel {

    override fun doInit() {
        updateState {
            it.copy(showSnackbarMessage = true)
        }
    }

    override fun acceptEvent(event: Event) {
        when (event) {
            Event.LoadData -> {
                updateState {
                    it.copy(
                        isLoading = true,
                        isWarning = false,
                        message = "",
                    )
                }

                viewModelScope.launch {
                    val currentState = state.value
                    val userName = currentState.arguments.userName

                    repository
                        .getData(userName = userName)
                        .catch { error ->
                            error.printStackTrace()

                            updateState {
                                it.copy(
                                    isLoading = false,
                                    isWarning = true,
                                    message = "",
                                )
                            }
                        }
                        .collect { newData ->
                            updateState {
                                it.copy(
                                    isLoading = false,
                                    isWarning = false,
                                    message = newData,
                                )
                            }
                        }
                }
            }

            Event.OnActionClicked -> {
                updateState {
                    it.copy(navigationState = NavigationState.Back)
                }
            }

            Event.OnNavigationHandled -> {
                updateState {
                    it.copy(navigationState = null)
                }
            }

            Event.OnSnackbarDismissed -> {
                updateState {
                    it.copy(showSnackbarMessage = false)
                }
            }
        }
    }
}
