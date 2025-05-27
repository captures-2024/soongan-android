package com.captures2024.soongan.presentation.feature.main.home.component.registration

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.core.designsystem.icon.MyIconPack
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonFillLeftArrow
import com.captures2024.soongan.core.designsystem.ui.component.HeightSpacer
import com.captures2024.soongan.core.designsystem.ui.component.button.SGTextButtonType2
import com.captures2024.soongan.core.designsystem.ui.component.text.SGText
import com.captures2024.soongan.core.designsystem.ui.component.text.getSGNonScaleTextStyle
import com.captures2024.soongan.core.designsystem.ui.theme.SGColor
import com.captures2024.soongan.core.designsystem.ui.theme.SGTheme
import com.captures2024.soongan.core.designsystem.ui.theme.SGTypography
import com.captures2024.soongan.core.designsystem.ui.util.DevicePreviews
import com.captures2024.soongan.presentation.feature.main.home.R
import com.captures2024.soongan.presentation.viewmodel.main.home.RegistrationPostViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun RegistrationPostSubmitBottomSheet(
    state: RegistrationPostViewModel.State,
    modifier: Modifier = Modifier,
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
            RegistrationPostSubmitBodyComponent(
                title = state.title,
                onClickConfirm = onClickConfirm,
                onClickCancel = onClickCancel,
            )
        },
    )
}

@Composable
private fun RegistrationPostSubmitBodyComponent(
    title: String,
    onClickConfirm: () -> Unit,
    onClickCancel: () -> Unit,
) {
    val content = StringBuilder()
        .append(title)
        .append("\n")
        .append(stringResource(R.string.registration_post_submit_bs_title_content))
        .toString()

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center,
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                contentAlignment = Alignment.CenterStart,
            ) {
                Icon(
                    imageVector = MyIconPack.IconNonFillLeftArrow,
                    contentDescription = stringResource(R.string.back_button_description),
                    modifier = Modifier.clickable(onClick = onClickCancel),
                    tint = SGColor.Grayscale.black100,
                )
            }

            SGText(
                text = stringResource(R.string.registration_post_submit_bs_title),
                style = getSGNonScaleTextStyle(
                    color = SGColor.primaryA,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 20.sp,
                    fontFamily = SGTypography.pretendard,
                    letterSpacing = (-5).em,
                ),
            )
        }

        HeightSpacer(12.dp)

        HorizontalDivider(
            color = SGColor.buttonDisableGray,
            thickness = 1.dp,
        )

        Column(
            modifier = Modifier.fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = 20.dp)
                .padding(
                    top = 40.dp,
                    bottom = 28.dp,
                ),
        ) {
            SGText(
                text = content,
                style = getSGNonScaleTextStyle(
                    color = SGColor.primaryA,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    lineHeight = 24.sp,
                    fontFamily = SGTypography.pretendard,
                    letterSpacing = (-5).em,
                ),
                modifier = Modifier.padding(horizontal = 20.dp),
            )

            HeightSpacer(40.dp)

            SGTextButtonType2(
                text = stringResource(R.string.registration_post_submit_bs_button_content),
                modifier = Modifier.fillMaxWidth(),
                onClick = onClickConfirm,
            )
        }
    }
}

@DevicePreviews
@Composable
private fun PreviewRegistrationPostSubmitBottomSheet() {
    SGTheme {
        RegistrationPostSubmitBottomSheet(
            state = RegistrationPostViewModel.State(
                currentMedia = null,
                currentContestInfo = null,
                title = "test",
                maxInputLength = 15,
                isShowBackDialog = false,
                isOpenSubmitBottomSheet = false,
            ),
            onClickConfirm = {},
            onClickCancel = {},
        )
    }
}

@DevicePreviews
@Composable
private fun PreviewRegistrationPostSubmitBodyComponent() {
    SGTheme {
        RegistrationPostSubmitBodyComponent(
            title = "test",
            onClickConfirm = {},
            onClickCancel = {},
        )
    }
}
