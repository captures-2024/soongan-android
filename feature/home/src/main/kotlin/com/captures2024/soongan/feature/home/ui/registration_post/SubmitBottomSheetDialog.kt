package com.captures2024.soongan.feature.home.ui.registration_post

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.core.designsystem.component.HeightSpacer
import com.captures2024.soongan.core.designsystem.component.NonScaleText
import com.captures2024.soongan.core.designsystem.theme.NanumSquareNeoFontFamily
import com.captures2024.soongan.core.designsystem.theme.PretendardFontFamily
import com.captures2024.soongan.core.designsystem.theme.SGColor
import com.captures2024.soongan.core.designsystem.util.DevicePreviews
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
    ) {
        Scaffold(
            modifier = modifier
                .fillMaxWidth()
                .height(264.dp),
            topBar = @Composable {
                SubmitBottomSheetDialogTopBar(onBackPressed = closeSheet)
            },
            containerColor = SGColor.transparent,
        ) { paddingValues ->
            Column(
                modifier = Modifier.fillMaxWidth()
                    .wrapContentHeight()
                    .padding(paddingValues)
                    .padding(horizontal = 20.dp)
                    .padding(top = 40.dp, bottom = 28.dp),
            ) {
                NonScaleText(
                    text = content,
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = NanumSquareNeoFontFamily,
                )
                HeightSpacer(48.dp)
                Box(
                    modifier = Modifier
                        .height(40.dp)
                        .fillMaxWidth()
                        .background(
                            color = SGColor.primaryA,
                            shape = RoundedCornerShape(8.dp),
                        )
                        .clickable(onClick = onClickConfirm),
                    contentAlignment = Alignment.Center
                ) {
                    NonScaleText(
                        text = stringResource(id = R.string.submit_bottom_sheet_confirm_button_title),
                        color = SGColor.primaryB,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        lineHeight = 20.sp,
                        fontFamily = PretendardFontFamily,
                    )
                }
            }
        }
    }
}

@DevicePreviews
@Composable
private fun SubmitBottomSheetDialogPreview() {
    SubmitBottomSheetDialog(
        uiState = RegistrationPostViewModel.State()
    )
}