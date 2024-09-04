package com.captures2024.soongan.feature.home

import androidx.lifecycle.SavedStateHandle
import com.captures2024.soongan.core.common.base.BaseViewModel
import com.captures2024.soongan.feature.home.state.home_gallery.HomeGalleryIntent
import com.captures2024.soongan.feature.home.state.home_gallery.HomeGallerySideEffect
import com.captures2024.soongan.feature.home.state.home_gallery.HomeGalleryUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class HomeGalleryViewModel
@Inject
constructor(
    savedStateHandle: SavedStateHandle
) : BaseViewModel<HomeGalleryUIState, HomeGallerySideEffect, HomeGalleryIntent>(savedStateHandle) {
    override fun createInitialState(savedStateHandle: SavedStateHandle): HomeGalleryUIState {
        return HomeGalleryUIState()
    }

    override fun handleClientException(throwable: Throwable) {
        TODO("Not yet implemented")
    }

    override suspend fun handleIntent(intent: HomeGalleryIntent) {
        TODO("Not yet implemented")
    }

    companion object {
        private const val TAG = "HomeGalleryVM"
    }
}