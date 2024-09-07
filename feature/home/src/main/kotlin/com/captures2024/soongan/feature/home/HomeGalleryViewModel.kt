package com.captures2024.soongan.feature.home

import androidx.lifecycle.SavedStateHandle
import com.captures2024.soongan.core.common.base.BaseViewModel
import com.captures2024.soongan.core.model.UserPost
import com.captures2024.soongan.core.model.mock.samplePhotos
import com.captures2024.soongan.feature.home.state.home_gallery.HomeGalleryIntent
import com.captures2024.soongan.feature.home.state.home_gallery.HomeGallerySideEffect
import com.captures2024.soongan.feature.home.state.home_gallery.HomeGalleryUIState
import com.captures2024.soongan.feature.home.utils.GalleryPhotoSortFilter
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class HomeGalleryViewModel
@Inject
constructor(
    savedStateHandle: SavedStateHandle
) : BaseViewModel<HomeGalleryUIState, HomeGallerySideEffect, HomeGalleryIntent>(savedStateHandle) {
    init {
        initMockUpPost()
    }

    override fun createInitialState(savedStateHandle: SavedStateHandle): HomeGalleryUIState {
        return HomeGalleryUIState()
    }

    override fun handleClientException(throwable: Throwable) {
        TODO("Not yet implemented")
    }

    override suspend fun handleIntent(intent: HomeGalleryIntent) {
        when (intent) {
            is HomeGalleryIntent.OnBottomModalDismissRequest -> onBottomModalDismissRequest()

            is HomeGalleryIntent.OnClickFilter -> onClickFilter()

            is HomeGalleryIntent.OnClickPost -> onClickPost(intent.post)

            is HomeGalleryIntent.OnClickSortFilter -> onClickSortFilter(intent.selectedSortFilter)
        }
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

    private fun onClickSortFilter(sortType: GalleryPhotoSortFilter) {
        reduce {
            copy(
                sortOrder = sortType
            )
        }
    }

    private fun onClickPost(post: UserPost.PhotoPost) {
        postSideEffect(HomeGallerySideEffect.NavigateToHomePost(post))
    }

    private fun initMockUpPost() {
        reduce {
            copy(
                photoList = samplePhotos
            )
        }
    }

    companion object {
        private const val TAG = "HomeGalleryVM"
    }
}