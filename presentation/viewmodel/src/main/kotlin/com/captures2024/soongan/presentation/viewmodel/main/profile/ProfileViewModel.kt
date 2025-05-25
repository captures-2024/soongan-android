package com.captures2024.soongan.presentation.viewmodel.main.profile

import androidx.lifecycle.SavedStateHandle
import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.common.base.UIIntent
import com.captures2024.soongan.core.common.base.UISideEffect
import com.captures2024.soongan.core.common.base.UIState
import com.captures2024.soongan.core.domain.usecase.dialog.SetIsShowGuestModeDialogFlowUseCase
import com.captures2024.soongan.core.domain.usecase.loading.ClearLoadingUseCase
import com.captures2024.soongan.core.domain.usecase.loading.HideLoadingUseCase
import com.captures2024.soongan.core.domain.usecase.loading.ShowLoadingUseCase
import com.captures2024.soongan.core.domain.usecase.members.GetCurrentMemberFlowUseCase
import com.captures2024.soongan.core.domain.usecase.members.GetIsCurrentGuestModeUseCase
import com.captures2024.soongan.core.domain.usecase.system.LaunchTermsUseCase
import com.captures2024.soongan.presentation.viewmodel.BaseViewModel
import com.captures2024.soongan.presentation.viewmodel.model.UserProfile
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel
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
    private val launchTermsUseCase: LaunchTermsUseCase,
) : BaseViewModel<ProfileViewModel.State, ProfileViewModel.Effect, ProfileViewModel.Intent>(
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
        val isShowMenuBottomSheet: Boolean,
    ) : UIState

    sealed interface Effect : UISideEffect {

        data object NavigateToNotification : Effect

        data class NavigateToPostInfo(
            val postId: Long,
        ) : Effect

        data object NavigateToRegistrationPost : Effect

        data object NavigateToEditProfile : Effect

        data object NavigateToFAQ : Effect
    }

    sealed interface Intent : UIIntent {
        data object Init : Intent

        data object OnClickMenu : Intent

        data object OnClickNotification : Intent

        data object OnDismissRequestMenuBottomSheet : Intent

        data object OnClickEditProfile : Intent

        data object OnClickFaq : Intent

        data object OnClickTerms : Intent
    }

    init {
        intent(Intent.Init)
    }

    override fun createInitialState(savedStateHandle: SavedStateHandle): State {
        return State(
            userProfile = UserProfile(),
            isShowMenuBottomSheet = false,
        )
    }

    override fun handleIntent(intent: Intent) {
        when (intent) {
            is Intent.Init -> handleInit()
            is Intent.OnClickMenu -> handleOnClickMenu()
            is Intent.OnClickNotification -> handleOnClickNotification()
            is Intent.OnDismissRequestMenuBottomSheet -> handleOnDismissRequestMenuBottomSheet()
            is Intent.OnClickEditProfile -> handleOnClickEditProfile()
            is Intent.OnClickFaq -> handleOnClickFaq()
            is Intent.OnClickTerms -> loadingLaunch { handleOnClickTerms() }
        }
    }

    override fun handleClientException(throwable: Throwable) {
        analyticsHelper.e(throwable) { "state: $currentState" }
    }

    private fun handleInit() {
        launch { collectUserProfile() }
    }

    private fun handleOnClickMenu() {
        showMenuBottomSheet()
    }

    private fun handleOnClickNotification() {
        postSideEffect(Effect.NavigateToNotification)
    }

    private fun handleOnDismissRequestMenuBottomSheet() {
        dismissMenuBottomSheet()
    }

    private fun handleOnClickEditProfile() {
        postSideEffect(Effect.NavigateToEditProfile)
    }

    private fun handleOnClickFaq() {
        postSideEffect(Effect.NavigateToFAQ)
    }

    private suspend fun handleOnClickTerms() {
        launchTermsUseCase()
    }

    private suspend fun collectUserProfile() {
        getCurrentMemberFlowUseCase().collect { currentMember ->
            currentMember?.let {
                reduce {
                    copy(
                        userProfile = UserProfile(
                            nickname = currentMember.nickname ?: "user1",
                            selfIntroduction = currentMember.selfIntroduction ?: "본인을 소개해주세요",
                            profileImageUrl = currentMember.profileImageUrl,
                        ),
                    )
                }
            }
        }
    }

    private fun showMenuBottomSheet() {
        reduce {
            copy(
                isShowMenuBottomSheet = true,
            )
        }
    }

    private fun dismissMenuBottomSheet() {
        reduce {
            copy(
                isShowMenuBottomSheet = false,
            )
        }
    }
}
