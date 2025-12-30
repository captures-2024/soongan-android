package com.captures2024.soongan.presentation.viewmodel.main.profile

import androidx.lifecycle.SavedStateHandle
import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.common.base.UIIntent
import com.captures2024.soongan.core.common.base.UISideEffect
import com.captures2024.soongan.core.common.base.UIState
import com.captures2024.soongan.core.model.dto.GalleryPostDto
import com.captures2024.soongan.domain.usecase.contest.GetHidePostEventUseCase
import com.captures2024.soongan.domain.usecase.contest.GetMyGalleryUseCase
import com.captures2024.soongan.domain.usecase.contest.GetRegisterPostEventUseCase
import com.captures2024.soongan.domain.usecase.member.GetCurrentMemberFlowUseCase
import com.captures2024.soongan.domain.usecase.member.GetIsCurrentGuestModeUseCase
import com.captures2024.soongan.domain.usecase.notification.GetIsNotReadNotificationCacheFlowUseCase
import com.captures2024.soongan.domain.usecase.notification.GetUnreadNotificationsCountUseCase
import com.captures2024.soongan.domain.usecase.system.dialog.SetIsShowGuestModeDialogFlowUseCase
import com.captures2024.soongan.domain.usecase.system.inapp.LaunchTermsUseCase
import com.captures2024.soongan.domain.usecase.system.loading.ClearLoadingUseCase
import com.captures2024.soongan.domain.usecase.system.loading.HideLoadingUseCase
import com.captures2024.soongan.domain.usecase.system.loading.ShowLoadingUseCase
import com.captures2024.soongan.presentation.viewmodel.BaseViewModel
import com.captures2024.soongan.presentation.viewmodel.model.PaginationStatus
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
    private val getMyGalleryUseCase: GetMyGalleryUseCase,
    private val getRegisterPostEventUseCase: GetRegisterPostEventUseCase,
    private val getHidePostEventUseCase: GetHidePostEventUseCase,
    private val isNotReadNotificationCacheFlowUseCase: GetIsNotReadNotificationCacheFlowUseCase,
    private val getUnreadNotificationsCountUseCase: GetUnreadNotificationsCountUseCase,
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
        val userProfile: UserProfile = UserProfile.guestUserProfile,
        val myGalleryState: MyGalleryState = MyGalleryState(),
        val isShowMenuBottomSheet: Boolean = false,
        val isNotReadNotification: Boolean = false,
    ) : UIState {

        data class MyGalleryState(
            val isRefreshing: Boolean = false,
            val posts: List<GalleryPostDto> = emptyList(),
            val paginationStatus: PaginationStatus = PaginationStatus.DEFAULT,
            val loadPage: Int = 0,
            val loadPageSize: Int = 50,
            val hasNextPage: Boolean = false,
        ) {
            val isInitPage: Boolean
                get() = loadPage == 0
        }
    }

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

        data object OnRefresh : Intent

        data object OnLoadNextPage : Intent

        data object OnClickRegisterPost : Intent

        data class OnClickPost(
            val postId: Long,
        ) : Intent
    }

    init {
        intent(Intent.Init)
    }

    override fun createInitialState(savedStateHandle: SavedStateHandle): State = State()

    override fun handleIntent(intent: Intent) {
        when (intent) {
            is Intent.Init -> loadingLaunch { handleInit() }
            is Intent.OnClickMenu -> blockGuestModeLogic { handleOnClickMenu() }
            is Intent.OnClickNotification -> blockGuestModeLogic { handleOnClickNotification() }
            is Intent.OnDismissRequestMenuBottomSheet -> handleOnDismissRequestMenuBottomSheet()
            is Intent.OnClickEditProfile -> handleOnClickEditProfile()
            is Intent.OnClickFaq -> handleOnClickFaq()
            is Intent.OnClickTerms -> loadingLaunch { handleOnClickTerms() }
            is Intent.OnRefresh -> launch { handleOnRefresh() }
            is Intent.OnLoadNextPage -> launch { handleOnLoadNextPage() }
            is Intent.OnClickRegisterPost -> blockGuestModeLogic { handleOnClickRegisterPost() }
            is Intent.OnClickPost -> handleOnClickPost(intent)
        }
    }

    override fun handleClientException(throwable: Throwable) {
        analyticsHelper.e(throwable) { "state: $currentState" }
    }

    private suspend fun handleInit() {
        launch { collectUserProfile() }
        launch { collectRegisterPostEvent() }
        launch { collectHidePostEvent() }
        launch { collectIsNotReadNotificationFlow() }

        getCurrentMemberFlowUseCase().value?.let { currentMember ->
            reduce {
                copy(
                    userProfile = UserProfile(
                        nickname = currentMember.nickname ?: "user",
                        selfIntroduction = currentMember.selfIntroduction ?: UserProfile.DEFAULT_SELF_INTRODUCTION,
                        profileImageUrl = currentMember.profileImageUrl,
                    ),
                )
            }
        }

        fetchInitData()
        getUnreadNotificationsCountUseCase()
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

    private suspend fun handleOnRefresh() {
        reduce {
            copy(
                myGalleryState = myGalleryState.copy(
                    isRefreshing = true,
                ),
            )
        }

        val status = getRemotePost(
            page = 0,
            isRefreshing = true,
        )

        reduce {
            copy(
                myGalleryState = myGalleryState.copy(
                    isRefreshing = false,
                    paginationStatus = status,
                ),
            )
        }
    }

    private suspend fun handleOnLoadNextPage() {
        val status = getRemotePost(page = currentState.myGalleryState.loadPage)

        reduce {
            copy(
                myGalleryState = myGalleryState.copy(
                    paginationStatus = status,
                ),
            )
        }
    }

    private fun handleOnClickRegisterPost() {
        postSideEffect(Effect.NavigateToRegistrationPost)
    }

    private fun handleOnClickPost(intent: Intent.OnClickPost) {
        postSideEffect(
            sideEffect = Effect.NavigateToPostInfo(
                postId = intent.postId,
            ),
        )
    }

    private suspend fun collectUserProfile() {
        getCurrentMemberFlowUseCase().collect { currentMember ->
            currentMember?.let {
                reduce {
                    copy(
                        userProfile = UserProfile(
                            nickname = currentMember.nickname ?: "user",
                            selfIntroduction = currentMember.selfIntroduction ?: UserProfile.DEFAULT_SELF_INTRODUCTION,
                            profileImageUrl = currentMember.profileImageUrl,
                        ),
                    )
                }
            }
        }
    }

    private suspend fun collectRegisterPostEvent() {
        getRegisterPostEventUseCase().collect {
            fetchInitData()
        }
    }

    private suspend fun collectHidePostEvent() {
        getHidePostEventUseCase().collect { postId ->
            reduce {
                copy(
                    myGalleryState = myGalleryState.copy(
                        posts = myGalleryState.posts.filter { it.postId != postId },
                    ),
                )
            }
        }
    }

    private suspend fun collectIsNotReadNotificationFlow() {
        isNotReadNotificationCacheFlowUseCase().collect {
            reduce {
                copy(
                    isNotReadNotification = it,
                )
            }
        }
    }

    private suspend fun fetchInitData() {
        val status = getRemotePost(
            page = 0,
            isRefreshing = true,
        )

        reduce {
            copy(
                myGalleryState = myGalleryState.copy(
                    paginationStatus = status,
                ),
            )
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

    private suspend fun getRemotePost(
        page: Int,
        isRefreshing: Boolean = false,
    ): PaginationStatus {
        val state = currentState

        if (state.userProfile == UserProfile.guestUserProfile) {
            return PaginationStatus.GUEST
        }

        when (state.myGalleryState.paginationStatus) {
            PaginationStatus.REFRESH_LOAD,
            PaginationStatus.PAGING_LOAD,
            -> return state.myGalleryState.paginationStatus

            else -> Unit
        }

        reduce {
            copy(
                myGalleryState = myGalleryState.copy(
                    paginationStatus = when (isRefreshing) {
                        true -> PaginationStatus.REFRESH_LOAD
                        false -> PaginationStatus.PAGING_LOAD
                    },
                ),
            )
        }

        val galleryDto = getMyGalleryUseCase(
            page = page,
            pageSize = state.myGalleryState.loadPageSize,
        ).getOrNull()

        if (galleryDto == null) {
            return PaginationStatus.FAILED
        }

        reduce {
            copy(
                myGalleryState = myGalleryState.copy(
                    posts = when (isRefreshing) {
                        true -> galleryDto.posts
                        false -> myGalleryState.posts + galleryDto.posts
                    },
                    loadPage = page + 1,
                    hasNextPage = galleryDto.hasNext,
                ),
            )
        }

        return PaginationStatus.SUCCESS
    }
}
