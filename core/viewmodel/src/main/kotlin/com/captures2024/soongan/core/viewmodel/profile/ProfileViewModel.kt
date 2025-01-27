package com.captures2024.soongan.core.viewmodel.profile

import androidx.lifecycle.SavedStateHandle
import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.analytics.utils.LogElementArgument
import com.captures2024.soongan.core.common.base.BaseViewModel
import com.captures2024.soongan.core.common.base.UIIntent
import com.captures2024.soongan.core.common.base.UISideEffect
import com.captures2024.soongan.core.common.base.UIState
import com.captures2024.soongan.core.domain.usecase.members.GetCurrentMemberFlowUseCase
import com.captures2024.soongan.core.domain.usecase.weekly.contests.GetMyGalleryUseCase
import com.captures2024.soongan.core.model.dto.GalleryPostDto
import com.captures2024.soongan.core.viewmodel.model.PaginationStatus
import com.captures2024.soongan.core.viewmodel.model.profile.UserProfile
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel
@Inject
constructor(
    private val analyticsHelper: AnalyticsHelper,
    private val getCurrentMemberFlowUseCase: GetCurrentMemberFlowUseCase,
    private val getMyGalleryUseCase: GetMyGalleryUseCase,
    savedStateHandle: SavedStateHandle,
) : BaseViewModel<ProfileViewModel.State, ProfileViewModel.Effect, ProfileViewModel.Intent>(
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
            val postId: Int,
        ) : Effect

        data object NavigateToRegistrationPost : Effect

        data object NavigateToEditProfile : Effect

        data object NavigateToHome : Effect
    }

    sealed interface Intent : UIIntent {

        data object Init : Intent

        data object RefreshMyGallery : Intent

        data object LoadNextPage : Intent

        data class OnClickPhoto(val postId: Int) : Intent

        data object OnClickRegistrationText : Intent

        data object OnClickNotification : Intent

        data object OnClickMenu : Intent

        data class OnCloseBottomSheet(
            val outType: ProfileBottomSheetOutType,
        ) : Intent
    }

    init {
        intent(Intent.Init)
        analyticsHelper.d(message = "posts: ${currentState.myPosts}")
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
            Intent.Init -> initSyncData()

            Intent.RefreshMyGallery -> fetchProfileGallery(page = 0, isRefreshing = true)

            Intent.LoadNextPage -> fetchProfileGallery(page = currentState.nextPage)

            Intent.OnClickMenu -> reduce { copy(isOpenBottomSheet = true) }

            Intent.OnClickNotification -> onClickNotification()

            is Intent.OnClickPhoto -> postSideEffect(Effect.NavigateToHomePost(intent.postId))

            Intent.OnClickRegistrationText -> postSideEffect(Effect.NavigateToRegistrationPost)

            is Intent.OnCloseBottomSheet -> onCloseBottomSheet(intent)
        }
    }

    private fun initSyncData() {
        launch {
            fetchUserProfile()
        }
        launch {
            fetchProfileGallery(page = 0)
        }
    }

    private suspend fun fetchUserProfile() {
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

    private fun fetchProfileGallery(page: Int, isRefreshing: Boolean = false) {
        when (currentState.paginationStatus) {
            PaginationStatus.LOADING, PaginationStatus.PAGINATING -> return

            else -> Unit
        }

        val isInitPage = (page == 0)

        setUpLoading(isInitPage = isInitPage, isRefreshing = isRefreshing)

        launch {
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

                return@launch
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
    }

    private fun onClickNotification() {
        reduce {
            copy(hasNotification = false)
        }

        postSideEffect(Effect.NavigateToNotification)
    }

    private fun onCloseBottomSheet(intent: Intent.OnCloseBottomSheet) {
        reduce {
            copy(
                isOpenBottomSheet = false
            )
        }

        when (intent.outType) {
            ProfileBottomSheetOutType.EDIT -> postSideEffect(Effect.NavigateToEditProfile)

            ProfileBottomSheetOutType.FAQ -> TODO("navigate FAQ")

            ProfileBottomSheetOutType.TERMS_AND_POLICY -> TODO("navigate Terms_And_Policy")

            ProfileBottomSheetOutType.DONE_BUTTON -> postSideEffect(Effect.NavigateToHome)

            ProfileBottomSheetOutType.OUT_OF_AREA -> Unit
        }
    }

    companion object {
        private const val PAGE_SIZE = 20
    }
}