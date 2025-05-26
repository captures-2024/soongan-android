package com.captures2024.soongan.presentation.feature.main.post.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.captures2024.soongan.presentation.feature.main.post.component.screen.PostInfoEditScreen
import com.captures2024.soongan.presentation.viewmodel.main.post.PostInfoEditViewModel

@Composable
internal fun PostInfoEditRoute(
    navigateToBack: () -> Unit,
    viewModel: PostInfoEditViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(viewModel.sideEffect) {
        viewModel.sideEffect.collect { effect ->
            when (effect) {
                is PostInfoEditViewModel.Effect.NavigateToBack -> navigateToBack()
            }
        }
    }

    PostInfoEditScreen(
        state = state,
        onClickBack = { viewModel.intent(PostInfoEditViewModel.Intent.OnClickBack) },
        onTitleValueChanged = { viewModel.intent(PostInfoEditViewModel.Intent.OnTitleValueChanged(it)) },
        onClickEdit = { viewModel.intent(PostInfoEditViewModel.Intent.OnClickEdit) },
        onClickConfirmInitErrorDialog = { viewModel.intent(PostInfoEditViewModel.Intent.OnClickConfirmInitErrorDialog) },
    )
}
