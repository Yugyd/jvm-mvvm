package com.yugyd.jvmmvvm.domain

interface ViewModelLogger {
    fun log(message: String)
    fun throwIfDebug(error: Throwable)
}
