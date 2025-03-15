package com.captures2024.soongan.core.viewmodel.home

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.analytics.utils.LogElementArgument
import com.captures2024.soongan.core.common.base.UIIntent
import com.captures2024.soongan.core.common.base.UISideEffect
import com.captures2024.soongan.core.common.base.UIState
import com.captures2024.soongan.core.domain.usecase.dialog.SetIsShowGuestModeDialogFlowUseCase
import com.captures2024.soongan.core.domain.usecase.like.DeletePostLikeUseCase
import com.captures2024.soongan.core.domain.usecase.like.PutPostLikeUseCase
import com.captures2024.soongan.core.domain.usecase.loading.ClearLoadingUseCase
import com.captures2024.soongan.core.domain.usecase.loading.HideLoadingUseCase
import com.captures2024.soongan.core.domain.usecase.loading.ShowLoadingUseCase
import com.captures2024.soongan.core.domain.usecase.members.GetCurrentMemberFlowUseCase
import com.captures2024.soongan.core.domain.usecase.members.GetIsCurrentGuestModeUseCase
import com.captures2024.soongan.core.domain.usecase.weekly.contests.DeletePostUseCase
import com.captures2024.soongan.core.domain.usecase.weekly.contests.GetPostInfoUseCase
import com.captures2024.soongan.core.model.dto.PostInfoDto
import com.captures2024.soongan.core.navigator.screen.main.home.HomePostNavigator
import com.captures2024.soongan.core.viewmodel.NewBaseViewModel
import com.captures2024.soongan.core.viewmodel.model.HomePostBottomModalState
import com.captures2024.soongan.core.viewmodel.model.HomePostDialogModalState
import com.captures2024.soongan.core.viewmodel.model.PostLikeContestType
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomePostViewModel
@Inject
constructor(
    private val currentMemberFlowUseCase: GetCurrentMemberFlowUseCase,
    private val getPostInfoUseCase: GetPostInfoUseCase,
    private val deletePostUseCase: DeletePostUseCase,
    private val putPostLikeUseCase: PutPostLikeUseCase,
    private val deletePostLikeUseCase: DeletePostLikeUseCase,
    analyticsHelper: AnalyticsHelper,
    showLoadingUseCase: ShowLoadingUseCase,
    hideLoadingUseCase: HideLoadingUseCase,
    clearLoadingUseCase: ClearLoadingUseCase,
    getIsCurrentGuestModeUseCase: GetIsCurrentGuestModeUseCase,
    setIsShowGuestModeDialogFlowUseCase: SetIsShowGuestModeDialogFlowUseCase,
    savedStateHandle: SavedStateHandle,
) : NewBaseViewModel<HomePostViewModel.State, HomePostViewModel.Effect, HomePostViewModel.Intent>(
    analyticsHelper = analyticsHelper,
    showLoadingUseCase = showLoadingUseCase,
    hideLoadingUseCase = hideLoadingUseCase,
    clearLoadingUseCase = clearLoadingUseCase,
    getIsCurrentGuestModeUseCase = getIsCurrentGuestModeUseCase,
    setIsShowGuestModeDialogFlowUseCase = setIsShowGuestModeDialogFlowUseCase,
    savedStateHandle = savedStateHandle,
) {

    data class State(
        val postId: Long,
        val post: PostInfoDto = PostInfoDto(),
        val isMyPost: Boolean = false,
        val isLikedChanged: Boolean = false,
        val isOpenModal: HomePostBottomModalState = HomePostBottomModalState.CLOSED,
        val isOpenDialogModal: HomePostDialogModalState = HomePostDialogModalState.CLOSED,
        val inWritingComment: String = "",
    ) : UIState {

        override fun toLoggingElements(): Array<LogElementArgument> = arrayOf(
            LogElementArgument("postId", postId.toString()),
            LogElementArgument("post", post.toString()),
            LogElementArgument("isMyPost", isMyPost.toString()),
            LogElementArgument("isLikedChanged", isLikedChanged.toString()),
            LogElementArgument("isOpenModal", isOpenModal.toString()),
            LogElementArgument("isOpenDialogModal", isOpenDialogModal.toString()),
            LogElementArgument("inWritingComment", inWritingComment),
        )
    }

    sealed interface Effect : UISideEffect {

        data object NavigateToBack : Effect

        data class NavigateToEditPost(
            val postId: Long,
            val imageUrl: String,
            val title: String,
        ) : Effect

        data class NavigateToHomePostPhoto(
            val url: String,
        ) : Effect

        data class NavigateToBackWithHidePost(
            val postId: Long,
        ) : Effect
    }

    sealed interface Intent : UIIntent {

        data object Init : Intent

        data object OnClickBack : Intent

        data object OnClickPhoto : Intent

        data object OnClickMenu : Intent

        data object OnClickHeart : Intent

        data object OnClickHeartRemote : Intent

        data object OnClickComment : Intent

        data object OnClosedModal : Intent

        data object OnClosedDialogModal : Intent

        data class OnCommentValueChanged(
            val inWritingComment: String,
        ) : Intent

        data object OnClickEditPost : Intent

        data object OnClickDeletePost : Intent

        data object OnClickReportPost : Intent

        data object OnDeletePostRemote : Intent

        data object OnHidePost : Intent
    }

    init {
        intent(Intent.Init)
    }

    override fun createInitialState(savedStateHandle: SavedStateHandle): State {
        val info = savedStateHandle.toRoute<HomePostNavigator>()

        return State(postId = info.id)
    }

    override fun handleClientException(throwable: Throwable) {
        analyticsHelper.e(throwable = throwable)
    }

    override fun handleIntent(intent: Intent) {
        when (intent) {
            is Intent.Init -> launch { handleInit() }

            is Intent.OnClickBack -> handleOnClickBack()

            is Intent.OnClickComment -> handleOnClickComment()

            is Intent.OnClickHeart -> handleOnClickHeart()

            is Intent.OnClickHeartRemote -> loadingLaunch { handleOnClickHeartRemote() }

            is Intent.OnClickMenu -> handleOnClickMenu()

            is Intent.OnClickPhoto -> handleOnClickPhoto()

            is Intent.OnClosedModal -> handleOnClosedModal()

            is Intent.OnClosedDialogModal -> handleOnClosedDialogModal()

            is Intent.OnCommentValueChanged -> handleOnCommentValueChanged(intent)

            is Intent.OnClickDeletePost -> handleOnClickDeletePost()

            is Intent.OnClickEditPost -> handleOnClickEditPost()

            is Intent.OnClickReportPost -> handleOnClickReportPost()

            is Intent.OnDeletePostRemote -> loadingLaunch { handleOnDeletePostRemote() }

            is Intent.OnHidePost -> handleOnHidePost()
        }
    }

    private suspend fun handleInit() {
        val postInfo = getPostInfoUseCase(currentState.postId).getOrNull()

        if (postInfo == null) {
            postSideEffect(Effect.NavigateToBack)
            return
        }

        currentMemberFlowUseCase().collect { currentMember ->

            reduce {
                copy(
                    postId = postInfo.postId,
                    post = postInfo,
                    isMyPost = postInfo.nickname == currentMember?.nickname
                )
            }
        }
    }

    private fun handleOnClickBack() {
        postSideEffect(Effect.NavigateToBack)
    }

    private fun handleOnClickComment() {
        reduce {
            copy(
                isOpenModal = HomePostBottomModalState.OPEN_COMMENT,
            )
        }
    }

    private fun handleOnClickHeart() {
        reduce {
            copy(
                post = post.copy(
                    likeCount = if (post.isLiked) post.likeCount - 1 else post.likeCount + 1,
                    isLiked = !post.isLiked
                ),
                isLikedChanged = !isLikedChanged
            )
        }
    }

    private suspend fun handleOnClickHeartRemote() {
        if (!currentState.isLikedChanged) {
            return
        }

        when (currentState.post.isLiked) {
            true -> {
                putPostLikeUseCase(
                    postId = currentState.postId,
                    contestType = PostLikeContestType.WEEKLY.name
                )
            }

            false -> {
                deletePostLikeUseCase(
                    postId = currentState.postId,
                    contestType = PostLikeContestType.WEEKLY.name
                )
            }
        }
    }

    private fun handleOnClickMenu() {
        reduce {
            copy(
                isOpenModal = HomePostBottomModalState.OPEN_MENU,
            )
        }
    }

    private fun handleOnClickPhoto() {
        postSideEffect(Effect.NavigateToHomePostPhoto(currentState.post.imageUrl))
    }

    private fun handleOnClosedModal() {
        reduce {
            copy(
                isOpenModal = HomePostBottomModalState.CLOSED
            )
        }
    }

    private fun handleOnClosedDialogModal() {
        reduce {
            copy(
                isOpenDialogModal = HomePostDialogModalState.CLOSED
            )
        }
    }

    private fun handleOnCommentValueChanged(intent: Intent.OnCommentValueChanged) {
        reduce {
            copy(
                inWritingComment = intent.inWritingComment,
            )
        }
    }

    private fun handleOnClickEditPost() {
        reduce {
            copy(
                isOpenModal = HomePostBottomModalState.CLOSED
            )
        }

        postSideEffect(
            Effect.NavigateToEditPost(
                postId = currentState.postId,
                imageUrl = currentState.post.imageUrl,
                title = currentState.post.title
            )
        )
    }

    private fun handleOnClickDeletePost() {
        reduce {
            copy(
                isOpenDialogModal = HomePostDialogModalState.OPEN_DELETE
            )
        }
    }

    private fun handleOnClickReportPost() {
        reduce {
            copy(
                isOpenModal = HomePostBottomModalState.OPEN_REPORT,
            )
        }
    }

    private suspend fun handleOnDeletePostRemote() {
        val result = deletePostUseCase(
            postId = currentState.postId,
        ).getOrNull()

        analyticsHelper.d(message = "postDeleteResult: $result")

        reduce {
            copy(
                isOpenDialogModal = when (result) {
                    true -> HomePostDialogModalState.OPEN_COMPLETE

                    else -> HomePostDialogModalState.OPEN_FAIL
                }
            )
        }
    }

    private fun handleOnHidePost() {
        postSideEffect(Effect.NavigateToBackWithHidePost(currentState.postId))
    }
}