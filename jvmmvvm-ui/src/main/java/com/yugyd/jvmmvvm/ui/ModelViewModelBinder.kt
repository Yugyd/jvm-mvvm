package com.yugyd.jvmmvvm.ui

import androidx.annotation.CallSuper
import com.yugyd.jvmmvvm.domain.JvmViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

abstract class ModelViewModelBinder<Event : Any, State : Any, Model : Any>(
    private val viewModel: JvmViewModel<Event, State>,
    stateToModelMapper: StateToModelMapper<State, Model>,
    initialModel: Model,
    mainCoroutineDispatcher: CoroutineDispatcher = Dispatchers.Main.immediate,
) : ViewModelBinder(mainCoroutineDispatcher = mainCoroutineDispatcher) {

    val model: StateFlow<Model> = viewModel.state
        .map(stateToModelMapper::map)
        .stateIn(
            scope = binderScope,
            started = SharingStarted.WhileSubscribed(
                stopTimeoutMillis = CHANGE_CONFIGURATION_TIMEOUT_MILLS,
            ),
            initialValue = initialModel,
        )

    @CallSuper
    override fun unbind() {
        viewModel.dispose()
    }

    companion object {
        private const val CHANGE_CONFIGURATION_TIMEOUT_MILLS = 5000L
    }
}
