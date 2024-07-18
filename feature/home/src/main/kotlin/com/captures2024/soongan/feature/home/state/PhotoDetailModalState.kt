package com.captures2024.soongan.feature.home.state

sealed interface PhotoDetailModalState {
    data object Open : PhotoDetailModalState
    data object Close : PhotoDetailModalState
}