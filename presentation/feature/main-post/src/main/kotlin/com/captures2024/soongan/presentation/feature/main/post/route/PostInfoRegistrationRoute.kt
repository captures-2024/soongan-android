package com.captures2024.soongan.presentation.feature.main.post.route

import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.captures2024.soongan.presentation.feature.main.post.component.screen.PostInfoRegistrationScreen
import com.captures2024.soongan.presentation.viewmodel.main.home.RegistrationPostViewModel

@Composable
internal fun PostInfoRegistrationRoute(
    navigateToBack: () -> Unit,
    navigateToPost: (Long) -> Unit,
    viewModel: RegistrationPostViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()

    val pickSingleMedia = rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        viewModel.intent(RegistrationPostViewModel.Intent.InitMedia(uri))
    }

    LaunchedEffect(viewModel.sideEffect) {
        viewModel.sideEffect.collect { effect ->
            when (effect) {
                is RegistrationPostViewModel.Effect.NavigateToBack -> navigateToBack()
                is RegistrationPostViewModel.Effect.NavigateToPost -> navigateToPost(effect.postId)
                is RegistrationPostViewModel.Effect.OpenMediaPicker -> pickSingleMedia.launch(
                    PickVisualMediaRequest(
                        mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly,
                    ),
                )
            }
        }
    }

    BackHandler {
        viewModel.intent(RegistrationPostViewModel.Intent.OnClickBack)
    }

    PostInfoRegistrationScreen(
        state = state,
        onClickBack = { viewModel.intent(RegistrationPostViewModel.Intent.OnClickBack) },
        onClickCancelBackDialog = { viewModel.intent(RegistrationPostViewModel.Intent.OnClickCancelBackDialog) },
        onClickConfirmBackDialog = { viewModel.intent(RegistrationPostViewModel.Intent.OnClickConfirmBackDialog) },
        onTitleValueChanged = { viewModel.intent(RegistrationPostViewModel.Intent.OnTitleValueChanged(it)) },
        onClickSubmit = { viewModel.intent(RegistrationPostViewModel.Intent.OnClickSubmit) },
        onClickTermsSubmitBottomSheet = { viewModel.intent(RegistrationPostViewModel.Intent.OnClickTermsSubmitBottomSheet) },
        onClickCheckBoxSubmitBottomSheet = { viewModel.intent(RegistrationPostViewModel.Intent.OnClickCheckBoxSubmitBottomSheet) },
        onClickConfirmSubmitBottomSheet = { viewModel.intent(RegistrationPostViewModel.Intent.OnClickConfirmSubmitBottomSheet) },
        onClickCancelSubmitBottomSheet = { viewModel.intent(RegistrationPostViewModel.Intent.OnClickCancelSubmitBottomSheet) },
    )
}
