package com.captures2024.soongan.core.viewmodel.sign

import androidx.lifecycle.SavedStateHandle
import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.analytics.utils.LogElementArgument
import com.captures2024.soongan.core.common.Validation
import com.captures2024.soongan.core.common.base.UIIntent
import com.captures2024.soongan.core.common.base.UISideEffect
import com.captures2024.soongan.core.common.base.UIState
import com.captures2024.soongan.core.domain.usecase.loading.ClearLoadingUseCase
import com.captures2024.soongan.core.domain.usecase.loading.HideLoadingUseCase
import com.captures2024.soongan.core.domain.usecase.loading.ShowLoadingUseCase
import com.captures2024.soongan.core.domain.usecase.members.IsVerifiedNicknameUseCase
import com.captures2024.soongan.core.domain.usecase.members.PatchProfileUseCase
import com.captures2024.soongan.core.viewmodel.NewBaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NicknameViewModel
@Inject
constructor(
    private val isVerifiedNicknameUseCase: IsVerifiedNicknameUseCase,
    private val patchProfileUseCase: PatchProfileUseCase,
    analyticsHelper: AnalyticsHelper,
    showLoadingUseCase: ShowLoadingUseCase,
    hideLoadingUseCase: HideLoadingUseCase,
    clearLoadingUseCase: ClearLoadingUseCase,
    savedStateHandle: SavedStateHandle,
) : NewBaseViewModel<NicknameViewModel.State, NicknameViewModel.Effect, NicknameViewModel.Intent>(
    analyticsHelper = analyticsHelper,
    showLoadingUseCase = showLoadingUseCase,
    hideLoadingUseCase = hideLoadingUseCase,
    clearLoadingUseCase = clearLoadingUseCase,
    savedStateHandle = savedStateHandle,
) {

    data class State(
        val nickname: String = "",
        val maxNicknameLength: Int = 10,
        val isDuplicatedNickname: Boolean = false,
    ) : UIState {
        val isValid: Validation.NicknameValidState
            get() = Validation.isValidNickname(nickname)

        override fun toLoggingElements(): Array<LogElementArgument> = arrayOf(
            LogElementArgument("nickname", nickname),
            LogElementArgument("maxNicknameLength", maxNicknameLength.toString()),
            LogElementArgument("isDuplicatedNickname", isDuplicatedNickname.toString()),
            LogElementArgument("isValid", isValid.toString()),
        )
    }

    sealed interface Effect : UISideEffect {
        data object NavigateToBack : Effect

        data object NavigateToBirth : Effect
    }

    sealed interface Intent : UIIntent {
        data object OnClickBack : Intent

        data class OnNicknameValueChanged(
            val newValue: String,
        ) : Intent

        data object OnClickConfirm : Intent
    }

    override fun createInitialState(savedStateHandle: SavedStateHandle): State = State()

    override fun handleClientException(throwable: Throwable) {
        analyticsHelper.e(throwable)
    }

    override fun handleIntent(intent: Intent) {
        when (intent) {
            is Intent.OnClickBack -> handleOnClickBack()

            is Intent.OnNicknameValueChanged -> handleOnNicknameValueChanged(intent)

            is Intent.OnClickConfirm -> loadingLaunch { handleOnClickConfirm() }
        }
    }

    private fun handleOnClickBack() {
        postSideEffect(Effect.NavigateToBack)
    }

    private fun handleOnNicknameValueChanged(intent: Intent.OnNicknameValueChanged) {
        if (intent.newValue.length !in 0 .. currentState.maxNicknameLength) {
            return
        }

        reduce {
            copy(
                nickname = intent.newValue,
                isDuplicatedNickname = false,
            )
        }
    }

    private suspend fun handleOnClickConfirm() {
        val isAllow = isVerifiedNicknameUseCase(currentState.nickname).getOrNull()

        if (isAllow == null) {
            analyticsHelper.d(message = "isAllow is null")
            return
        }

        when (isAllow) {
            true -> registerNickname()

            false -> {
                analyticsHelper.d(message = "isAllow is false")

                reduce {
                    copy(
                        isDuplicatedNickname = true,
                    )
                }
            }
        }
    }

    private suspend fun registerNickname() {
        if (currentState.isDuplicatedNickname) {
            analyticsHelper.d(message = "nickname[${currentState.nickname}] is duplicated")
            reduce {
                copy(
                    isDuplicatedNickname = true,
                )
            }
            return
        }

        val currentNickname = currentState.nickname

        val isPatchedNickname = patchProfileUseCase(currentNickname).getOrNull()

        if (isPatchedNickname == null) {
            analyticsHelper.d(message = "isRegister is null")
            return
        }

        when (isPatchedNickname) {
            true -> postSideEffect(Effect.NavigateToBirth)

            false -> {
                analyticsHelper.d(message = "nickame[${currentState.nickname}] post failed")
            }
        }
    }
}
