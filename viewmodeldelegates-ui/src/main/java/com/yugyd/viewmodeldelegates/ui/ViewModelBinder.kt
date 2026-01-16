package com.yugyd.viewmodeldelegates.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel

abstract class ViewModelBinder(
    mainCoroutineDispatcher: CoroutineDispatcher = Dispatchers.Main.immediate,
) : ViewModel(), Binder {

    protected val binderScope: CoroutineScope =
        CoroutineScope(SupervisorJob() + mainCoroutineDispatcher)

    final override fun onCleared() {
        binderScope.cancel()
        unbind()
        super.onCleared()
    }

    abstract override fun unbind()
}
