package com.captures2024.soongan.core.common.base

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

abstract class BaseViewModel<S : UIState, SE : UISideEffect, I : UIIntent>(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val initialState: S by lazy { createInitialState(savedStateHandle) }

    protected abstract fun createInitialState(savedStateHandle: SavedStateHandle): S

    protected abstract suspend fun handleIntent(intent: I)

    private val _state = MutableStateFlow(initialState)
    val state = _state.asStateFlow()

    private val _sideEffect: MutableSharedFlow<SE> = MutableSharedFlow()
    val sideEffect = _sideEffect.asSharedFlow()

    protected val currentState: S
        get() = _state.value

    protected val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        handleClientException(throwable)
    }

    fun intent(intent: I) {
        launch {
            handleIntent(intent)
        }
    }

    protected fun reduce(reduce: S.() -> S) {
        val state = currentState.reduce()
        _state.value = state
    }

    protected fun postSideEffect(sideEffect: SE) {
        viewModelScope.launch {
            _sideEffect.emit(sideEffect)
        }
    }

    protected inline fun launch(
        context: CoroutineContext = EmptyCoroutineContext,
        start: CoroutineStart = CoroutineStart.DEFAULT,
        crossinline action: suspend CoroutineScope.() -> Unit,
    ): Job = viewModelScope.launch(
        context = context + coroutineExceptionHandler,
        start = start
    ) {
        action()
    }

    abstract fun handleClientException(throwable: Throwable)
}