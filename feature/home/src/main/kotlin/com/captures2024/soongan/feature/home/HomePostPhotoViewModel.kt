package com.captures2024.soongan.feature.home

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import com.captures2024.soongan.core.common.base.BaseViewModel
import com.captures2024.soongan.core.navigator.screen.main.HomePostPhotoNavigator
import com.captures2024.soongan.feature.home.state.postphoto.HomePostPhotoIntent
import com.captures2024.soongan.feature.home.state.postphoto.HomePostPhotoSideEffect
import com.captures2024.soongan.feature.home.state.postphoto.HomePostPhotoUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class HomePostPhotoViewModel
@Inject
constructor(
    savedStateHandle: SavedStateHandle
) : BaseViewModel<HomePostPhotoUIState, HomePostPhotoSideEffect, HomePostPhotoIntent>(savedStateHandle) {

    override fun createInitialState(savedStateHandle: SavedStateHandle): HomePostPhotoUIState {
        val info = savedStateHandle.toRoute<HomePostPhotoNavigator>()

        return HomePostPhotoUIState(
            url = info.url
        )
    }

    override fun handleClientException(throwable: Throwable) {
        TODO("Not yet implemented")
    }

    override suspend fun handleIntent(intent: HomePostPhotoIntent) {
        TODO("Not yet implemented")
    }

    companion object {
        private const val TAG = "HomePostVM"
    }
}