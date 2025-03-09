package com.captures2024.soongan.feature.home.route

import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavOptions
import androidx.navigation.navOptions
import com.captures2024.soongan.core.android.utils.LocalAnalyticsHelper
import com.captures2024.soongan.core.designsystem.ui.component.dialog.SGDoubleButtonDialog
import com.captures2024.soongan.core.viewmodel.post.RegistrationPostViewModel
import com.captures2024.soongan.feature.home.ui.registration_post.RegistrationPostScreen
import com.captures2024.soongan.feature.home.ui.registration_post.SubmitBottomSheetDialog

@Composable
internal fun RegistrationPostRoute(
    navigateToBack: () -> Unit,
    navigateToPost: (Long, NavOptions?) -> Unit,
    registrationPostViewModel: RegistrationPostViewModel = hiltViewModel()
) {
    val analyticsHelper = LocalAnalyticsHelper.current
    val uiState by registrationPostViewModel.state.collectAsStateWithLifecycle()

    val pickSingleMedia = rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        analyticsHelper.d(message = "pickSingleMedia - uri: $uri")
        registrationPostViewModel.intent(RegistrationPostViewModel.Intent.InitMedia(uri))
    }

    LaunchedEffect(Unit) {
        registrationPostViewModel.sideEffect.collect { sideEffect ->
            analyticsHelper.d(message = "registrationPostViewModel.sideEffect.collect - sideEffect: $sideEffect")
            when (sideEffect) {
                is RegistrationPostViewModel.Effect.OpenMediaPicker -> pickSingleMedia.launch(
                    PickVisualMediaRequest(
                        mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly,
                    )
                )

                is RegistrationPostViewModel.Effect.NavigateToBack -> navigateToBack()

                is RegistrationPostViewModel.Effect.NavigateToPost -> navigateToPost(
                    sideEffect.postId,
                    navOptions {
                        popUpTo(0)
                    },
                )
            }
        }
    }

    BackHandler {
        registrationPostViewModel.intent(RegistrationPostViewModel.Intent.OnClickBack)
    }

    LaunchedEffect(Unit) {
        registrationPostViewModel.intent(RegistrationPostViewModel.Intent.Init)
    }

    RegistrationPostScreen(
        uiState = uiState,
        onBackPressed = { registrationPostViewModel.intent(RegistrationPostViewModel.Intent.OnClickBack) },
        onTitleValueChanged = { registrationPostViewModel.intent(RegistrationPostViewModel.Intent.OnTitleValueChanged(it)) },
        onClickSubmit = { registrationPostViewModel.intent(RegistrationPostViewModel.Intent.OnClickSubmit) },
    )

    if (uiState.isOpenSubmitBottomSheet) {
        SubmitBottomSheetDialog(
            uiState = uiState,
            onClickConfirm = { registrationPostViewModel.intent(RegistrationPostViewModel.Intent.OnClickSubmitRemote) },
            closeSheet = { registrationPostViewModel.intent(RegistrationPostViewModel.Intent.OnClickCloseBottomSheet) }
        )
    }

    if (uiState.showBackDialog) {
        SGDoubleButtonDialog(
            content = "정말 작품 등록을\n하지 않으시겠어요?",
            confirmContent = "네",
            onClickConfirm = { registrationPostViewModel.intent(RegistrationPostViewModel.Intent.OnClickBackDialogConfirm) },
            cancelContent = "아니오",
            onClickCancel = { registrationPostViewModel.intent(RegistrationPostViewModel.Intent.OnClickBackDialogCancel) },
            onDismissRequest = { registrationPostViewModel.intent(RegistrationPostViewModel.Intent.OnClickBackDialogCancel) },
        )
    }
}