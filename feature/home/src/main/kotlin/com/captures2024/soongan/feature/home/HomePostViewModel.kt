package com.captures2024.soongan.feature.home

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import com.captures2024.soongan.core.common.base.BaseViewModel
import com.captures2024.soongan.core.model.UserPost
import com.captures2024.soongan.core.navigator.screen.main.HomePostNavigator
import com.captures2024.soongan.feature.home.state.post.HomePostIntent
import com.captures2024.soongan.feature.home.state.post.HomePostSideEffect
import com.captures2024.soongan.feature.home.state.post.HomePostUIState
import com.captures2024.soongan.feature.home.utils.HomePostBottomModal
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
internal class HomePostViewModel
@Inject
constructor(
    savedStateHandle: SavedStateHandle
) : BaseViewModel<HomePostUIState, HomePostSideEffect, HomePostIntent>(savedStateHandle) {

    override fun createInitialState(savedStateHandle: SavedStateHandle): HomePostUIState {
        val info = savedStateHandle.toRoute<HomePostNavigator>()

        return HomePostUIState(
            post = UserPost.PhotoPost(
                id = info.id,
                url = info.url,
                title = info.title
            )
        )
    }

    override fun handleClientException(throwable: Throwable) {
        Timber.tag(TAG).e(throwable)
    }

    override suspend fun handleIntent(intent: HomePostIntent) {
        when (intent) {
            is HomePostIntent.OnClickComment -> onClickComment()

            is HomePostIntent.OnClickHeart -> onClickHeart()

            is HomePostIntent.OnClickMenu -> onClickMenu()

            is HomePostIntent.OnClickPhoto -> onClickPhoto()

            is HomePostIntent.OnClosedModal -> onClosedModal()

            is HomePostIntent.OnCommentValueChanged -> onCommentValueChanged(intent.inWritingComment)
        }
    }

    private fun onClickComment() {
        reduce {
            copy(
                isOpenModal = HomePostBottomModal.OPEN_COMMENT
            )
        }
    }

    private fun onClickHeart() {
        TODO("Not Impl yet")
    }

    private fun onClickMenu() {
        reduce {
            copy(
                isOpenModal = HomePostBottomModal.OPEN_REPORT
            )
        }
    }

    private fun onClickPhoto() {
        postSideEffect(HomePostSideEffect.NavigateToHomePostPhoto(currentState.post.url))
    }

    private fun onClosedModal() {
        reduce {
            copy(
                isOpenModal = HomePostBottomModal.CLOSED
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