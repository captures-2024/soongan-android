package com.captures2024.soongan.feature.profile

import androidx.lifecycle.SavedStateHandle
import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.common.base.BaseViewModel
import com.captures2024.soongan.core.domain.usecase.members.GetMemberInfoUseCase
import com.captures2024.soongan.core.domain.usecase.members.PatchProfileUseCase
import com.captures2024.soongan.core.model.UserProfile
import com.captures2024.soongan.feature.profile.state.profile.EditingState
import com.captures2024.soongan.feature.profile.state.profile.ProfileIntent
import com.captures2024.soongan.feature.profile.state.profile.ProfileIntent.BottomSheetI
import com.captures2024.soongan.feature.profile.state.profile.ProfileIntent.EditI
import com.captures2024.soongan.feature.profile.state.profile.ProfileIntent.ProfileI
import com.captures2024.soongan.feature.profile.state.profile.ProfileSideEffect
import com.captures2024.soongan.feature.profile.state.profile.ProfileSideEffect.BottomSheetSE
import com.captures2024.soongan.feature.profile.state.profile.ProfileSideEffect.EditSE
import com.captures2024.soongan.feature.profile.state.profile.ProfileSideEffect.ProfileSE
import com.captures2024.soongan.feature.profile.state.profile.ProfileUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class ProfileViewModel
@Inject
constructor(
    private val analyticsHelper: AnalyticsHelper,
    private val getMemberInfoUseCase: GetMemberInfoUseCase,
    private val patchProfileUseCase: PatchProfileUseCase,
    savedStateHandle: SavedStateHandle,
) : BaseViewModel<ProfileUIState, ProfileSideEffect, ProfileIntent>(savedStateHandle = savedStateHandle) {

    init {
        intent(ProfileI.Init)
    }

    override fun createInitialState(savedStateHandle: SavedStateHandle): ProfileUIState {
        return ProfileUIState()
    }

    override fun handleClientException(throwable: Throwable) {
        analyticsHelper.e(
            throwable = throwable,
            logVariable = currentState.toLoggingElements(),
            message = "handleClientException"
        )
    }

    override suspend fun handleIntent(intent: ProfileIntent) {
        when (intent) {
            is ProfileI -> handleProfileIntent(intent)

            is BottomSheetI -> handleBottomSheetIntent(intent)

            is EditI -> handleEditIntent(intent)
        }
    }

    private suspend fun handleProfileIntent(intent: ProfileI) {
        when (intent) {
            ProfileI.Init -> fetchUserProfile()

            ProfileI.OnClickMenu -> onClickMenu()

            ProfileI.OnClickNotification -> onClickNotification()

            is ProfileI.OnClickPhoto ->
                postSideEffect(ProfileSE.NavigateToHomePost(intent.userPhoto))
        }
    }

    private fun handleBottomSheetIntent(intent: BottomSheetI) {
        when (intent) {
            BottomSheetI.OnClickEdit -> onClickEdit()

            BottomSheetI.OnClickFAQ -> TODO()

            BottomSheetI.OnClickNotificationSetting -> TODO()

            BottomSheetI.OnClickSignOut -> TODO()

            BottomSheetI.OnClickTermsAndPolicy -> TODO()

            BottomSheetI.OnClickWithdraw -> TODO()

            BottomSheetI.OnCloseBottomSheet -> onCloseBottomSheet()
        }
    }

    private fun handleEditIntent(intent: EditI) {
        when (intent) {
            EditI.OnBackPressed ->
                postSideEffect(EditSE.NavigateToBack)

            EditI.OnClickEditButton -> onClickEditButton()

            EditI.OnClickProfileImage ->
                postSideEffect(EditSE.OpenMediaPicker)

            is EditI.OnIntroductionChanged -> TODO()

            is EditI.OnNicknameChanged -> TODO()

            is EditI.OnProfileImageChanged -> TODO()
        }
    }

    private suspend fun fetchUserProfile() = launch {
        val memberInfo = getMemberInfoUseCase().getOrNull()

        memberInfo?.let {
            val userProfile = UserProfile(
                nickname = it.nickname ?: "user1",
                selfIntroduction = it.selfIntroduction ?: "본인을 소개해주세요",
                profileImageUrl = it.profileImageUrl
            )

            reduce {
                copy(
                    userProfile = userProfile
                )
            }
        }
    }

    // Handle ProfileScreen Intent
    private fun onClickEdit() {
        reduce {
            copy(
                editingState = EditingState(editingProfile = currentState.userProfile)
            )
        }

        postSideEffect(BottomSheetSE.NavigateToEditProfile)
    }

    private fun onClickMenu() {
        reduce {
            copy(isOpenBottomSheet = true)
        }
    }

    private fun onClickNotification() {
        reduce {
            copy(hasNotification = false)
        }

        postSideEffect(ProfileSE.NavigateToNotification)
    }


    // Handle ProfileMenuBottomSheet - Intent
    private fun onCloseBottomSheet() {
        reduce {
            copy(isOpenBottomSheet = false)
        }
    }


    // Handle EditScreen - Intent
    private fun onProfileImageChanged(newProfileImage: String) {
        reduce {
            copy()
        }
    }

    private fun onIntroductionChanged(newIntroduction: String) {
        TODO("validate & manage buttonEnabled")

        reduce {
            copy()
        }
    }

    private fun onNicknameChanged(newNickname: String) {
        reduce {
            copy()
        }

        TODO("validate & manage buttonEnabled")
    }

    private fun onClickEditButton() = launch {
        val editedProfile = currentState.editingState.editingProfile

        patchProfileUseCase(
            nickname = editedProfile?.nickname,
            selfIntroduction = editedProfile?.selfIntroduction,
            profileImage = editedProfile?.profileImageUrl,
        )

        reduce {
            copy(
                userProfile = editedProfile ?: currentState.userProfile,
                editingState = EditingState()
            )
        }

        analyticsHelper.d()

        postSideEffect(EditSE.NavigateToBack)
    }
}