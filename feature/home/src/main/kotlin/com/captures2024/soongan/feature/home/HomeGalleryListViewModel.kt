package com.captures2024.soongan.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.captures2024.soongan.core.model.mock.samplePhotos
import com.captures2024.soongan.feature.home.state.HomeGalleryListUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeGalleryListViewModel
@Inject
constructor(

) : ViewModel() {
    val uiState: StateFlow<HomeGalleryListUIState> = flow<HomeGalleryListUIState> {
        emit(HomeGalleryListUIState.Loading)

        delay(500)

        emit(HomeGalleryListUIState.LoadPost(samplePhotos))
    }.stateIn(
        scope = viewModelScope,
        initialValue = HomeGalleryListUIState.Init,
        started = SharingStarted.WhileSubscribed(5_000),
    )



    companion object {
        private const val TAG = "HomeGalleryListVM"
    }
}