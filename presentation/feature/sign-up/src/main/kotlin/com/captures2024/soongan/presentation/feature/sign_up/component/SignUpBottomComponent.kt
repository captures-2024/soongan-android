package com.captures2024.soongan.presentation.feature.sign_up.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.presentation.designsystem.ui.component.HeightSpacer
import com.captures2024.soongan.presentation.designsystem.ui.component.background.SGBackground
import com.captures2024.soongan.presentation.designsystem.ui.component.button.SGButtonType2
import com.captures2024.soongan.presentation.designsystem.ui.component.text.SGText
import com.captures2024.soongan.presentation.designsystem.ui.component.text.getSGNonScaleTextStyle
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGColor
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGTypography
import com.captures2024.soongan.presentation.designsystem.ui.util.DevicePreviews

@Composable
internal fun SignUpBottomComponent(
    description: String,
    content: String,
    enabled: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 115.dp)
            .background(color = Color(0xFFFAFAF8))
            .padding(
                horizontal = 40.dp,
                vertical = 20.dp,
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        SGText(
            text = description,
            style = getSGNonScaleTextStyle(
                color = SGColor.black60,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                lineHeight = 16.sp,
                fontFamily = SGTypography.pretendard,
            ),
        )

        HeightSpacer(12.dp)

        SGButtonType2(
            onClick = onClick,
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            content = @Composable {
                SGText(
                    text = content,
                    style = getSGNonScaleTextStyle(
                        color = SGColor.primaryB,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        lineHeight = 24.sp,
                        fontFamily = SGTypography.pretendard,
                    ),
                )
            },
        )
    }
}

@DevicePreviews
@Composable
private fun PreviewSignUpBottomComponent_Disable() {
    SGBackground {
        SignUpBottomComponent(
            description = "다음이 마지막 단계입니다!",
            content = "다음",
            enabled = false,
            onClick = {},
        )
    }
}

@DevicePreviews
@Composable
private fun PreviewSignUpBottomComponent_Enable() {
    SGBackground {
        SignUpBottomComponent(
            description = "다음이 마지막 단계입니다!",
            content = "다음",
            enabled = true,
            onClick = {},
        )
    }
}
