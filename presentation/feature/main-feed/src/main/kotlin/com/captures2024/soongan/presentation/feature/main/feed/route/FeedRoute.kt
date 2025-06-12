package com.captures2024.soongan.presentation.feature.main.feed.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavOptions
import com.captures2024.soongan.presentation.feature.main.feed.component.screen.FeedScreen
import com.captures2024.soongan.presentation.viewmodel.main.feed.FeedViewModel
import com.captures2024.soongan.presentation.viewmodel.main.feed.FeedViewModel.Intent

@Composable
internal fun FeedRoute(
    navigateToPost: (Long, NavOptions?) -> Unit,
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

    FeedScreen(
        uiState = uiState,
        onRefresh = { feedViewModel.intent(Intent.RefreshFeed) },
        onLoadNextPage = { feedViewModel.intent(Intent.LoadNextPage) },
        onClickTitle = { feedViewModel.intent(Intent.OnClickTitle) },
        onSelectTitleOption = { feedViewModel.intent(Intent.OnSelectTitleOption(it)) },
        onClickFilter = { feedViewModel.intent(Intent.OnClickFilter) },
        onClickFilterItem = { feedViewModel.intent(Intent.OnClickFilterItem(it)) },
        onClickPost = { feedViewModel.intent(Intent.OnClickPost(it)) },
        onTitlePickerDismissRequest = { feedViewModel.intent(Intent.OnTitlePickerDismissRequest) },
        onFilterDismissRequest = { feedViewModel.intent(Intent.OnFilterDismissRequest) },
    )
}
