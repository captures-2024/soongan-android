package com.captures2024.soongan.feature.home

import androidx.lifecycle.SavedStateHandle
import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.common.base.BaseViewModel
import com.captures2024.soongan.core.model.UserPost
import com.captures2024.soongan.core.model.mock.samplePhotos
import com.captures2024.soongan.feature.home.state.home.HomeIntent
import com.captures2024.soongan.feature.home.state.home.HomeSideEffect
import com.captures2024.soongan.feature.home.state.home.HomeUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class HomeViewModel
@Inject
constructor(
    private val analyticsHelper: AnalyticsHelper,
    savedStateHandle: SavedStateHandle
) : BaseViewModel<HomeUIState, HomeSideEffect, HomeIntent>(savedStateHandle) {
    private val samples by lazy { samplePhotos.filterIsInstance<UserPost.PhotoPost>().take(3) }

    init {
        reduce { copy(myPosts = samples) }
    }

    override fun createInitialState(savedStateHandle: SavedStateHandle): HomeUIState {
        return HomeUIState()
    }

    override fun handleClientException(throwable: Throwable) {
        TODO("Not yet implemented")
    }

    override suspend fun handleIntent(intent: HomeIntent) {
        when (intent) {
            is HomeIntent.OnClickPlus -> onClickPlus()

            is HomeIntent.OnClickMyPost -> onClickMyPost(intent.myPost)

            is HomeIntent.OnToggleWeeklyDaily -> onToggleWeeklyDaily()

            is HomeIntent.OnClickInfo -> onClickInfo()

            is HomeIntent.OnClickRightArrow -> onClickRightArrow()

            is HomeIntent.OnCloseBottomSheet -> onCloseBottomSheet()
        }
    }

    private fun onClickPlus() {
        postSideEffect(HomeSideEffect.NavigateToHomeExhibition)
    }

    private fun onClickMyPost(myPost: UserPost.PhotoPost) {
        postSideEffect(HomeSideEffect.NavigateToHomePost(myPost))
    }

    private fun onToggleWeeklyDaily() {
        reduce {
            copy(
                isWeeklySelected = !isWeeklySelected
            )
        }
    }

    private fun onClickInfo() {
        reduce {
            copy(
                isOpenBottomSheet = true
            )
        }
    }

    private fun onClickRightArrow() {
        postSideEffect(HomeSideEffect.NavigateToHomeGallery)
    }

    private fun onCloseBottomSheet() {
        reduce {
            copy(
                isOpenBottomSheet = false
            )
        }
    }


    companion object {
        private const val TAG = "HomeVM"
    }
}