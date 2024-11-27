package com.captures2024.soongan.feature.home.route

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.captures2024.soongan.core.android.utils.LocalAnalyticsHelper
import com.captures2024.soongan.core.model.UserPost
import com.captures2024.soongan.feature.home.RegistrationPostViewModel
import com.captures2024.soongan.feature.home.state.registration_post.RegistrationPostIntent
import com.captures2024.soongan.feature.home.state.registration_post.RegistrationPostSideEffect
import com.captures2024.soongan.feature.home.ui.registration_post.RegistrationPostScreen
import com.captures2024.soongan.feature.home.ui.registration_post.SubmitBottomSheetDialog

@Composable
internal fun RegistrationPostRoute(
    navigateToBack: () -> Unit,
    navigateToPost: (UserPost.PhotoPost) -> Unit,
    registrationPostViewModel: RegistrationPostViewModel = hiltViewModel()
) {
    val analyticsHelper = LocalAnalyticsHelper.current
    val uiState by registrationPostViewModel.state.collectAsStateWithLifecycle()

    val pickSingleMedia = rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        analyticsHelper.d(message = "pickSingleMedia - uri: $uri")
        registrationPostViewModel.intent(RegistrationPostIntent.InitMedia(uri))
    }

    LaunchedEffect(Unit) {
        registrationPostViewModel.sideEffect.collect { sideEffect ->
            analyticsHelper.d(message = "registrationPostViewModel.sideEffect.collect - sideEffect: $sideEffect")
            when (sideEffect) {
                is RegistrationPostSideEffect.OpenMediaPicker -> pickSingleMedia.launch(
                    PickVisualMediaRequest(
                        mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly,
                    )
                )

                is RegistrationPostSideEffect.NavigateToBack -> navigateToBack()

                is RegistrationPostSideEffect.NavigateToPost -> {
                    TODO("NavigateToPost using navigateToPost")
                }
            }
        }
    }

    LaunchedEffect(Unit) {
        registrationPostViewModel.intent(RegistrationPostIntent.Init)
    }

    RegistrationPostScreen(
        uiState = uiState,
        onBackPressed = navigateToBack,
        onTitleValueChanged = { registrationPostViewModel.intent(RegistrationPostIntent.OnTitleValueChanged(it)) },
        onClickSubmit = { registrationPostViewModel.intent(RegistrationPostIntent.OnClickSubmit) },
    )

    if (uiState.isOpenSubmitBottomSheet) {
        SubmitBottomSheetDialog(
            uiState = uiState,
            onClickConfirm = { registrationPostViewModel.intent(RegistrationPostIntent.OnClickSubmitRemote) },
            closeSheet = { registrationPostViewModel.intent(RegistrationPostIntent.OnClickCloseBottomSheet) }
        )
    }
}