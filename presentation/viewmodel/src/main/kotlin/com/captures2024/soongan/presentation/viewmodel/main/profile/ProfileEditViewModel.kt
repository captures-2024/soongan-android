package com.captures2024.soongan.presentation.viewmodel.main.profile

import androidx.lifecycle.SavedStateHandle
import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.common.Validation
import com.captures2024.soongan.core.common.base.UIIntent
import com.captures2024.soongan.core.common.base.UISideEffect
import com.captures2024.soongan.core.common.base.UIState
import com.captures2024.soongan.core.domain.usecase.dialog.PostSingleButtonDialogUseCase
import com.captures2024.soongan.core.domain.usecase.dialog.SetIsShowGuestModeDialogFlowUseCase
import com.captures2024.soongan.core.domain.usecase.loading.ClearLoadingUseCase
import com.captures2024.soongan.core.domain.usecase.loading.HideLoadingUseCase
import com.captures2024.soongan.core.domain.usecase.loading.ShowLoadingUseCase
import com.captures2024.soongan.core.domain.usecase.members.GetCurrentMemberFlowUseCase
import com.captures2024.soongan.core.domain.usecase.members.GetIsCurrentGuestModeUseCase
import com.captures2024.soongan.core.domain.usecase.members.PatchProfileUseCase
import com.captures2024.soongan.core.model.AppConst
import com.captures2024.soongan.core.model.enums.CommonDialogType
import com.captures2024.soongan.core.model.exception.NetworkExceptionWrapper
import com.captures2024.soongan.presentation.viewmodel.BaseViewModel
import com.captures2024.soongan.presentation.viewmodel.model.UserProfile
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileEditViewModel
@Inject
constructor(
    analyticsHelper: AnalyticsHelper,
    showLoadingUseCase: ShowLoadingUseCase,
    hideLoadingUseCase: HideLoadingUseCase,
    clearLoadingUseCase: ClearLoadingUseCase,
    getIsCurrentGuestModeUseCase: GetIsCurrentGuestModeUseCase,
    setIsShowGuestModeDialogFlowUseCase: SetIsShowGuestModeDialogFlowUseCase,
    savedStateHandle: SavedStateHandle,
    private val postSingleButtonDialogUseCase: PostSingleButtonDialogUseCase,
    private val getCurrentMemberFlowUseCase: GetCurrentMemberFlowUseCase,
    private val patchProfileUseCase: PatchProfileUseCase,
) : BaseViewModel<ProfileEditViewModel.State, ProfileEditViewModel.Effect, ProfileEditViewModel.Intent>(
    analyticsHelper = analyticsHelper,
    showLoadingUseCase = showLoadingUseCase,
    hideLoadingUseCase = hideLoadingUseCase,
    clearLoadingUseCase = clearLoadingUseCase,
    getIsCurrentGuestModeUseCase = getIsCurrentGuestModeUseCase,
    setIsShowGuestModeDialogFlowUseCase = setIsShowGuestModeDialogFlowUseCase,
    savedStateHandle = savedStateHandle,
) {

    data class State(
        val userProfile: UserProfile,
        val editingState: EditingProfileState,
        val isShowEditBottomSheet: Boolean,
        val maxNicknameLength: Int = AppConst.Main.Profile.MAX_NICKNAME_LENGTH,
        val maxIntroductionLength: Int = AppConst.Main.Profile.MAX_INTRODUCTION_LENGTH,
    ) : UIState {
        val isEditable: Boolean
            get() = userProfile != editingState.editingProfile

        data class EditingProfileState(
            val editingProfile: UserProfile = UserProfile(),
            val isDuplicatedNickname: Boolean = false,
        ) {
            val isValidNickname: Validation.NicknameValidState
                get() = Validation.isValidNickname(editingProfile.nickname)

            val isValidIntroduction: Validation.IntroductionValidState
                get() = Validation.isValidSelfIntroduction(editingProfile.selfIntroduction)
        }
    }

    sealed interface Effect : UISideEffect {

        data object NavigateToBack : Effect

        data object OpenMediaPicker : Effect
    }

    sealed interface Intent : UIIntent {
        data object Init : Intent

        data object OnClickBack : Intent

        data object OnClickProfileImage : Intent

        data object OnChangeDefaultProfileImage : Intent

        data object OpenPhotoPicker : Intent

        data object OnDismissRequestEditBottomSheet : Intent

        data class OnProfileImageChanged(
            val newProfileImage: String,
        ) : Intent

        data class OnNicknameValueChanged(
            val newValue: String,
        ) : Intent

        data class OnIntroductionValueChanged(
            val newValue: String,
        ) : Intent

        data object OnClickEditButton : Intent
    }

    init {
        intent(Intent.Init)
    }

    override fun createInitialState(savedStateHandle: SavedStateHandle): State {
        return State(
            userProfile = UserProfile(),
            editingState = State.EditingProfileState(),
            isShowEditBottomSheet = false,
        )
    }

    override fun handleIntent(intent: Intent) {
        when (intent) {
            is Intent.Init -> handleInit()
            is Intent.OnClickBack -> handleOnClickBack()
            is Intent.OnClickProfileImage -> handleOnClickProfileImage()
            is Intent.OnChangeDefaultProfileImage -> handleOnChangeDefaultProfileImage()
            is Intent.OpenPhotoPicker -> handleOpenPhotoPicker()
            is Intent.OnDismissRequestEditBottomSheet -> handleOnDismissRequestEditBottomSheet()
            is Intent.OnProfileImageChanged -> handleOnProfileImageChanged(intent)
            is Intent.OnNicknameValueChanged -> handleOnNicknameValueChanged(intent)
            is Intent.OnIntroductionValueChanged -> handleOnIntroductionValueChanged(intent)
            is Intent.OnClickEditButton -> loadingLaunch { handleOnClickEditButton() }
        }
    }

    override fun handleClientException(throwable: Throwable) {
        analyticsHelper.e(throwable) { "state: $currentState" }
    }

    private fun handleInit() {
        launch { collectMemberInfo() }
    }

    private fun handleOnClickBack() {
        postSideEffect(Effect.NavigateToBack)
    }

    private fun handleOnClickProfileImage() {
        showEditBottomSheet()
    }

    private fun handleOnChangeDefaultProfileImage() {
        reduce {
            copy(
                editingState = editingState.copy(
                    editingProfile = editingState.editingProfile.copy(
                        profileImageUrl = null,
                    ),
                ),
            )
        }

        dismissEditBottomSheet()
    }

    private fun handleOpenPhotoPicker() {
        postSideEffect(Effect.OpenMediaPicker)
        dismissEditBottomSheet()
    }

    private fun handleOnDismissRequestEditBottomSheet() {
        dismissEditBottomSheet()
    }

    private fun handleOnProfileImageChanged(intent: Intent.OnProfileImageChanged) {
        reduce {
            copy(
                editingState = editingState.copy(
                    editingProfile = editingState.editingProfile.copy(
                        profileImageUrl = intent.newProfileImage,
                    ),
                ),
            )
        }
    }

    private fun handleOnNicknameValueChanged(intent: Intent.OnNicknameValueChanged) {
        reduce {
            copy(
                editingState = editingState.copy(
                    editingProfile = editingState.editingProfile.copy(
                        nickname = intent.newValue,
                    ),
                    isDuplicatedNickname = false,
                ),
            )
        }
    }

    private fun handleOnIntroductionValueChanged(intent: Intent.OnIntroductionValueChanged) {
        reduce {
            copy(
                editingState = editingState.copy(
                    editingProfile = editingState.editingProfile.copy(
                        selfIntroduction = intent.newValue,
                    ),
                ),
            )
        }
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

            analyticsHelper.d { "Patch Profile result : $isPatchedProfile" }

            postSideEffect(Effect.NavigateToBack)
        } catch (e: NetworkExceptionWrapper) {
            when (e.statusCode) {
                704 -> reduce {
                    copy(
                        editingState = editingState.copy(
                            isDuplicatedNickname = true,
                        ),
                    )
                }

                else -> postSingleButtonDialogUseCase(CommonDialogType.NETWORK_ERROR)
            }
        }
    }

    private suspend fun collectMemberInfo() {
        getCurrentMemberFlowUseCase().collect { currentMember ->
            analyticsHelper.d { "currentMember Update? - currentMember: $currentMember" }

            currentMember?.let {
                val userProfile = UserProfile(
                    nickname = currentMember.nickname ?: "user1",
                    selfIntroduction = currentMember.selfIntroduction ?: "본인을 소개해주세요",
                    profileImageUrl = currentMember.profileImageUrl,
                )

                reduce {
                    copy(
                        userProfile = userProfile,
                        editingState = editingState.copy(editingProfile = userProfile),
                    )
                }
            }
        }
    }

    private fun showEditBottomSheet() {
        reduce {
            copy(
                isShowEditBottomSheet = true,
            )
        }
    }

    private fun dismissEditBottomSheet() {
        reduce {
            copy(
                isShowEditBottomSheet = false,
            )
        }
    }
}
