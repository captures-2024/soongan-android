package com.captures2024.soongan.feature.home.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.captures2024.soongan.core.viewmodel.home.HomePostViewModel
import com.captures2024.soongan.feature.home.ui.post.HomePostScreen
import com.captures2024.soongan.feature.home.ui.post.comment.HomePostCommentBottomSheetDialog
import com.captures2024.soongan.core.viewmodel.model.HomePostBottomModalState

@Composable
internal fun HomePostRoute(
    navigateToBack: () -> Unit,
    navigateToHomePostPhoto: (String) -> Unit,
    homePostViewModel: HomePostViewModel = hiltViewModel()
) {
    val uiState by homePostViewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit) {
        homePostViewModel.sideEffect.collect { effect ->
            when (effect) {
                is HomePostViewModel.Effect.NavigateToHomePostPhoto -> navigateToHomePostPhoto(effect.url)

            }
        }
    }

    HomePostScreen(
        uiState = uiState,
        onBackPressed = navigateToBack,
        onClickPhoto = { homePostViewModel.intent(HomePostViewModel.Intent.OnClickPhoto) },
        onClickMenu = { homePostViewModel.intent(HomePostViewModel.Intent.OnClickMenu) },
        onClickHeart = { homePostViewModel.intent(HomePostViewModel.Intent.OnClickHeart) },
        onClickComment = { homePostViewModel.intent(HomePostViewModel.Intent.OnClickComment) },
    )

    when (uiState.isOpenModal) {
        HomePostBottomModalState.OPEN_COMMENT -> HomePostCommentBottomSheetDialog(
            comment = uiState.inWritingComment,
            closeSheet = { homePostViewModel.intent(HomePostViewModel.Intent.OnClosedModal) },
            onCommentValueChanged = { homePostViewModel.intent(HomePostViewModel.Intent.OnCommentValueChanged(it)) }
        )

        HomePostBottomModalState.OPEN_REPORT -> {

        }

        else -> Unit
    }
}