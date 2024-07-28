package com.captures2024.soongan.feature.home.state

sealed interface PhotoDetailModalState {
    sealed interface Open : PhotoDetailModalState {
        data class ReportOpen(
            val reportType: ReportType = ReportType.NONE
        ) : Open

        data class CommentOpen(
            val comment: String = ""
        ) : Open
    }

    data object Close : PhotoDetailModalState
}