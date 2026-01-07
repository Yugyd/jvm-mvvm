package com.yugyd.jvmmvvm.ui

fun interface StateToModelMapper<in State, out Model> {
    fun map(state: State): Model
}
