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
import com.captures2024.soongan.core.domain.usecase.members.ClearCurrentMemberUseCase
import com.captures2024.soongan.core.domain.usecase.members.GetCurrentMemberFlowUseCase
import com.captures2024.soongan.core.domain.usecase.members.GetIsCurrentGuestModeUseCase
import com.captures2024.soongan.core.domain.usecase.weekly.contests.GetMyGalleryUseCase
import com.captures2024.soongan.core.model.dto.GalleryPostDto
import com.captures2024.soongan.core.viewmodel.NewBaseViewModel
import com.captures2024.soongan.core.viewmodel.model.PaginationStatus
import com.captures2024.soongan.core.viewmodel.model.profile.ProfileBtmShtOutType
import com.captures2024.soongan.core.viewmodel.model.profile.UserProfile
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel
@Inject
constructor(
    private val getCurrentMemberFlowUseCase: GetCurrentMemberFlowUseCase,
    private val getMyGalleryUseCase: GetMyGalleryUseCase,
    private val clearCurrentMemberUseCase: ClearCurrentMemberUseCase,
    analyticsHelper: AnalyticsHelper,
    showLoadingUseCase: ShowLoadingUseCase,
    hideLoadingUseCase: HideLoadingUseCase,
    clearLoadingUseCase: ClearLoadingUseCase,
    getIsCurrentGuestModeUseCase: GetIsCurrentGuestModeUseCase,
    setIsShowGuestModeDialogFlowUseCase: SetIsShowGuestModeDialogFlowUseCase,
    savedStateHandle: SavedStateHandle,
) : NewBaseViewModel<ProfileViewModel.State, ProfileViewModel.Effect, ProfileViewModel.Intent>(
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
        val isRefreshing: Boolean = false,
        val paginationStatus: PaginationStatus = PaginationStatus.INACTIVE,
        val myPosts: List<GalleryPostDto> = emptyList(),
        val nextPage: Int = 0,
        val hasNextPage: Boolean = false,
        val hasNotification: Boolean = false,
        val isOpenBottomSheet: Boolean = false,
    ) : UIState {

        override fun toLoggingElements(): Array<LogElementArgument> = arrayOf(
            LogElementArgument("isLoading", isLoading.toString()),
            LogElementArgument("userProfile", userProfile.toString()),
            LogElementArgument("isRefreshing", isRefreshing.toString()),
            LogElementArgument("paginationStatus", paginationStatus.toString()),
            LogElementArgument("myPosts", myPosts.toString()),
            LogElementArgument("nextPage", nextPage.toString()),
            LogElementArgument("hasNextPage", hasNextPage.toString()),
            LogElementArgument("hasNotification", hasNotification.toString()),
            LogElementArgument("isOpenBottomSheet", isOpenBottomSheet.toString()),
        )
    }

    sealed interface Effect : UISideEffect {

        data object NavigateToNotification : Effect

        data class NavigateToHomePost(
            val postId: Long,
        ) : Effect

        data object NavigateToRegistrationPost : Effect

        data object NavigateToEditProfile : Effect

        data object NavigateToFAQ : Effect
    }

    sealed interface Intent : UIIntent {

        data object Init : Intent

        data object RefreshMyGallery : Intent

        data object LoadNextPage : Intent

        data class OnClickPhoto(
            val postId: Long,
        ) : Intent

        data object OnClickRegistrationText : Intent

        data object OnClickNotification : Intent

        data object OnClickMenu : Intent

        data class OnCloseBottomSheet(
            val outType: ProfileBtmShtOutType,
        ) : Intent
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
            is Intent.RefreshMyGallery -> launch { handleRefreshMyGallery() }
            is Intent.LoadNextPage -> launch { handleLoadNextPage() }
            is Intent.OnClickMenu -> handleOnClickMenu()
            is Intent.OnClickNotification -> handleOnClickNotification()
            is Intent.OnClickPhoto -> handleOnClickPhoto(intent)
            is Intent.OnClickRegistrationText -> handleOnClickRegistrationText()
            is Intent.OnCloseBottomSheet -> handleOnCloseBottomSheet(intent)
        }
    }

    private fun handleInit() {
        launch { collectUserProfile() }
        launch { fetchProfileGallery(page = 0) }
    }

    private suspend fun handleRefreshMyGallery() {
        fetchProfileGallery(
            page = 0,
            isRefreshing = true,
        )
    }

    private suspend fun handleLoadNextPage() {
        fetchProfileGallery(page = currentState.nextPage)
    }

    private fun handleOnClickMenu() {
        reduce {
            copy(
                isOpenBottomSheet = true,
            )
        }
    }

    private fun handleOnClickNotification() {
        reduce {
            copy(hasNotification = false)
        }

        postSideEffect(Effect.NavigateToNotification)
    }

    private fun handleOnClickRegistrationText() {
        postSideEffect(Effect.NavigateToRegistrationPost)
    }

    private fun handleOnClickPhoto(intent: Intent.OnClickPhoto) {
        postSideEffect(Effect.NavigateToHomePost(intent.postId))
    }

    private fun handleOnCloseBottomSheet(intent: Intent.OnCloseBottomSheet) {
        reduce {
            copy(
                isOpenBottomSheet = false
            )
        }

        when (intent.outType) {
            ProfileBtmShtOutType.EDIT -> blockGuestModeLogic { postSideEffect(Effect.NavigateToEditProfile) }

            ProfileBtmShtOutType.FAQ -> postSideEffect(Effect.NavigateToFAQ)

            ProfileBtmShtOutType.TERMS_AND_POLICY -> TODO("navigate Terms_And_Policy")

            ProfileBtmShtOutType.DONE_STATUS -> launch { clearCurrentMemberUseCase() }

            ProfileBtmShtOutType.OUT_OF_AREA -> Unit
        }
    }

    private suspend fun collectUserProfile() {
        getCurrentMemberFlowUseCase().collect { currentMember ->
            reduce {
                copy(
                    userProfile = UserProfile(
                        nickname = currentMember?.nickname ?: "user1",
                        selfIntroduction = currentMember?.selfIntroduction ?: "본인을 소개해주세요",
                        profileImageUrl = currentMember?.profileImageUrl
                    )
                )
            }
        }
    }

    private fun setUpLoading(isInitPage: Boolean, isRefreshing: Boolean) {
        if (isInitPage) {
            reduce {
                copy(
                    isRefreshing = isRefreshing,
                    paginationStatus = PaginationStatus.LOADING,
                    myPosts = emptyList()
                )
            }
        } else {
            reduce {
                copy(
                    paginationStatus = PaginationStatus.PAGINATING,
                )
            }
        }
    }

    private suspend fun fetchProfileGallery(
        page: Int,
        isRefreshing: Boolean = false
    ) {
        when (currentState.paginationStatus) {
            PaginationStatus.LOADING, PaginationStatus.PAGINATING -> return

            else -> Unit
        }

        val isInitPage: Boolean = (page == 0)

        setUpLoading(
            isInitPage = isInitPage,
            isRefreshing = isRefreshing,
        )

        val myGalleryDto = getMyGalleryUseCase(
            params = GetMyGalleryUseCase.Params(
                page = page,
                pageSize = PAGE_SIZE,
            )
        ).getOrNull()

        if (myGalleryDto == null) {
            analyticsHelper.d(message = "myGalleryDto is null")

            reduce {
                copy(
                    isRefreshing = false,
                    paginationStatus = PaginationStatus.ERROR
                )
            }

            return
        }

        analyticsHelper.d(message = "myGalleryDto is ${myGalleryDto.posts}")

        reduce {
            copy(
                isRefreshing = false,
                paginationStatus = when {
                    !myGalleryDto.hasNext -> PaginationStatus.EXHAUST
                    myGalleryDto.posts.isEmpty() -> PaginationStatus.EMPTY
                    else -> PaginationStatus.INACTIVE
                },
                myPosts = myPosts + myGalleryDto.posts,
                nextPage = page + 1,
                hasNextPage = myGalleryDto.hasNext
            )
        }
    }

    companion object {
        private const val PAGE_SIZE = 20
    }
}
