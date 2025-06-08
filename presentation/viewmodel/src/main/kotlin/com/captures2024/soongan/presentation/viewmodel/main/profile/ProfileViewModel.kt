package com.captures2024.soongan.presentation.viewmodel.main.profile

import androidx.lifecycle.SavedStateHandle
import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.common.base.UIIntent
import com.captures2024.soongan.core.common.base.UISideEffect
import com.captures2024.soongan.core.common.base.UIState
import com.captures2024.soongan.core.model.dto.GalleryPostDto
import com.captures2024.soongan.domain.usecase.contest.GetMyGalleryUseCase
import com.captures2024.soongan.domain.usecase.member.GetCurrentMemberFlowUseCase
import com.captures2024.soongan.domain.usecase.member.GetIsCurrentGuestModeUseCase
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
        val myGalleryState: MyGalleryState,
        val isShowMenuBottomSheet: Boolean,
    ) : UIState {

        data class MyGalleryState(
            val isRefreshing: Boolean,
            val posts: List<GalleryPostDto>,
            val paginationStatus: PaginationStatus,
            val loadPage: Int,
            val loadPageSize: Int,
            val hasNextPage: Boolean,
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

    override fun createInitialState(savedStateHandle: SavedStateHandle): State {
        return State(
            userProfile = UserProfile(),
            myGalleryState = State.MyGalleryState(
                isRefreshing = false,
                posts = emptyList(),
                paginationStatus = PaginationStatus.DEFAULT,
                loadPage = 0,
                loadPageSize = 50,
                hasNextPage = false,
            ),
            isShowMenuBottomSheet = false,
        )
    }

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
            is Intent.OnClickRegisterPost -> handleOnClickRegisterPost()
            is Intent.OnClickPost -> handleOnClickPost(intent)
        }
    }

    override fun handleClientException(throwable: Throwable) {
        analyticsHelper.e(throwable) { "state: $currentState" }
    }

    private suspend fun handleInit() {
        launch { collectUserProfile() }

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
            )
        )
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

    private suspend fun getRemotePost(
        page: Int,
        isRefreshing: Boolean = false,
    ): PaginationStatus {
        val state = currentState

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
