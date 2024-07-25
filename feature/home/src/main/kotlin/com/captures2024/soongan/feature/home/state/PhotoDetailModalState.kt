package com.captures2024.soongan.feature.home.state

sealed class PhotoDetailModalState {
    data class Open(
        val comment: String = ""
    ) : PhotoDetailModalState()
    data object Close : PhotoDetailModalState()
}