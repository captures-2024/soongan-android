package com.captures2024.soongan.feature.intro.viewmodel

sealed interface IntroActivityUiState {
    data object Loading : IntroActivityUiState
    data object Success : IntroActivityUiState
}