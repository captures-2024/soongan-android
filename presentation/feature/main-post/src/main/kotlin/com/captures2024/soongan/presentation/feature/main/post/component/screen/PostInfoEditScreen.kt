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
import com.captures2024.soongan.presentation.designsystem.ui.component.dialog.SGDoubleButtonDialog
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGColor
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGTheme
import com.captures2024.soongan.presentation.designsystem.ui.util.DevicePreviews
import com.captures2024.soongan.presentation.feature.main.post.R
import com.captures2024.soongan.presentation.feature.main.post.component.info.PostInfoTopBarComponent
import com.captures2024.soongan.presentation.feature.main.post.component.info.input.PostInfoInputComponent
import com.captures2024.soongan.presentation.feature.main.post.component.info.submit.PostInfoSubmitBottomSheet
import com.captures2024.soongan.presentation.viewmodel.main.post.PostInfoEditViewModel

@Composable
internal fun PostInfoEditScreen(
    state: PostInfoEditViewModel.State,
    modifier: Modifier = Modifier,
    onClickBack: () -> Unit,
    onTitleValueChanged: (String) -> Unit,
    onClickEdit: () -> Unit,
    onClickCancelBackDialog: () -> Unit,
    onClickConfirmBackDialog: () -> Unit,
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
                onClickBack = onClickBack,
            )
        },
        containerColor = SGColor.transparent,
    ) { paddingValues ->
        PostInfoInputComponent(
            model = ImageRequest.Builder(context)
                .data(state.defaultUrl)
                .build(),
            value = state.editTitle,
            maxInputLength = state.maxInputLength,
            isEnabledButton = state.isEditable,
            buttonContent = stringResource(R.string.button_edit),
            modifier = Modifier.padding(paddingValues),
            onValueChange = onTitleValueChanged,
            onClickButton = onClickEdit,
        )
    }

    if (state.isShowBackDialog) {
        SGDoubleButtonDialog(
            content = stringResource(R.string.post_info_edit_back_dialog_content),
            confirmContent = stringResource(R.string.post_info_edit_back_dialog_button_confirm),
            onClickConfirm = onClickConfirmBackDialog,
            cancelContent = stringResource(R.string.post_info_edit_back_dialog_button_cancel),
            onClickCancel = onClickCancelBackDialog,
            onDismissRequest = onClickCancelBackDialog,
        )
    }

    if (state.isOpenSubmitBottomSheet) {
        PostInfoSubmitBottomSheet(
            title = state.editTitle,
            content = stringResource(R.string.post_info_edit_submit_body_title_content),
            isChecked = state.isCheckedSubmitBottomSheet,
            onClickTerms = onClickTermsSubmitBottomSheet,
            onClickCheckBox = onClickCheckBoxSubmitBottomSheet,
            onClickConfirm = onClickConfirmSubmitBottomSheet,
            onClickCancel = onClickCancelSubmitBottomSheet,
        )
    }
}

@DevicePreviews
@Composable
private fun PreviewPostInfoEditScreen() {
    SGTheme {
        PostInfoEditScreen(
            state = PostInfoEditViewModel.State(
                postId = -1L,
                defaultUrl = "test",
                defaultTitle = "test",
                editTitle = "test",
                maxInputLength = 15,
                isShowBackDialog = false,
                isOpenSubmitBottomSheet = false,
                isCheckedSubmitBottomSheet = false,
            ),
            onClickBack = {},
            onTitleValueChanged = {},
            onClickEdit = {},
            onClickCancelBackDialog = {},
            onClickConfirmBackDialog = {},
            onClickTermsSubmitBottomSheet = {},
            onClickCheckBoxSubmitBottomSheet = {},
            onClickConfirmSubmitBottomSheet = {},
            onClickCancelSubmitBottomSheet = {},
        )
    }
}
