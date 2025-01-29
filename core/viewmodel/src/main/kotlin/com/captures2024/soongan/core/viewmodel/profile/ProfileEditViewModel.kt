package com.captures2024.soongan.core.viewmodel.profile

import androidx.lifecycle.SavedStateHandle
import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.analytics.utils.LogElementArgument
import com.captures2024.soongan.core.common.base.BaseViewModel
import com.captures2024.soongan.core.common.base.UIIntent
import com.captures2024.soongan.core.common.base.UISideEffect
import com.captures2024.soongan.core.common.base.UIState
import com.captures2024.soongan.core.domain.usecase.members.GetCurrentMemberFlowUseCase
import com.captures2024.soongan.core.domain.usecase.members.IsVerifiedNicknameUseCase
import com.captures2024.soongan.core.domain.usecase.members.PatchProfileUseCase
import com.captures2024.soongan.core.viewmodel.model.profile.EditingProfileState
import com.captures2024.soongan.core.viewmodel.model.profile.UserProfile
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileEditViewModel
@Inject
constructor(
    private val analyticsHelper: AnalyticsHelper,
    private val getCurrentMemberFlowUseCase: GetCurrentMemberFlowUseCase,
    private val patchProfileUseCase: PatchProfileUseCase,
    private val isVerifiedNicknameUseCase: IsVerifiedNicknameUseCase,
    savedStateHandle: SavedStateHandle,
) : BaseViewModel<ProfileEditViewModel.State, ProfileEditViewModel.Effect, ProfileEditViewModel.Intent>(
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

    override suspend fun handleIntent(intent: Intent) {
        when (intent) {
            Intent.Init -> initSet()

            Intent.OnBackPressed -> postSideEffect(Effect.NavigateToBack)

            Intent.OnChangeDefaultProfileImage -> onClickDefaultProfileImage()

            Intent.OnClickEditButton -> onClickEditButton()

            Intent.OnClickProfileImage -> reduce { copy(isOpenBottomSheet = true) }

            Intent.OnCloseEditBottomSheet -> onCloseEditBottomSheet()

            is Intent.OnIntroductionChanged -> onIntroductionChanged(intent)

            is Intent.OnNicknameChanged -> onNicknameChanged(intent)

            is Intent.OnProfileImageChanged -> onProfileImageChanged(intent)

            Intent.OpenPhotoPicker -> openPhotoPicker()
        }
    }

    private suspend fun initSet() {
        getCurrentMemberFlowUseCase().collect { currentMember ->
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

    private fun onClickDefaultProfileImage() {
        reduce {
            copy(
                editingState = editingState.copy(
                    editingProfile = editingState.editingProfile.copy(
                        profileImageUrl = null
                    )
                )
            )
        }
        onCloseEditBottomSheet()
        updateEditableState()
    }

    private fun openPhotoPicker() {
        onCloseEditBottomSheet()
        postSideEffect(Effect.OpenMediaPicker)
    }

    private fun onCloseEditBottomSheet() {
        reduce {
            copy(
                isOpenBottomSheet = false
            )
        }
    }

    private fun onProfileImageChanged(intent: Intent.OnProfileImageChanged) {
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

    private fun onNicknameChanged(intent: Intent.OnNicknameChanged) {
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

    private fun onIntroductionChanged(intent: Intent.OnIntroductionChanged) {
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

    private fun onClickEditButton() = launch {
        val editedProfile = currentState.editingState.editingProfile
        val isAllowNickname = isVerifiedNicknameUseCase(editedProfile.nickname).getOrNull()

        analyticsHelper.d(message = "isAllowNickname : $isAllowNickname")

        when (isAllowNickname) {
            true -> {
                val isPatchedProfile = patchProfileUseCase(
                    nickname = editedProfile.nickname,
                    selfIntroduction = editedProfile.selfIntroduction,
                    profileImage = editedProfile.profileImageUrl,
                ).onSuccess {
                    reduce {
                        copy(
                            userProfile = editedProfile,
                            isOpenBottomSheet = false
                        )
                    }
                }

                analyticsHelper.d(message = "Patch Profile result : $isPatchedProfile")

                postSideEffect(Effect.NavigateToBack)
            }

            false -> {
                reduce {
                    copy(
                        editingState = editingState.copy(
                            isEditable = false,
                            isDuplicatedNickname = true
                        )
                    )
                }
            }

            null -> {
                reduce {
                    copy(
                        editingState = editingState.copy(
                            isEditable = false
                        )
                    )
                }

//                Toast.makeText(context, "not statusCode 200", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
