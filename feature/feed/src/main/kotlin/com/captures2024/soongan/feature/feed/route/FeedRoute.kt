package com.captures2024.soongan.feature.feed.route

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavOptions
import com.captures2024.soongan.core.designsystem.ui.util.extension.sgBottomBarPadding
import com.captures2024.soongan.core.viewmodel.feed.FeedViewModel
import com.captures2024.soongan.core.viewmodel.feed.FeedViewModel.Intent
import com.captures2024.soongan.feature.feed.ui.FeedFilterBottomSheet
import com.captures2024.soongan.feature.feed.ui.FeedScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun FeedRoute(
    navigateToPost: (Long, NavOptions?) -> Unit,
    getReportedPostId: () -> Long,
    feedViewModel: FeedViewModel = hiltViewModel(),
) {
    val uiState by feedViewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit) {
        feedViewModel.sideEffect.collect { effect ->
            when (effect) {
                is FeedViewModel.Effect.NavigateToHomePost -> navigateToPost(effect.postId, null)
            }
        }
    }

    LifecycleEventEffect(Lifecycle.Event.ON_RESUME) {
        val postId = getReportedPostId()

        if (postId != -1L) {
            feedViewModel.intent(Intent.HidePost(postId))
        }
    }

    FeedScreen(
        uiState = uiState,
        modifier = Modifier.fillMaxSize().sgBottomBarPadding(),
        onRefresh = { feedViewModel.intent(Intent.RefreshFeed) },
        onClickRound = { feedViewModel.intent(Intent.OnClickRound(it)) },
        onClickFilter = { feedViewModel.intent(Intent.OnClickFilter) },
        onClickPost = { feedViewModel.intent(Intent.OnClickPost(it)) },
    )

    if (uiState.isOpenFilterBottomSheet) {
        FeedFilterBottomSheet(
            orderType = uiState.postOrderType,
            onDismissRequest = { feedViewModel.intent(Intent.OnFilterDismissRequest) },
            onClickItem = { feedViewModel.intent(Intent.OnClickSortFilter(it)) },
        )
    }
}
