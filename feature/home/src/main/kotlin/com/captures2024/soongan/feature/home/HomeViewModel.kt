package com.captures2024.soongan.feature.home

import androidx.lifecycle.SavedStateHandle
import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.common.base.BaseViewModel
import com.captures2024.soongan.core.domain.usecase.home.GetHomeUseCase
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
    private val getHomeUseCase: GetHomeUseCase,
    savedStateHandle: SavedStateHandle
) : BaseViewModel<HomeUIState, HomeSideEffect, HomeIntent>(savedStateHandle) {

    init {
        intent(HomeIntent.Init)
    }

    override fun createInitialState(savedStateHandle: SavedStateHandle): HomeUIState {
        return HomeUIState()
    }

    override fun handleClientException(throwable: Throwable) {
        analyticsHelper.e(throwable = throwable)
    }

    override suspend fun handleIntent(intent: HomeIntent) {
        when (intent) {
            is HomeIntent.Init -> handleInit()

            is HomeIntent.OnClickPlus -> onClickPlus()

            is HomeIntent.OnClickPost -> handleOnClickPost(intent)

            is HomeIntent.OnToggleWeeklyDaily -> onToggleWeeklyDaily()

            is HomeIntent.OnClickInfo -> onClickInfo()

            is HomeIntent.OnClickRightArrow -> onClickRightArrow()

            is HomeIntent.OnCloseBottomSheet -> onCloseBottomSheet()
        }
    }

    private suspend fun handleInit() {
        val result = getHomeUseCase().getOrNull()

        if (result == null) {
            analyticsHelper.d(message = "result is null")
            return
        }

        val (contestInfo, postInfoList) = result

        reduce {
            copy(
                contestInfo = contestInfo,
                postList = postInfoList,
            )
        }
    }

    private fun onClickPlus() {
        postSideEffect(HomeSideEffect.NavigateToRegistrationPost)
    }

    private fun handleOnClickPost(intent: HomeIntent.OnClickPost) {
        postSideEffect(HomeSideEffect.NavigateToHomePost(intent.postInfo))
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