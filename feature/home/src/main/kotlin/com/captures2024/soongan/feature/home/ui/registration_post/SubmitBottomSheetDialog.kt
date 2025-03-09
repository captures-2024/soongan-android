package com.captures2024.soongan.feature.home.ui.registration_post

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.core.designsystem.ui.component.HeightSpacer
import com.captures2024.soongan.core.designsystem.ui.component.button.SGTextButtonType2
import com.captures2024.soongan.core.designsystem.ui.component.text.SGText
import com.captures2024.soongan.core.designsystem.ui.component.text.getSGNonScaleTextStyle
import com.captures2024.soongan.core.designsystem.ui.theme.SGColor
import com.captures2024.soongan.core.designsystem.ui.theme.SGTypography
import com.captures2024.soongan.core.designsystem.ui.util.DevicePreviews
import com.captures2024.soongan.core.viewmodel.post.RegistrationPostViewModel
import com.captures2024.soongan.feature.home.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SubmitBottomSheetDialog(
    uiState: RegistrationPostViewModel.State,
    modifier: Modifier = Modifier,
    onClickConfirm: () -> Unit = {},
    closeSheet: () -> Unit = {},
) {
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true,
        confirmValueChange = { newState ->
            newState != SheetValue.Hidden //  Stop bottom sheet from hiding on outside press
        },
    )

    val content = StringBuilder()
        .append(uiState.title)
        .append("\n")
        .append(stringResource(R.string.submit_bottom_sheet_content))
        .toString()

    ModalBottomSheet(
        modifier = modifier,
        onDismissRequest = closeSheet,
        sheetState = sheetState,
        containerColor = SGColor.white,
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight(),
        ) {
            SubmitBottomSheetDialogTopBar(onBackPressed = closeSheet)

            Column(
                modifier = Modifier.fillMaxWidth()
                    .wrapContentHeight()
                    .padding(horizontal = 20.dp)
                    .padding(top = 40.dp, bottom = 28.dp),
            ) {
                SGText(
                    text = content,
                    style = getSGNonScaleTextStyle(
                        color = SGColor.primaryA,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        lineHeight = 24.sp,
                        fontFamily = SGTypography.nanumSquareNeo,
                        letterSpacing = (-5).em,
                    ),
                    modifier = Modifier.padding(horizontal = 20.dp),
                )

                HeightSpacer(40.dp)

                SGTextButtonType2(
                    text = stringResource(id = R.string.submit_bottom_sheet_confirm_button_title),
                    modifier = Modifier.fillMaxWidth(),
                    onClick = onClickConfirm,
                )
            }
        }
    }
}

@DevicePreviews
@Composable
private fun SubmitBottomSheetDialogPreview() {
    SubmitBottomSheetDialog(
        uiState = RegistrationPostViewModel.State(
            title = "입력한 제목",
        ),
    )
}
