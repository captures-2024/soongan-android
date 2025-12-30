package com.captures2024.soongan.presentation.feature.main.post.component.info.input

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.presentation.designsystem.ui.component.HeightSpacer
import com.captures2024.soongan.presentation.designsystem.ui.component.text.SGText
import com.captures2024.soongan.presentation.designsystem.ui.component.text.getSGNonScaleTextStyle
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGColor
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGTheme
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGTypography
import com.captures2024.soongan.presentation.designsystem.ui.theme.dropShadow
import com.captures2024.soongan.presentation.designsystem.ui.util.DevicePreviews
import com.captures2024.soongan.presentation.feature.main.post.R

@Composable
internal fun PostInfoInputTitleComponent(
    value: String,
    maxInputLength: Int,
    onValueChange: (String) -> Unit,
) {
    val focusManager = LocalFocusManager.current

    val commonStyle = getSGNonScaleTextStyle(
        color = SGColor.Grayscale.black100,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        lineHeight = 22.sp,
        fontFamily = SGTypography.pretendard,
        letterSpacing = (-5).em,
        textAlign = TextAlign.Center,
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
                            color = SGColor.black.copy(0.25f),
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
                            text = stringResource(R.string.post_info_edit_title_hint),
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
private fun PreviewPostInfoInputTitleComponent() {
    SGTheme {
        PostInfoInputTitleComponent(
            value = "",
            maxInputLength = 15,
            onValueChange = {},
        )
    }
}
