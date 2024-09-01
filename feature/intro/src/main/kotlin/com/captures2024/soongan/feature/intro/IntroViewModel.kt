package com.captures2024.soongan.feature.intro

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.captures2024.soongan.core.common.base.BaseViewModel
import com.captures2024.soongan.core.domain.usecase.members.IsAllowUserInfoUseCase
import com.captures2024.soongan.core.domain.usecase.token.GetAllTokenUseCase
import com.captures2024.soongan.feature.intro.state.IntroIntent
import com.captures2024.soongan.feature.intro.state.IntroSideEffect
import com.captures2024.soongan.feature.intro.state.IntroUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class IntroViewModel
@Inject
constructor(
    private val getAllTokenUseCase: GetAllTokenUseCase,
    private val isAllowUserInfoUseCase: IsAllowUserInfoUseCase,
    savedStateHandle: SavedStateHandle,
) : BaseViewModel<IntroUIState, IntroSideEffect, IntroIntent>(savedStateHandle = savedStateHandle) {
    override fun createInitialState(savedStateHandle: SavedStateHandle): IntroUIState = IntroUIState()

    override fun handleClientException(throwable: Throwable) {
        /* TODO */
    }

    override suspend fun handleIntent(intent: IntroIntent) {
        when (intent) {
            is IntroIntent.RefreshUserData -> refreshUserData()
        }
    }


    private fun refreshUserData() {
        launch {
            val tokenResult = getAllTokenUseCase().getOrNull()

            if (tokenResult == null) {
                Timber.tag(TAG).d("tokenResult is Null")
                postSideEffect(IntroSideEffect.NavigateToSign)
                return@launch
            }

            val isAllow = isAllowUserInfoUseCase().getOrNull()

            if (isAllow == null) {
                Timber.tag(TAG).d("isAllow is Null")
                postSideEffect(IntroSideEffect.NavigateToSign)
                return@launch
            }

            when (isAllow) {
                true -> postSideEffect(IntroSideEffect.NavigateToMain)
                false -> postSideEffect(IntroSideEffect.NavigateToSign)
            }
        }
    }

    companion object {
        private const val TAG = "IntroVM"
    }
}