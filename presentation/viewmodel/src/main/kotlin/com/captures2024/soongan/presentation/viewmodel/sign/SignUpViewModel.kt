package com.captures2024.soongan.presentation.viewmodel.sign

import androidx.lifecycle.SavedStateHandle
import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.common.Validation
import com.captures2024.soongan.core.common.Validation.BirthYearValidState
import com.captures2024.soongan.core.common.base.UIIntent
import com.captures2024.soongan.core.common.base.UISideEffect
import com.captures2024.soongan.core.common.base.UIState
import com.captures2024.soongan.core.domain.usecase.dialog.SetIsShowGuestModeDialogFlowUseCase
import com.captures2024.soongan.core.domain.usecase.loading.ClearLoadingUseCase
import com.captures2024.soongan.core.domain.usecase.loading.HideLoadingUseCase
import com.captures2024.soongan.core.domain.usecase.loading.ShowLoadingUseCase
import com.captures2024.soongan.core.domain.usecase.members.GetCurrentMemberFlowUseCase
import com.captures2024.soongan.core.domain.usecase.members.GetIsCurrentGuestModeUseCase
import com.captures2024.soongan.core.domain.usecase.members.IsVerifiedNicknameUseCase
import com.captures2024.soongan.core.domain.usecase.members.PatchBirthYearUseCase
import com.captures2024.soongan.core.domain.usecase.members.PatchProfileUseCase
import com.captures2024.soongan.core.domain.usecase.token.ClearAllTokenUseCase
import com.captures2024.soongan.core.model.AppConst
import com.captures2024.soongan.presentation.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.filterNotNull
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel
@Inject
constructor(
    analyticsHelper: AnalyticsHelper,
    showLoadingUseCase: ShowLoadingUseCase,
    hideLoadingUseCase: HideLoadingUseCase,
    clearLoadingUseCase: ClearLoadingUseCase,
    getIsCurrentGuestModeUseCase: GetIsCurrentGuestModeUseCase,
    setIsShowGuestModeDialogFlowUseCase: SetIsShowGuestModeDialogFlowUseCase,
    savedStateHandle: SavedStateHandle,
    private val getCurrentMemberFlowUseCase: GetCurrentMemberFlowUseCase,
    private val clearAllTokenUseCase: ClearAllTokenUseCase,
    private val isVerifiedNicknameUseCase: IsVerifiedNicknameUseCase,
    private val patchProfileUseCase: PatchProfileUseCase,
    private val patchBirthYearUseCase: PatchBirthYearUseCase,
) : BaseViewModel<SignUpViewModel.State, SignUpViewModel.Effect, SignUpViewModel.Intent>(
    analyticsHelper = analyticsHelper,
    showLoadingUseCase = showLoadingUseCase,
    hideLoadingUseCase = hideLoadingUseCase,
    clearLoadingUseCase = clearLoadingUseCase,
    getIsCurrentGuestModeUseCase = getIsCurrentGuestModeUseCase,
    setIsShowGuestModeDialogFlowUseCase = setIsShowGuestModeDialogFlowUseCase,
    savedStateHandle = savedStateHandle,
) {

    data class State(
        val nicknameState: NicknameState,
        val birthState: BirthState,
    ) : UIState {

        data class NicknameState(
            val nickname: String,
            val maxNicknameLength: Int,
            val isDuplicatedNickname: Boolean,
            val isRemoteSuccess: Boolean,
        ) {
            val isValid: Validation.NicknameValidState
                get() = Validation.isValidNickname(nickname)

            override fun toString(): String {
                return "NicknameState(nickname='$nickname', maxNicknameLength=$maxNicknameLength, isDuplicatedNickname=$isDuplicatedNickname, isValid=$isValid)"
            }
        }

        data class BirthState(
            val birthYear: String,
            val maxBirthLength: Int,
        ) {
            val isValid: BirthYearValidState
                get() = Validation.isValidBirthYear(birthYear)

            override fun toString(): String {
                return "BirthState(birthYear='$birthYear', maxBirthLength=$maxBirthLength, isValid=$isValid)"
            }
        }
    }

    sealed interface Effect : UISideEffect {
        data object NavigateToBack : Effect
    }

    sealed interface Intent : UIIntent {
        data object Init : Intent

        data object OnClickBack : Intent

        data class OnNicknameValueChanged(
            val newValue: String,
        ) : Intent

        data class OnBirthValueChanged(
            val newValue: String,
        ) : Intent

        data object OnConfirmNickname : Intent

        data object OnConfirmBirth : Intent
    }

    init {
        intent(Intent.Init)
    }

    override fun createInitialState(savedStateHandle: SavedStateHandle): State = State(
        nicknameState = State.NicknameState(
            nickname = AppConst.EMPTY_STRING,
            maxNicknameLength = AppConst.Sign.SignUp.MAX_NICKNAME_LENGTH,
            isDuplicatedNickname = false,
            isRemoteSuccess = false,
        ),
        birthState = State.BirthState(
            birthYear = AppConst.EMPTY_STRING,
            maxBirthLength = AppConst.Sign.SignUp.MAX_BIRTH_LENGTH,
        ),
    )

    override fun handleIntent(intent: Intent) {
        when (intent) {
            is Intent.Init -> handleInit()
            is Intent.OnClickBack -> loadingLaunch { handleOnClickBack() }
            is Intent.OnNicknameValueChanged -> handleOnNicknameValueChanged(intent)
            is Intent.OnBirthValueChanged -> handleOnBirthValueChanged(intent)
            is Intent.OnConfirmNickname -> loadingLaunch { handleOnConfirmNickname() }
            is Intent.OnConfirmBirth -> loadingLaunch { handleOnConfirmBirth() }
        }
    }

    override fun handleClientException(throwable: Throwable) {
        analyticsHelper.e(throwable) { "currentState: $currentState" }
    }

    private fun handleInit() {
        launch { collectMemberInfo() }
    }

    private suspend fun handleOnClickBack() {
        clearAllTokenUseCase()
        postSideEffect(Effect.NavigateToBack)
    }

    private fun handleOnNicknameValueChanged(intent: Intent.OnNicknameValueChanged) {
        if (currentState.nicknameState.isRemoteSuccess) {
            return
        }

        val maxNicknameLength = currentState.nicknameState.maxNicknameLength
        val value = when (intent.newValue.length > maxNicknameLength) {
            true -> intent.newValue.substring(0, maxNicknameLength)
            false -> intent.newValue
        }

        reduce {
            copy(
                nicknameState = nicknameState.copy(
                    nickname = value,
                    isDuplicatedNickname = false,
                    isRemoteSuccess = false,
                ),
            )
        }
    }

    private fun handleOnBirthValueChanged(intent: Intent.OnBirthValueChanged) {
        reduce {
            copy(
                birthState = birthState.copy(
                    birthYear = intent.newValue,
                ),
            )
        }
    }

    private suspend fun handleOnConfirmNickname() {
        val nickname = currentState.nicknameState.nickname

        val isAllow = isVerifiedNicknameUseCase(nickname).getOrNull()

        if (isAllow == null) {
            analyticsHelper.d { "isAllow is null" }
            return
        }

        when (isAllow) {
            true -> {
                reduce {
                    copy(
                        nicknameState = nicknameState.copy(
                            isDuplicatedNickname = false,
                        ),
                    )
                }

                registerNickname(nickname)
            }

            false -> {
                analyticsHelper.d { "isAllow is false" }

                reduce {
                    copy(
                        nicknameState = nicknameState.copy(
                            isDuplicatedNickname = true,
                            isRemoteSuccess = false,
                        ),
                    )
                }
            }
        }
    }

    private suspend fun handleOnConfirmBirth() {
        val birthState = currentState.birthState

        if (birthState.isValid != BirthYearValidState.Success) {
            return
        }

        val birthYear = birthState.birthYear.toIntOrNull() ?: return

        patchBirthYearUseCase(birthYear = birthYear).getOrNull()
    }

    private suspend fun collectMemberInfo() {
        getCurrentMemberFlowUseCase.invoke()
            .filterNotNull()
            .collect { memberInfo ->
                memberInfo.nickname?.let {
                    reduce {
                        copy(
                            nicknameState = nicknameState.copy(
                                nickname = it,
                                isRemoteSuccess = true,
                            ),
                        )
                    }
                }

                memberInfo.birthYear?.let {
                    reduce {
                        copy(
                            birthState = birthState.copy(birthYear = it.toString()),
                        )
                    }
                }
            }
    }

    private suspend fun registerNickname(nickname: String) {
        val isPatchedNickname = patchProfileUseCase(nickname).getOrNull()

        if (isPatchedNickname == null) {
            analyticsHelper.d { "isRegister is null" }
            return
        }

        reduce {
            copy(
                nicknameState = nicknameState.copy(
                    isRemoteSuccess = true,
                ),
            )
        }
    }
}
