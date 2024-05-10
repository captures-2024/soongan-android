package com.captures2024.soongan.feature.intro.viewmodel

sealed interface IntroState {
    data object Loading : IntroState
    data object Sign : IntroState
    data object Main : IntroState
}