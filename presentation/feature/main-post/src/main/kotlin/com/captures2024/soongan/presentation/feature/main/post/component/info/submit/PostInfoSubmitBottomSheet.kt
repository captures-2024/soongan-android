package com.captures2024.soongan.presentation.feature.main.post.component.info.submit

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGColor
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGTheme
import com.captures2024.soongan.presentation.designsystem.ui.util.DevicePreviews
import com.captures2024.soongan.presentation.feature.main.post.R
import com.captures2024.soongan.presentation.viewmodel.main.home.RegistrationPostViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun PostInfoSubmitBottomSheet(
    state: RegistrationPostViewModel.State,
    modifier: Modifier = Modifier,
    onClickTerms: () -> Unit = {},
    onClickCheckBox: () -> Unit = {},
    onClickConfirm: () -> Unit = {},
    onClickCancel: () -> Unit = {},
) {
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true,
        confirmValueChange = { newState ->
            newState != SheetValue.Hidden //  Stop bottom sheet from hiding on outside press
        },
    )

    ModalBottomSheet(
        modifier = modifier,
        onDismissRequest = onClickCancel,
        sheetState = sheetState,
        containerColor = SGColor.Grayscale.white,
        content = @Composable {
            PostInfoSubmitBodyComponent(
                title = state.title,
                content = stringResource(R.string.post_info_registration_submit_body_title_content),
                isChecked = state.isCheckedSubmitBottomSheet,
                onClickTerms = onClickTerms,
                onClickCheckBox = onClickCheckBox,
                onClickConfirm = onClickConfirm,
                onClickCancel = onClickCancel,
            )
        },
    )
}

@DevicePreviews
@Composable
private fun PreviewPostInfoSubmitBottomSheet() {
    SGTheme {
        PostInfoSubmitBottomSheet(
            state = RegistrationPostViewModel.State(
                currentMedia = null,
                currentContestInfo = null,
                title = "test",
                maxInputLength = 15,
                isShowBackDialog = false,
                isOpenSubmitBottomSheet = false,
                isCheckedSubmitBottomSheet = false,
            ),
            onClickTerms = {},
            onClickCheckBox = {},
            onClickConfirm = {},
            onClickCancel = {},
        )
    }
}