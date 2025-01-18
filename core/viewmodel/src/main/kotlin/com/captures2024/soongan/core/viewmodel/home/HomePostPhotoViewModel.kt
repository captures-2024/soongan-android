package com.captures2024.soongan.core.viewmodel.home

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import com.captures2024.soongan.core.analytics.utils.LogElementArgument
import com.captures2024.soongan.core.common.base.BaseViewModel
import com.captures2024.soongan.core.common.base.UIIntent
import com.captures2024.soongan.core.common.base.UISideEffect
import com.captures2024.soongan.core.common.base.UIState
import com.captures2024.soongan.core.navigator.screen.main.home.HomePostPhotoNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomePostPhotoViewModel
@Inject
constructor(
    savedStateHandle: SavedStateHandle
) : BaseViewModel<HomePostPhotoViewModel.State, HomePostPhotoViewModel.Effect, HomePostPhotoViewModel.Intent>(savedStateHandle) {

    data class State(
        val url: String,
        val isLoading: Boolean = false,
        val isShowShimmer: Boolean = true,
    ) : UIState {

        override fun toLoggingElements(): Array<LogElementArgument> = arrayOf(
            LogElementArgument("url", url),
            LogElementArgument("isLoading", isLoading.toString()),
            LogElementArgument("isShowShimmer", isShowShimmer.toString()),
        )
    }

    sealed interface Effect : UISideEffect {

    }

    sealed interface Intent : UIIntent {

    }

    override fun createInitialState(savedStateHandle: SavedStateHandle): State {
        val info = savedStateHandle.toRoute<HomePostPhotoNavigator>()

        return State(url = info.url)
    }

    override fun handleClientException(throwable: Throwable) {
        TODO("Not yet implemented")
    }

    override suspend fun handleIntent(intent: Intent) {
        TODO("Not yet implemented")
    }

    companion object {
        private const val TAG = "HomePostVM"
    }
}