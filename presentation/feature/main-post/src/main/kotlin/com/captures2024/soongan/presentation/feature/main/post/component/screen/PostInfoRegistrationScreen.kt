package com.captures2024.soongan.presentation.feature.main.post.component.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import coil.request.ImageRequest
import com.captures2024.soongan.core.model.AppConst
import com.captures2024.soongan.presentation.designsystem.ui.component.dialog.SGDoubleButtonDialog
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGColor
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGTheme
import com.captures2024.soongan.presentation.designsystem.ui.util.DevicePreviews
import com.captures2024.soongan.presentation.feature.main.post.R
import com.captures2024.soongan.presentation.feature.main.post.component.info.PostInfoTopBarComponent
import com.captures2024.soongan.presentation.feature.main.post.component.info.input.PostInfoInputComponent
import com.captures2024.soongan.presentation.feature.main.post.component.info.submit.PostInfoSubmitBottomSheet
import com.captures2024.soongan.presentation.viewmodel.main.home.RegistrationPostViewModel

@Composable
internal fun PostInfoRegistrationScreen(
    state: RegistrationPostViewModel.State,
    modifier: Modifier = Modifier,
    onClickBack: () -> Unit,
    onClickCancelBackDialog: () -> Unit,
    onClickConfirmBackDialog: () -> Unit,
    onTitleValueChanged: (String) -> Unit,
    onClickSubmit: () -> Unit,
    onClickTermsSubmitBottomSheet: () -> Unit,
    onClickCheckBoxSubmitBottomSheet: () -> Unit,
    onClickConfirmSubmitBottomSheet: () -> Unit,
    onClickCancelSubmitBottomSheet: () -> Unit,
) {
    val context = LocalContext.current

    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .background(color = SGColor.BG.background),
        topBar = @Composable {
            PostInfoTopBarComponent(
                round = state.currentContestInfo?.round ?: 0,
                subject = state.currentContestInfo?.subject ?: AppConst.EMPTY_STRING,
                onClickBack = onClickBack,
            )
        },
        containerColor = SGColor.transparent,
    ) { paddingValues ->
        PostInfoInputComponent(
            model = ImageRequest.Builder(context)
                .data(state.currentMedia)
                .build(),
            value = state.title,
            maxInputLength = state.maxInputLength,
            isEnabledButton = state.isEnabled,
            buttonContent = stringResource(R.string.button_submit),
            modifier = Modifier.padding(paddingValues),
            onValueChange = onTitleValueChanged,
            onClickButton = onClickSubmit,
        )
    }

    if (state.isShowBackDialog) {
        SGDoubleButtonDialog(
            content = stringResource(R.string.registration_post_back_dialog_content),
            confirmContent = stringResource(R.string.registration_post_back_dialog_button_confirm),
            onClickConfirm = onClickConfirmBackDialog,
            cancelContent = stringResource(R.string.registration_post_back_dialog_button_cancel),
            onClickCancel = onClickCancelBackDialog,
            onDismissRequest = onClickCancelBackDialog,
        )
    }

    if (state.isOpenSubmitBottomSheet) {
        PostInfoSubmitBottomSheet(
            state = state,
            onClickTerms = onClickTermsSubmitBottomSheet,
            onClickCheckBox = onClickCheckBoxSubmitBottomSheet,
            onClickConfirm = onClickConfirmSubmitBottomSheet,
            onClickCancel = onClickCancelSubmitBottomSheet,
        )
    }
}

@DevicePreviews
@Composable
private fun PreviewPostInfoRegistrationScreen() {
    SGTheme {
        PostInfoRegistrationScreen(
            state = RegistrationPostViewModel.State(
                currentMedia = null,
                currentContestInfo = null,
                title = "",
                maxInputLength = 15,
                isShowBackDialog = false,
                isOpenSubmitBottomSheet = false,
                isCheckedSubmitBottomSheet = false,
            ),
            onClickBack = {},
            onClickCancelBackDialog = {},
            onClickConfirmBackDialog = {},
            onTitleValueChanged = {},
            onClickSubmit = {},
            onClickTermsSubmitBottomSheet = {},
            onClickCheckBoxSubmitBottomSheet = {},
            onClickConfirmSubmitBottomSheet = {},
            onClickCancelSubmitBottomSheet = {},
        )
    }
}

@DevicePreviews
@Composable
private fun PreviewPostInfoRegistrationScreen_ShowBackDialog() {
    SGTheme {
        PostInfoRegistrationScreen(
            state = RegistrationPostViewModel.State(
                currentMedia = null,
                currentContestInfo = null,
                title = "",
                maxInputLength = 15,
                isShowBackDialog = true,
                isOpenSubmitBottomSheet = false,
                isCheckedSubmitBottomSheet = false,
            ),
            onClickBack = {},
            onClickCancelBackDialog = {},
            onClickConfirmBackDialog = {},
            onTitleValueChanged = {},
            onClickSubmit = {},
            onClickTermsSubmitBottomSheet = {},
            onClickCheckBoxSubmitBottomSheet = {},
            onClickConfirmSubmitBottomSheet = {},
            onClickCancelSubmitBottomSheet = {},
        )
    }
}