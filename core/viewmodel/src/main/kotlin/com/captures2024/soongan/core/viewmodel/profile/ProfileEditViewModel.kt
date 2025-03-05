package com.captures2024.soongan.core.viewmodel.profile

import androidx.lifecycle.SavedStateHandle
import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.analytics.utils.LogElementArgument
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
import com.captures2024.soongan.core.domain.usecase.members.PatchProfileUseCase
import com.captures2024.soongan.core.model.exception.NetworkExceptionWrapper
import com.captures2024.soongan.core.viewmodel.NewBaseViewModel
import com.captures2024.soongan.core.viewmodel.model.profile.EditingProfileState
import com.captures2024.soongan.core.viewmodel.model.profile.UserProfile
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileEditViewModel
@Inject
constructor(
    private val getCurrentMemberFlowUseCase: GetCurrentMemberFlowUseCase,
    private val patchProfileUseCase: PatchProfileUseCase,
    private val isVerifiedNicknameUseCase: IsVerifiedNicknameUseCase,
    analyticsHelper: AnalyticsHelper,
    showLoadingUseCase: ShowLoadingUseCase,
    hideLoadingUseCase: HideLoadingUseCase,
    clearLoadingUseCase: ClearLoadingUseCase,
    getIsCurrentGuestModeUseCase: GetIsCurrentGuestModeUseCase,
    setIsShowGuestModeDialogFlowUseCase: SetIsShowGuestModeDialogFlowUseCase,
    savedStateHandle: SavedStateHandle,
) : NewBaseViewModel<ProfileEditViewModel.State, ProfileEditViewModel.Effect, ProfileEditViewModel.Intent>(
    analyticsHelper = analyticsHelper,
    showLoadingUseCase = showLoadingUseCase,
    hideLoadingUseCase = hideLoadingUseCase,
    clearLoadingUseCase = clearLoadingUseCase,
    getIsCurrentGuestModeUseCase = getIsCurrentGuestModeUseCase,
    setIsShowGuestModeDialogFlowUseCase = setIsShowGuestModeDialogFlowUseCase,
    savedStateHandle = savedStateHandle
) {

    data class State(
        val isLoading: Boolean = false,
        val userProfile: UserProfile = UserProfile(),
        val editingState: EditingProfileState = EditingProfileState(),
        val isOpenBottomSheet: Boolean = false,
    ) : UIState {

        override fun toLoggingElements(): Array<LogElementArgument> = arrayOf(
            LogElementArgument("isLoading", isLoading.toString()),
            LogElementArgument("userProfile", userProfile.toString()),
            LogElementArgument("editingState", editingState.toString()),
            LogElementArgument("isOpenBottomSheet", isOpenBottomSheet.toString()),
        )
    }

    sealed interface Effect : UISideEffect {

        data object NavigateToBack : Effect

        data object OpenMediaPicker : Effect
    }


    sealed interface Intent : UIIntent {

        data object Init : Intent

        data object OnBackPressed : Intent

        data object OnClickProfileImage : Intent

        data object OnChangeDefaultProfileImage : Intent

        data object OpenPhotoPicker : Intent

        data object OnCloseEditBottomSheet : Intent

        data class OnProfileImageChanged(
            val newProfileImage: String,
        ) : Intent

        data class OnNicknameChanged(
            val newNickname: String,
        ) : Intent

        data class OnIntroductionChanged(
            val newIntroduction: String,
        ) : Intent

        data object OnClickEditButton : Intent
    }

    init {
        intent(Intent.Init)
    }

    override fun createInitialState(savedStateHandle: SavedStateHandle): State {
        return State()
    }

    override fun handleClientException(throwable: Throwable) {
        analyticsHelper.e(
            throwable = throwable,
            logVariable = currentState.toLoggingElements(),
            message = "handleClientException"
        )
    }

    override fun handleIntent(intent: Intent) {
        when (intent) {
            is Intent.Init -> handleInit()
            is Intent.OnBackPressed -> handleOnBackPressed()
            is Intent.OnChangeDefaultProfileImage -> blockGuestModeLogic { handleOnChangeDefaultProfileImage() }
            is Intent.OnClickEditButton -> blockGuestModeLogic { loadingLaunch { handleOnClickEditButton() } }
            is Intent.OnClickProfileImage -> blockGuestModeLogic { handleOnClickProfileImage() }
            is Intent.OnCloseEditBottomSheet -> handleOnCloseEditBottomSheet()
            is Intent.OnIntroductionChanged -> blockGuestModeLogic { handleOnIntroductionChanged(intent) }
            is Intent.OnNicknameChanged -> blockGuestModeLogic { handleOnNicknameChanged(intent) }
            is Intent.OnProfileImageChanged -> blockGuestModeLogic { handleOnProfileImageChanged(intent) }
            is Intent.OpenPhotoPicker -> blockGuestModeLogic { handleOpenPhotoPicker() }
        }
    }

    private fun handleInit() {
        launch { collectMemberInfo() }
    }

    private fun handleOnBackPressed() {
        postSideEffect(Effect.NavigateToBack)
    }

    private fun handleOnChangeDefaultProfileImage() {
        reduce {
            copy(
                editingState = editingState.copy(
                    editingProfile = editingState.editingProfile.copy(
                        profileImageUrl = null
                    )
                )
            )
        }

        dismissBottomSheet()
        updateEditableState()
    }

    private suspend fun handleOnClickEditButton() {
        val editedProfile = currentState.editingState.editingProfile

        try {
            val isPatchedProfile = patchProfileUseCase(
                nickname = editedProfile.nickname,
                selfIntroduction = editedProfile.selfIntroduction,
                profileImageUrl = editedProfile.profileImageUrl,
                isDefaultProfileImage = (editedProfile.profileImageUrl == null),
            ).getOrThrow()

            analyticsHelper.d(message = "Patch Profile result : $isPatchedProfile")

            reduce { copy(isOpenBottomSheet = false) }

            postSideEffect(Effect.NavigateToBack)

        } catch (e: NetworkExceptionWrapper) {
            reduce {
                copy(
                    editingState = editingState.copy(
                        isEditable = false
                    )
                )
            }

            if (e.statusCode == 704) {
                reduce {
                    copy(
                        editingState = editingState.copy(
                            isDuplicatedNickname = true
                        )
                    )
                }
            }
        }
    }

    private fun handleOnClickProfileImage() {
        openBottomSheet()
    }

    private fun handleOnCloseEditBottomSheet() {
        dismissBottomSheet()
    }

    private fun handleOnIntroductionChanged(intent: Intent.OnIntroductionChanged) {
        reduce {
            copy(
                editingState = editingState.copy(
                    editingProfile = editingState.editingProfile.copy(
                        selfIntroduction = intent.newIntroduction
                    )
                )
            )
        }

        updateEditableState()
    }

    private fun handleOnNicknameChanged(intent: Intent.OnNicknameChanged) {
        reduce {
            copy(
                editingState = editingState.copy(
                    editingProfile = editingState.editingProfile.copy(
                        nickname = intent.newNickname
                    ),
                    isDuplicatedNickname = false
                )
            )
        }

        updateEditableState()
    }

    private fun handleOnProfileImageChanged(intent: Intent.OnProfileImageChanged) {
        reduce {
            copy(
                editingState = editingState.copy(
                    editingProfile = editingState.editingProfile.copy(
                        profileImageUrl = intent.newProfileImage
                    )
                )
            )
        }

        updateEditableState()
    }

    private fun handleOpenPhotoPicker() {
        dismissBottomSheet()
        postSideEffect(Effect.OpenMediaPicker)
    }

    private suspend fun collectMemberInfo() {
        getCurrentMemberFlowUseCase().collect { currentMember ->

            analyticsHelper.d(message = "currentMember Update? - currentMember: $currentMember")

            val userProfile = UserProfile(
                nickname = currentMember?.nickname ?: "user1",
                selfIntroduction = currentMember?.selfIntroduction ?: "본인을 소개해주세요",
                profileImageUrl = currentMember?.profileImageUrl,
            )

            reduce {
                copy(
                    userProfile = userProfile,
                    editingState = editingState.copy(editingProfile = userProfile)
                )
            }
        }
    }

    private fun updateEditableState() {
        val isEditable = (currentState.userProfile != currentState.editingState.editingProfile)

        reduce {
            copy(
                editingState = editingState.copy(
                    isEditable = isEditable
                )
            )
        }
    }

    private fun openBottomSheet() {
        reduce {
            copy(
                isOpenBottomSheet = true
            )
        }
    }

    private fun dismissBottomSheet() {
        reduce {
            copy(
                isOpenBottomSheet = false
            )
        }
    }
}
