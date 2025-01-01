package com.captures2024.soongan.feature.signUp

import androidx.lifecycle.SavedStateHandle
import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.common.base.BaseViewModel
import com.captures2024.soongan.core.domain.usecase.members.IsVerifiedNicknameUseCase
import com.captures2024.soongan.core.domain.usecase.members.PatchProfileUseCase
import com.captures2024.soongan.feature.signUp.state.nickname.NicknameIntent
import com.captures2024.soongan.feature.signUp.state.nickname.NicknameSideEffect
import com.captures2024.soongan.feature.signUp.state.nickname.NicknameUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class NicknameViewModel
@Inject
constructor(
    private val analyticsHelper: AnalyticsHelper,
    private val isVerifiedNicknameUseCase: IsVerifiedNicknameUseCase,
    private val patchProfileUseCase: PatchProfileUseCase,
    savedStateHandle: SavedStateHandle
) : BaseViewModel<NicknameUIState, NicknameSideEffect, NicknameIntent>(savedStateHandle) {

    override fun createInitialState(savedStateHandle: SavedStateHandle): NicknameUIState = NicknameUIState()

    override fun handleClientException(throwable: Throwable) {
        analyticsHelper.e(
            throwable = throwable,
            logVariable = currentState.toLoggingElements(),
        )
    }

    override suspend fun handleIntent(intent: NicknameIntent) {
        when (intent) {
            is NicknameIntent.OnClickBack -> onClickBack()

            is NicknameIntent.OnClickConfirm -> onClickConfirm()

            is NicknameIntent.OnValueChanged -> onValueChanged(intent)
        }
    }

    private fun onClickBack() {
        postSideEffect(NicknameSideEffect.NavigateToBack)
    }

    private fun onClickConfirm() {
        reduce {
            copy(
                isLoading = true
            )
        }

        launch {
            val isAllow = isVerifiedNicknameUseCase(currentState.nickname).getOrNull()

            if (isAllow == null) {
                analyticsHelper.d(message = "isAllow is null")
                reduce {
                    copy(isLoading = false)
                }
                return@launch
            }

            when (isAllow) {
                true -> registerNickname()

                false -> {
                    analyticsHelper.d(message = "isAllow is false")

                    reduce {
                        copy(
                            isLoading = false,
                            isDuplicatedNickname = true
                        )
                    }
                }
            }
        }
    }

    private fun onValueChanged(intent: NicknameIntent.OnValueChanged) {
        reduce {
            copy(
                nickname = intent.nickname,
                isDuplicatedNickname = false,
            )
        }
    }

    private fun registerNickname() {
        launch {
            if (currentState.isDuplicatedNickname) {
                analyticsHelper.d(message = "nickname[${currentState.nickname}] is duplicated")
                reduce {
                    copy(
                        isLoading = false,
                        isDuplicatedNickname = true
                    )
                }
                return@launch
            }

            val currentNickname = currentState.nickname

            val isPatchedNickname = patchProfileUseCase(currentNickname).getOrNull()

            if (isPatchedNickname == null) {
                analyticsHelper.d(message = "isRegister is null")
                reduce {
                    copy(
                        isLoading = false,
                    )
                }
                return@launch
            }

            when (isPatchedNickname) {
                true -> postSideEffect(NicknameSideEffect.NavigateToBirth)

                false -> {
                    analyticsHelper.d(message = "nickame[${currentState.nickname}] post failed")
                    reduce {
                        copy(
                            isLoading = false,
                        )
                    }
                }
            }
        }
    }
}