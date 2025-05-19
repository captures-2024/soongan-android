package com.captures2024.soongan.presentation.feature.main.home.component.registration

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.captures2024.soongan.core.designsystem.ui.component.HeightSpacer
import com.captures2024.soongan.core.designsystem.ui.component.WeightSpacer
import com.captures2024.soongan.core.designsystem.ui.component.button.SGTextButtonType2
import com.captures2024.soongan.core.designsystem.ui.component.text.SGText
import com.captures2024.soongan.core.designsystem.ui.component.text.getSGNonScaleTextStyle
import com.captures2024.soongan.core.designsystem.ui.theme.SGColor
import com.captures2024.soongan.core.designsystem.ui.theme.SGTheme
import com.captures2024.soongan.core.designsystem.ui.theme.SGTypography
import com.captures2024.soongan.core.designsystem.ui.theme.dropShadow
import com.captures2024.soongan.core.designsystem.ui.util.DevicePreviews
import com.captures2024.soongan.presentation.feature.main.home.R
import com.captures2024.soongan.presentation.viewmodel.main.home.RegistrationPostViewModel

@Composable
internal fun RegistrationPostBodyComponent(
    state: RegistrationPostViewModel.State,
    modifier: Modifier = Modifier,
    onTitleValueChanged: (String) -> Unit,
    onClickSubmit: () -> Unit,
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(
                bottom = 58.dp,
            )
            .padding(horizontal = 20.dp)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(state.currentMedia)
                .build(),
            contentDescription = stringResource(R.string.photo_button_description),
            modifier = modifier
                .width(353.dp)
                .height(353.dp)
                .dropShadow(RectangleShape)
                .background(SGColor.white),
            contentScale = ContentScale.Crop,
        )

        HeightSpacer(36.dp)

        TitleInputTextField(
            maxInputLength = state.maxInputLength,
            value = state.title,
            onValueChange = onTitleValueChanged,
        )

        WeightSpacer(1f)

        SGTextButtonType2(
            text = stringResource(R.string.register_button_content),
            enabled = state.currentMedia != null && state.title.isNotEmpty(),
            onClick = onClickSubmit,
        )

        HeightSpacer(8.dp)

        SGText(
            text = stringResource(R.string.register_button_info_description),
            style = getSGNonScaleTextStyle(
                color = SGColor.primaryA,
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                lineHeight = 12.sp,
                fontFamily = SGTypography.pretendard,
                letterSpacing = (-5).em,
            ),
        )
    }
}

@Composable
private fun TitleInputTextField(
    maxInputLength: Int,
    value: String,
    onValueChange: (String) -> Unit,
) {
    val focusManager = LocalFocusManager.current

    val commonStyle = getSGNonScaleTextStyle(
        color = SGColor.primaryA,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        lineHeight = 22.sp,
        fontFamily = SGTypography.pretendard,
        letterSpacing = (-5).em,
    )

    Column(horizontalAlignment = Alignment.End) {
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.width(289.dp)
                .height(40.dp),
            textStyle = commonStyle,
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier.fillMaxSize()
                        .dropShadow(
                            shape = RectangleShape,
                            color = SGColor.Grayscale.black100.copy(0.25f),
                            offsetX = 0.dp,
                            offsetY = 2.dp,
                            blur = 4.dp,
                            spread = 0.dp,
                        )
                        .background(
                            color = SGColor.Grayscale.white,
                            shape = RoundedCornerShape(4.dp),
                        ),
                    contentAlignment = Alignment.Center,
                ) {
                    innerTextField()

                    if (value.isEmpty()) {
                        SGText(
                            text = stringResource(R.string.register_title_input_hint),
                            style = commonStyle.copy(color = SGColor.buttonDisableGray),
                        )
                    }
                }
            },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                },
            ),
        )

        HeightSpacer(8.dp)

        Row(
            modifier = Modifier.padding(end = 4.dp),
        ) {
            SGText(
                text = "${value.length}/$maxInputLength",
                style = getSGNonScaleTextStyle(
                    color = SGColor.primaryA,
                    fontSize = 8.sp,
                    fontWeight = FontWeight.Normal,
                    lineHeight = 8.sp,
                    fontFamily = SGTypography.pretendard,
                    letterSpacing = 0.em,
                ),
            )
        }
    }
}

@DevicePreviews
@Composable
private fun PreviewRegistrationPostBodyComponent() {
    SGTheme {
        RegistrationPostBodyComponent(
            state = RegistrationPostViewModel.State(
                currentMedia = null,
                currentContestInfo = null,
                title = "",
                maxInputLength = 15,
                isShowBackDialog = false,
                isOpenSubmitBottomSheet = false,
            ),
            onTitleValueChanged = {},
            onClickSubmit = {},
        )
    }
}
