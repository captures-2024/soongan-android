package com.captures2024.soongan.core.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.common.base.UIIntent
import com.captures2024.soongan.core.common.base.UISideEffect
import com.captures2024.soongan.core.common.base.UIState
import com.captures2024.soongan.core.domain.usecase.dialog.SetIsShowGuestModeDialogFlowUseCase
import com.captures2024.soongan.core.domain.usecase.loading.ClearLoadingUseCase
import com.captures2024.soongan.core.domain.usecase.loading.HideLoadingUseCase
import com.captures2024.soongan.core.domain.usecase.loading.ShowLoadingUseCase
import com.captures2024.soongan.core.domain.usecase.members.GetIsCurrentGuestModeUseCase
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

@ViewModelScoped
abstract class NewBaseViewModel<S: UIState, SE: UISideEffect, I: UIIntent>(
    protected val analyticsHelper: AnalyticsHelper,
    private val showLoadingUseCase: ShowLoadingUseCase,
    private val hideLoadingUseCase: HideLoadingUseCase,
    private val clearLoadingUseCase: ClearLoadingUseCase,
    private val getIsCurrentGuestModeUseCase: GetIsCurrentGuestModeUseCase,
    private val setIsShowGuestModeDialogFlowUseCase: SetIsShowGuestModeDialogFlowUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val simpleName: String?
        get() = this::class.simpleName

    private val initialState: S by lazy { createInitialState(savedStateHandle = savedStateHandle) }

    protected abstract fun createInitialState(savedStateHandle: SavedStateHandle): S

    protected abstract fun handleIntent(intent: I)

    private val _state: MutableStateFlow<S> = MutableStateFlow(initialState)
    val state: StateFlow<S>
        get() = _state.asStateFlow()

    private val _sideEffect: MutableSharedFlow<SE> = MutableSharedFlow()
    val sideEffect
        get() = _sideEffect.asSharedFlow()

    protected val currentState: S
        get() = _state.value

    protected val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        handleClientException(throwable)
    }

    protected abstract fun handleClientException(throwable: Throwable)

    protected fun showLoading() {
        showLoadingUseCase(simpleName ?: "")
    }

    protected fun hideLoading() {
        hideLoadingUseCase(simpleName ?: "")
    }

    protected fun clearLoading() {
        clearLoadingUseCase(simpleName ?: "")
    }

    protected fun isGuestMode(): Boolean = getIsCurrentGuestModeUseCase()

    protected fun showGuestModeDialog() {
        setIsShowGuestModeDialogFlowUseCase(true)
    }

    protected fun dismissGuestModeDialog() {
        setIsShowGuestModeDialogFlowUseCase(false)
    }

    protected fun launch(
        context: CoroutineContext = EmptyCoroutineContext,
        start: CoroutineStart = CoroutineStart.DEFAULT,
        block: suspend CoroutineScope.() -> Unit
    ): Job = viewModelScope.launch(
        context = context + coroutineExceptionHandler,
        start = start,
        block = block
    )

    protected fun loadingLaunch(
        context: CoroutineContext = EmptyCoroutineContext,
        block: suspend CoroutineScope.() -> Unit
    ) {
        showLoading()
        launch(context) {
            try {
                block()
            } finally {
                hideLoading()
            }
        }
    }

    fun intent(intent: I) {
        analyticsHelper.i(message = "[$simpleName] intent: $intent")
        handleIntent(intent)
    }

    protected fun reduce(reduce: S.() -> S) {
        val state = currentState.reduce()
        if (_state.value != state) {
            analyticsHelper.i(message = "[$simpleName] reduce: $state")
            _state.value = state
        }
    }

    protected fun postSideEffect(sideEffect: SE) {
        analyticsHelper.i(message = "[$simpleName] postSideEffect: $sideEffect")
        launch { _sideEffect.emit(sideEffect) }
    }
}
