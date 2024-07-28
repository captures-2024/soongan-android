package com.captures2024.soongan.feature.home.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavOptions
import com.captures2024.soongan.feature.home.PhotoDetailViewModel
import com.captures2024.soongan.feature.home.state.PhotoDetailModalState
import com.captures2024.soongan.feature.home.ui.photo.CommentBottomSheetDialog
import com.captures2024.soongan.feature.home.ui.photo.PhotoDetailScreen
import com.captures2024.soongan.feature.home.ui.photo.PostReportBottomSheetDialog
import timber.log.Timber

@Composable
internal fun PhotoDetailRoute(
    photoId: String,
    photoDetailViewModel: PhotoDetailViewModel = hiltViewModel(),
    navigateToBack: () -> Unit,
    navigateToControlImage: (String, NavOptions?) -> Unit
) {
    val uiState = photoDetailViewModel.uiState.collectAsStateWithLifecycle().value

    LaunchedEffect(key1 = true) {
        photoDetailViewModel.loadImage(photoId)
    }

    Timber.tag("PhotoDetailRoute").d("uiState = $uiState")

    PhotoDetailScreen(
        uiState = uiState,
        onClickBack = navigateToBack,
        onClickImage = navigateToControlImage,
        onClickMenu = { photoDetailViewModel.openModal(false) },
        onClickComment = { photoDetailViewModel.openModal(true) }
    )

    when (val reportMenuState = uiState.menuModalState) {
        is PhotoDetailModalState.Open.ReportOpen -> {
            PostReportBottomSheetDialog(
                reportState = reportMenuState,
                onClickReport = photoDetailViewModel::onReportValueChanged,
                closeSheet = { photoDetailViewModel.closeModal(false) }
            )
        }
        else -> Unit
    }
    when (val commentState = uiState.commentModalState) {
        is PhotoDetailModalState.Open.CommentOpen -> {
            CommentBottomSheetDialog(
                closeSheet = { photoDetailViewModel.closeModal(true) },
                comment = commentState.comment,
                onCommentValueChanged = photoDetailViewModel::onCommentValueChanged
            )
        }
        else -> Unit
    }
}