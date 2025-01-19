package com.captures2024.soongan.core.viewmodel.home

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import com.captures2024.soongan.core.analytics.utils.LogElementArgument
import com.captures2024.soongan.core.common.base.BaseViewModel
import com.captures2024.soongan.core.common.base.UIIntent
import com.captures2024.soongan.core.common.base.UISideEffect
import com.captures2024.soongan.core.common.base.UIState
import com.captures2024.soongan.core.domain.usecase.weekly.contests.GetPostInfoUseCase
import com.captures2024.soongan.core.model.dto.PostInfoDto
import com.captures2024.soongan.core.navigator.screen.main.home.HomePostNavigator
import com.captures2024.soongan.core.viewmodel.model.HomePostBottomModalState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import javax.inject.Inject

@HiltViewModel
class HomePostViewModel
@Inject
constructor(
    private val getPostInfoUseCase: GetPostInfoUseCase,
    savedStateHandle: SavedStateHandle,
) : BaseViewModel<HomePostViewModel.State, HomePostViewModel.Effect, HomePostViewModel.Intent>(savedStateHandle) {

    data class State(
        val postId: Int,
        val post: PostInfoDto = PostInfoDto(),
        val isLoading: Boolean = false,
        val isOpenModal: HomePostBottomModalState = HomePostBottomModalState.CLOSED,
        val inWritingComment: String = "",
    ) : UIState {

        override fun toLoggingElements(): Array<LogElementArgument> = arrayOf(
            LogElementArgument("isLoading", isLoading.toString()),
            LogElementArgument("postId", postId.toString()),
            LogElementArgument("post", post.toString()),
            LogElementArgument("isOpenModal", isOpenModal.toString()),
            LogElementArgument("inWritingComment", inWritingComment.toString()),
        )
    }

    sealed interface Effect : UISideEffect {

        data object NavigateToBack : Effect

        data class NavigateToHomePostPhoto(
            val url: String,
        ) : Effect
    }

    sealed interface Intent : UIIntent {

        data object Init: Intent

        data object OnClickBack : Intent

        data object OnClickPhoto : Intent

        data object OnClickMenu : Intent

        data object OnClickHeart : Intent

        data object OnClickComment : Intent

        data object OnClosedModal : Intent

        data class OnCommentValueChanged(
            val inWritingComment: String,
        ) : Intent

        data object OnClickEditPost : Intent

        data object OnClickDeletePost : Intent

        data object OnClickReportPost : Intent
    }

    init {
        intent(Intent.Init)
    }

    override fun createInitialState(savedStateHandle: SavedStateHandle): State {
        val info = savedStateHandle.toRoute<HomePostNavigator>()

        return State(
            postId = info.id,
            isLoading = true,
        )
    }

    override fun handleClientException(throwable: Throwable) {
//        Timber.tag(TAG).e(throwable)
    }

    override suspend fun handleIntent(intent: Intent) {
        when (intent) {
            is Intent.Init -> handleInit()

            is Intent.OnClickBack -> handleOnClickBack()

            is Intent.OnClickComment -> handleOnClickComment()

            is Intent.OnClickHeart -> handleOnClickHeart()

            is Intent.OnClickMenu -> handleOnClickMenu()

            is Intent.OnClickPhoto -> handleOnClickPhoto()

            is Intent.OnClosedModal -> handleOnClosedModal()

            is Intent.OnCommentValueChanged -> handleOnCommentValueChanged(intent.inWritingComment)

            is Intent.OnClickDeletePost -> handleOnClickDeletePost()

            is Intent.OnClickEditPost -> handleOnClickEditPost()

            is Intent.OnClickReportPost -> handleOnClickReportPost()
        }
    }

    private suspend fun handleInit() {
        val postInfo = getPostInfoUseCase(currentState.postId).getOrNull()

        if (postInfo == null) {
            postSideEffect(Effect.NavigateToBack)
            return
        }

        reduce {
            copy(
                postId = postInfo.postId,
                post = postInfo,
                isLoading = false,
            )
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
        TODO("Not Impl yet")
    }

    private fun handleOnClickMenu() {
        reduce {
            copy(
                isOpenModal = HomePostBottomModalState.OPEN_MENU,
            )
        }
    }

    private fun handleOnClickPhoto() {
        // TODO 1차 MVP 스펙아웃
//        postSideEffect(Effect.NavigateToHomePostPhoto(currentState.post.url))
    }

    private fun handleOnClosedModal() {
        reduce {
            copy(
                isOpenModal = HomePostBottomModalState.CLOSED
            )
        }
    }

    private fun handleOnCommentValueChanged(inWritingComment: String) {
        reduce {
            copy(
                inWritingComment = inWritingComment,
            )
        }
    }

    private fun handleOnClickDeletePost() {
        TODO("handleOnClickDeletePost Not Impl Yet")
    }

    private fun handleOnClickEditPost() {
        TODO("handleOnClickEditPost Not Impl Yet")
    }

    private fun handleOnClickReportPost() {
        reduce {
            copy(
                isOpenModal = HomePostBottomModalState.OPEN_REPORT,
            )
        }
    }

    companion object {
        private const val TAG = "HomePostVM"
    }
}