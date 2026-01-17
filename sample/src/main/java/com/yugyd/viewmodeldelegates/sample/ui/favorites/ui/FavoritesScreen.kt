package com.yugyd.viewmodeldelegates.sample.ui.favorites.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.yugyd.viewmodeldelegates.sample.ui.favorites.domain.FavoritesViewModel
import com.yugyd.viewmodeldelegates.sample.ui.favorites.domain.FavoritesViewModel.State.NavigationState

@Composable
fun FavoritesScreen(
    binder: FavoritesBinder,
    modifier: Modifier = Modifier,
    onBack: () -> Unit = {},
) {
    val state by binder.model.collectAsStateWithLifecycle()
    val snackbarHostState = SnackbarHostState()

    FavoritesContent(
        message = state.message,
        isLoading = state.isLoading,
        isWarning = state.isWarning,
        onActionClicked = {
            binder.onEvent(FavoritesViewModel.Event.OnActionClicked)
        },
        modifier = modifier,
    )

    ErrorMessageEffect(
        showErrorMessage = state.showSnackbarMessage,
        snackbarHostState = snackbarHostState,
        onErrorDismissState = {
            binder.onEvent(FavoritesViewModel.Event.OnSnackbarDismissed)
        },
    )

    NavigationHandlerEffect(
        navigationState = state.navigationState,
        onBack = onBack,
        onNavigationHandled = {
            binder.onEvent(FavoritesViewModel.Event.OnNavigationHandled)
        },
    )
}

@Composable
private fun FavoritesContent(
    message: String,
    isLoading: Boolean,
    isWarning: Boolean,
    onActionClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    when {
        isLoading -> {
            Text(
                text = "Loading...",
                modifier = modifier
            )
        }

        isWarning -> {
            Text(
                text = "Warning!",
                modifier = modifier
            )
        }

        else -> {
            Column(modifier = modifier) {
                Text(
                    text = "Hello $message!",
                    modifier = modifier
                )

                Button(
                    onClick = onActionClicked,
                ) {
                    Text(text = "Back")
                }
            }
        }
    }
}

@Composable
private fun ErrorMessageEffect(
    showErrorMessage: Boolean,
    snackbarHostState: SnackbarHostState,
    onErrorDismissState: () -> Unit,
) {
    LaunchedEffect(key1 = showErrorMessage) {
        if (showErrorMessage) {
            snackbarHostState.showSnackbar(message = "An error occurred")

            onErrorDismissState()
        }
    }
}

@Composable
private fun NavigationHandlerEffect(
    navigationState: NavigationState?,
    onBack: () -> Unit,
    onNavigationHandled: () -> Unit,
) {
    LaunchedEffect(key1 = navigationState) {
        when (navigationState) {
            is NavigationState.Back -> onBack()
            null -> Unit
        }

        navigationState?.let { onNavigationHandled() }
    }
}
