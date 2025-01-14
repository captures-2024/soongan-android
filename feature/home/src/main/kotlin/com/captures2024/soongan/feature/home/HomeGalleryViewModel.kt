package com.captures2024.soongan.feature.home

import androidx.lifecycle.SavedStateHandle
import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.analytics.utils.LogElementArgument
import com.captures2024.soongan.core.common.base.BaseViewModel
import com.captures2024.soongan.core.domain.usecase.weekly.contests.GetGalleryUseCase
import com.captures2024.soongan.feature.home.state.home_gallery.HomeGalleryIntent
import com.captures2024.soongan.feature.home.state.home_gallery.HomeGallerySideEffect
import com.captures2024.soongan.feature.home.state.home_gallery.HomeGalleryUIState
import com.captures2024.soongan.feature.home.utils.PaginationStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class HomeGalleryViewModel
@Inject
constructor(
    private val analyticsHelper: AnalyticsHelper,
    private val getGalleryUseCase: GetGalleryUseCase,
    savedStateHandle: SavedStateHandle,
) : BaseViewModel<HomeGalleryUIState, HomeGallerySideEffect, HomeGalleryIntent>(savedStateHandle) {

    init {
        intent(HomeGalleryIntent.Init)
    }

    override fun createInitialState(savedStateHandle: SavedStateHandle): HomeGalleryUIState {
        return HomeGalleryUIState()
    }

    override fun handleClientException(throwable: Throwable) {
        analyticsHelper.e(throwable = throwable)
    }

    override suspend fun handleIntent(intent: HomeGalleryIntent) {
        when (intent) {
            is HomeGalleryIntent.Init -> fetchPostPage(page = 0)

            is HomeGalleryIntent.RefreshGallery -> fetchPostPage(page = 0, isRefreshing = true)

            is HomeGalleryIntent.LoadNextPage -> fetchPostPage(page = currentState.nextPage)

            is HomeGalleryIntent.OnBottomModalDismissRequest -> onBottomModalDismissRequest()

            is HomeGalleryIntent.OnClickFilter -> onClickFilter()

            is HomeGalleryIntent.OnClickPost -> onClickPost(intent)

            is HomeGalleryIntent.OnClickSortFilter -> onClickSortFilter(intent)
        }
    }

    private fun setUpLoading(isInitPage: Boolean, isRefreshing: Boolean) {
        if (isInitPage) {
            reduce {
                copy(
                    isRefreshing = isRefreshing,
                    paginationStatus = PaginationStatus.LOADING,
                    posts = emptyList()
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

    private suspend fun fetchPostPage(page: Int = 0, isRefreshing: Boolean = false) = launch {
        if (currentState.paginationStatus in listOf(
                PaginationStatus.LOADING,
                PaginationStatus.PAGINATING,
            )
        ) return@launch

        setUpLoading(isInitPage = (page == 0), isRefreshing = isRefreshing)

        getGalleryUseCase(
            params = GetGalleryUseCase.Params(
                round = null,
                orderType = currentState.postOrderType.name,
                page = page,
                pageSize = PAGE_SIZE,
            )
        ).onSuccess { galleryDto ->
            reduce {
                copy(
                    isRefreshing = false,
                    paginationStatus = when {
                        !galleryDto.hasNext -> PaginationStatus.EXHAUST
                        galleryDto.posts.isEmpty() -> PaginationStatus.EMPTY
                        else -> PaginationStatus.INACTIVE
                    },
                    posts = posts + galleryDto.posts,
                    nextPage = page + 1,
                    hasNextPage = galleryDto.hasNext
                )
            }
        }.onFailure {
            reduce {
                copy(
                    isRefreshing = false,
                    paginationStatus = PaginationStatus.ERROR

                )
            }
        }

        analyticsHelper.d(
            logVariable = arrayOf(
                LogElementArgument("pagingStatus", "${currentState.paginationStatus}"),
                LogElementArgument("page", "${currentState.nextPage}"),
                LogElementArgument("haspage", "${currentState.hasNextPage}"),
            ),
            message = "fetch post Page"
        )
    }

    private fun onBottomModalDismissRequest() {
        reduce {
            copy(
                isShowBottomSheet = false
            )
        }
    }

    private fun onClickFilter() {
        reduce {
            copy(
                isShowBottomSheet = true
            )
        }
    }

    private suspend fun onClickSortFilter(intent: HomeGalleryIntent.OnClickSortFilter) = launch {
        reduce {
            copy(
                isShowBottomSheet = false,
                postOrderType = intent.postOrderType
            )
        }

        fetchPostPage(page = 0)
    }

    private fun onClickPost(intent: HomeGalleryIntent.OnClickPost) {
//        postSideEffect(HomeGallerySideEffect.NavigateToHomePost(intent.post))
    }

    private fun initMockUpPost() {
        reduce {
            copy(
//                galleryInfo = samplePhotos
            )
        }
    }

    companion object {
        private const val TAG = "HomeGalleryVM"

        private const val PAGE_SIZE = 10
    }
}