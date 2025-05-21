package com.captures2024.soongan.presentation.feature.main.home.component.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.captures2024.soongan.core.designsystem.ui.component.dialog.SGDoubleButtonDialog
import com.captures2024.soongan.core.designsystem.ui.theme.SGColor
import com.captures2024.soongan.core.designsystem.ui.theme.SGTheme
import com.captures2024.soongan.core.designsystem.ui.util.DevicePreviews
import com.captures2024.soongan.presentation.feature.main.home.R
import com.captures2024.soongan.presentation.feature.main.home.component.registration.RegistrationPostBodyComponent
import com.captures2024.soongan.presentation.feature.main.home.component.registration.RegistrationPostSubmitBottomSheet
import com.captures2024.soongan.presentation.feature.main.home.component.registration.RegistrationPostTopBarComponent
import com.captures2024.soongan.presentation.viewmodel.main.home.RegistrationPostViewModel

@Composable
internal fun RegistrationPostScreen(
    state: RegistrationPostViewModel.State,
    onClickBack: () -> Unit,
    onClickCancelBackDialog: () -> Unit,
    onClickConfirmBackDialog: () -> Unit,
    onTitleValueChanged: (String) -> Unit,
    onClickSubmit: () -> Unit,
    onClickConfirmSubmitBottomSheet: () -> Unit,
    onClickCancelSubmitBottomSheet: () -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize()
            .background(color = SGColor.BG.background),
        topBar = @Composable {
            RegistrationPostTopBarComponent(
                contestInfo = state.currentContestInfo,
                onBackPressed = onClickBack,
            )
        },
        containerColor = SGColor.transparent,
    ) { paddingValues ->
        RegistrationPostBodyComponent(
            state = state,
            modifier = Modifier.padding(paddingValues),
            onTitleValueChanged = onTitleValueChanged,
            onClickSubmit = onClickSubmit,
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
        RegistrationPostSubmitBottomSheet(
            state = state,
            onClickConfirm = onClickConfirmSubmitBottomSheet,
            onClickCancel = onClickCancelSubmitBottomSheet,
        )
    }
}

@DevicePreviews
@Composable
private fun PreviewRegistrationPostScreen() {
    SGTheme {
        RegistrationPostScreen(
            state = RegistrationPostViewModel.State(
                currentMedia = null,
                currentContestInfo = null,
                title = "",
                maxInputLength = 15,
                isShowBackDialog = false,
                isOpenSubmitBottomSheet = false,
            ),
            onClickBack = {},
            onClickCancelBackDialog = {},
            onClickConfirmBackDialog = {},
            onTitleValueChanged = {},
            onClickSubmit = {},
            onClickConfirmSubmitBottomSheet = {},
            onClickCancelSubmitBottomSheet = {},
        )
    }
}
