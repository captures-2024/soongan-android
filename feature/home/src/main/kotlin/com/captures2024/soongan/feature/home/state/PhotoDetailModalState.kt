package com.captures2024.soongan.feature.home.state

sealed interface PhotoDetailModalState {
    sealed interface Open : PhotoDetailModalState {
        data object ReportOpen : Open

        data class CommentOpen(
            val comment: String = "",
        ) : Open
    }

    data object Close : PhotoDetailModalState
}