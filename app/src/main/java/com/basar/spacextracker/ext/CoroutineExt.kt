package com.basar.spacextracker.ext

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import timber.log.Timber

val exceptionHandler = CoroutineExceptionHandler { _, e ->
    Timber.d("exceptionHandler: " + e.message)
}

fun ViewModel.launchViewModelScope(block: suspend CoroutineScope.() -> Unit): Job {
    return viewModelScope.launch(exceptionHandler + Dispatchers.IO, block = block)
}