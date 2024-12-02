package com.captures2024.soongan.feature.profile

import androidx.lifecycle.SavedStateHandle
import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.common.base.BaseViewModel
import com.captures2024.soongan.core.model.UserPost
import com.captures2024.soongan.feature.profile.state.profile.ProfileIntent
import com.captures2024.soongan.feature.profile.state.profile.ProfileSideEffect
import com.captures2024.soongan.feature.profile.state.profile.ProfileUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class ProfileViewModel
@Inject
constructor(
    private val analyticsHelper: AnalyticsHelper,
    savedStateHandle: SavedStateHandle,
) : BaseViewModel<ProfileUiState, ProfileSideEffect, ProfileIntent>(savedStateHandle = savedStateHandle) {

    override fun createInitialState(savedStateHandle: SavedStateHandle): ProfileUiState {
        return ProfileUiState()
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
            is ProfileIntent.OnClickPhoto -> onClickPhoto(intent.userPhoto)

            ProfileIntent.OnClickNotification -> onClickNotification()

            ProfileIntent.OnClickMenu -> onClickMenu()

            ProfileIntent.OnClickEdit -> onClickEdit()

            ProfileIntent.OnClickNotificationSetting -> TODO()

            ProfileIntent.OnClickTermsAndPolicy -> TODO()

            ProfileIntent.OnClickFAQ -> TODO()

            ProfileIntent.OnClickWithdraw -> TODO()

            ProfileIntent.OnClickSignOut -> TODO()

            ProfileIntent.OnCloseBottomSheet -> onCloseBottomSheet()
        }
    }

    private fun onClickPhoto(userPhoto: UserPost.PhotoPost) {
        postSideEffect(ProfileSideEffect.NavigateToHomePost(userPhoto))
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
        postSideEffect(ProfileSideEffect.NavigateToNotification)
    }

    private fun onClickEdit() {
        postSideEffect(ProfileSideEffect.NavigateToEditProfile(currentState.userProfile))
    }

    private fun onCloseBottomSheet() {
        reduce {
            copy(isOpenBottomSheet = false)
        }
    }
}