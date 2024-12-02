package com.captures2024.soongan.feature.profile

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.common.base.BaseViewModel
import com.captures2024.soongan.core.navigator.screen.main.profile.EditProfileNavigator
import com.captures2024.soongan.feature.profile.state.edit.EditProfileIntent
import com.captures2024.soongan.feature.profile.state.edit.EditProfileSideEffect
import com.captures2024.soongan.feature.profile.state.edit.EditProfileUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class EditProfileViewModel
@Inject
constructor(
    private val analyticsHelper: AnalyticsHelper,
    savedStateHandle: SavedStateHandle,
) : BaseViewModel<EditProfileUiState, EditProfileSideEffect, EditProfileIntent>(savedStateHandle = savedStateHandle) {

    override fun createInitialState(savedStateHandle: SavedStateHandle): EditProfileUiState {
        val userProfile = savedStateHandle.toRoute<EditProfileNavigator>()

        return EditProfileUiState(
            profileImage = userProfile.image,
            nickname = userProfile.nickname,
            selfIntroduction = userProfile.selfIntroduction
        )
    }

    override fun handleClientException(throwable: Throwable) {
        analyticsHelper.e(
            throwable = throwable,
            logVariable = currentState.toLoggingElements(),
            message = "handleClientException"
        )
    }

    override suspend fun handleIntent(intent: EditProfileIntent) {
        analyticsHelper.d(message = "handleIntent - intent: $intent")

        when (intent) {
            EditProfileIntent.OnBackPressed -> postSideEffect(EditProfileSideEffect.NavigateToBack)

            EditProfileIntent.OnClickProfileImage -> postSideEffect(EditProfileSideEffect.OpenMediaPicker)

            is EditProfileIntent.OnProfileImageChanged -> onProfileImageChanged(intent.newProfileImage)

            is EditProfileIntent.OnNicknameChanged -> onNicknameChanged(intent.newNickname)

            is EditProfileIntent.OnIntroductionChanged -> onIntroductionChanged(intent.newIntroduction)

            EditProfileIntent.OnClickEditButton -> onClickEditButton()
        }
    }

    private fun onProfileImageChanged(newProfileImage: String) {
        TODO("manage buttonEnabled")

        reduce {
            copy(profileImage = newProfileImage)
        }
    }

    private fun onIntroductionChanged(newIntroduction: String) {
        TODO("validate & manage buttonEnabled")

        reduce {
            copy(nickname = newIntroduction)
        }
    }

    private fun onNicknameChanged(newNickname: String) {
        reduce {
            copy(nickname = newNickname)
        }

        TODO("validate & manage buttonEnabled")
    }

    private fun onClickEditButton() {
        val currentUserProfile = currentState

        TODO("update userProfile remote")

        postSideEffect(EditProfileSideEffect.NavigateToBack)
    }
}
