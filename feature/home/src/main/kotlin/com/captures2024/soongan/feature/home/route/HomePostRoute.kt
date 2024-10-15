package com.captures2024.soongan.feature.home.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.captures2024.soongan.feature.home.HomePostViewModel
import com.captures2024.soongan.feature.home.state.post.HomePostIntent
import com.captures2024.soongan.feature.home.state.post.HomePostSideEffect
import com.captures2024.soongan.feature.home.ui.post.HomePostScreen
import com.captures2024.soongan.feature.home.ui.post.comment.HomePostCommentBottomSheetDialog
import com.captures2024.soongan.feature.home.utils.HomePostBottomModal

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
                is HomePostSideEffect.NavigateToHomePostPhoto -> navigateToHomePostPhoto(effect.url)

            }
        }
    }

    HomePostScreen(
        uiState = uiState,
        onBackPressed = navigateToBack,
        onClickPhoto = { homePostViewModel.intent(HomePostIntent.OnClickPhoto) },
        onClickMenu = { homePostViewModel.intent(HomePostIntent.OnClickMenu) },
        onClickHeart = { homePostViewModel.intent(HomePostIntent.OnClickHeart) },
        onClickComment = { homePostViewModel.intent(HomePostIntent.OnClickComment) },
    )

    when (uiState.isOpenModal) {
        HomePostBottomModal.OPEN_COMMENT -> HomePostCommentBottomSheetDialog(
            comment = uiState.inWritingComment,
            closeSheet = { homePostViewModel.intent(HomePostIntent.OnClosedModal) },
            onCommentValueChanged = { homePostViewModel.intent(HomePostIntent.OnCommentValueChanged(it)) }
        )

        HomePostBottomModal.OPEN_REPORT -> {

        }

        else -> Unit
    }
}