package com.captures2024.soongan.core.viewmodel.home

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import com.captures2024.soongan.core.analytics.utils.LogElementArgument
import com.captures2024.soongan.core.common.base.BaseViewModel
import com.captures2024.soongan.core.common.base.UIIntent
import com.captures2024.soongan.core.common.base.UISideEffect
import com.captures2024.soongan.core.common.base.UIState
import com.captures2024.soongan.core.model.UserPost
import com.captures2024.soongan.core.navigator.screen.main.home.HomePostNavigator
import com.captures2024.soongan.core.viewmodel.model.HomePostBottomModalState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomePostViewModel
@Inject
constructor(
    savedStateHandle: SavedStateHandle
) : BaseViewModel<HomePostViewModel.State, HomePostViewModel.Effect, HomePostViewModel.Intent>(savedStateHandle) {

    data class State(
        val post: UserPost.PhotoPost,
        val isLoading: Boolean = false,
        val isOpenModal: HomePostBottomModalState = HomePostBottomModalState.CLOSED,
        val inWritingComment: String = "",
    ) : UIState {

        override fun toLoggingElements(): Array<LogElementArgument> = arrayOf(
            LogElementArgument("isLoading", isLoading.toString()),
            LogElementArgument("post", post.toString()),
            LogElementArgument("isOpenModal", isOpenModal.toString()),
            LogElementArgument("inWritingComment", inWritingComment.toString()),
        )
    }

    sealed interface Effect : UISideEffect {

        data class NavigateToHomePostPhoto(
            val url: String,
        ) : Effect
    }

    sealed interface Intent : UIIntent {

        data object OnClickPhoto : Intent

        data object OnClickMenu : Intent

        data object OnClickHeart : Intent

        data object OnClickComment : Intent

        data object OnClosedModal : Intent

        data class OnCommentValueChanged(
            val inWritingComment: String,
        ) : Intent
    }

    override fun createInitialState(savedStateHandle: SavedStateHandle): State {
        val info = savedStateHandle.toRoute<HomePostNavigator>()

        return State(
            post = UserPost.PhotoPost(
                id = info.id,
                url = info.url,
                title = "",
            )
        )
    }

    override fun handleClientException(throwable: Throwable) {
//        Timber.tag(TAG).e(throwable)
    }

    override suspend fun handleIntent(intent: Intent) {
        when (intent) {
            is Intent.OnClickComment -> onClickComment()

            is Intent.OnClickHeart -> onClickHeart()

            is Intent.OnClickMenu -> onClickMenu()

            is Intent.OnClickPhoto -> onClickPhoto()

            is Intent.OnClosedModal -> onClosedModal()

            is Intent.OnCommentValueChanged -> onCommentValueChanged(intent.inWritingComment)
        }
    }

    private fun onClickComment() {
        reduce {
            copy(
                isOpenModal = HomePostBottomModalState.OPEN_COMMENT
            )
        }
    }

    private fun onClickHeart() {
        TODO("Not Impl yet")
    }

    private fun onClickMenu() {
        reduce {
            copy(
                isOpenModal = HomePostBottomModalState.OPEN_REPORT
            )
        }
    }

    private fun onClickPhoto() {
        postSideEffect(Effect.NavigateToHomePostPhoto(currentState.post.url))
    }

    private fun onClosedModal() {
        reduce {
            copy(
                isOpenModal = HomePostBottomModalState.CLOSED
            )
        }
    }

    private fun onCommentValueChanged(inWritingComment: String) {
        reduce {
            copy(
                inWritingComment = inWritingComment
            )
        }
    }

    companion object {
        private const val TAG = "HomePostVM"
    }
}