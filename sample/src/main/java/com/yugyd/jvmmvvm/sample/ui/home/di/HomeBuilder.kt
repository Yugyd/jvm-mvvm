package com.yugyd.jvmmvvm.sample.ui.home.di

import com.yugyd.jvmmvvm.domain.DefaultViewModelFactory
import com.yugyd.jvmmvvm.domain.JvmViewModel
import com.yugyd.jvmmvvm.domain.ViewModelLogger
import com.yugyd.jvmmvvm.sample.ui.home.domain.HomeViewModel
import com.yugyd.jvmmvvm.sample.ui.home.domain.HomeViewModel.Event
import com.yugyd.jvmmvvm.sample.ui.home.domain.HomeViewModel.State
import com.yugyd.jvmmvvm.sample.ui.home.domain.LoadDataViewModelDelegate
import com.yugyd.jvmmvvm.sample.ui.home.domain.OnActionClickedViewModelDelegate
import com.yugyd.jvmmvvm.sample.ui.home.domain.OnNavigationHandledViewModelDelegate
import com.yugyd.jvmmvvm.sample.ui.home.domain.OnSnackbarDismissedViewModelDelegate
import com.yugyd.jvmmvvm.sample.ui.home.domain.data.HomeRepositoryImpl
import com.yugyd.jvmmvvm.sample.ui.home.ui.HomeBinder
import com.yugyd.jvmmvvm.sample.ui.home.ui.HomeMapper

private fun buildLogger() = object : ViewModelLogger {
    override fun log(message: String) {
        println(message)
    }

    override fun throwIfDebug(error: Throwable) {
        throw error
    }
}

fun buildHomeBinder(): HomeBinder {
    val arguments = State.Arguments(
        userName = "Default User",
    )

    val repository = HomeRepositoryImpl()

    val delegates = setOf(
        LoadDataViewModelDelegate(repository = repository),
        OnActionClickedViewModelDelegate(),
        OnNavigationHandledViewModelDelegate(),
        OnSnackbarDismissedViewModelDelegate(),
    )

    val viewModel = object : HomeViewModel,
        JvmViewModel<Event, State> by DefaultViewModelFactory().create(
            initialState = State(arguments = arguments),
            viewModelDelegates = delegates,
            initEvents = setOf(Event.LoadData),
            logger = buildLogger(),
            name = "HomeViewModel",
        ) {}
    val mapper = HomeMapper()
    return HomeBinder(
        viewModel = viewModel,
        mapper = mapper,
    )
}
