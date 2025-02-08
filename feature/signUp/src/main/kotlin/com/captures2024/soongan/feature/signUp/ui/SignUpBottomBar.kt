package com.captures2024.soongan.feature.signUp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.core.designsystem.component.HeightSpacer
import com.captures2024.soongan.core.designsystem.component.text.getSGNonScaleTextStyle
import com.captures2024.soongan.core.designsystem.component.text.SGText
import com.captures2024.soongan.core.designsystem.theme.SGColor
import com.captures2024.soongan.core.designsystem.theme.SGTypography
import com.captures2024.soongan.core.designsystem.util.DevicePreviews
import com.captures2024.soongan.feature.signUp.R

@Composable
internal fun SignUpBottomBar(
    title: String,
    enabled: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(115.dp)
            .background(color = SGColor.primaryA)
            .padding(
                horizontal = 40.dp,
                vertical = 15.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SGText(
            text = title,
            style = getSGNonScaleTextStyle(
                color = SGColor.hintGray,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                lineHeight = 16.sp,
                fontFamily = SGTypography.pretendard,
            )
        )

        HeightSpacer(12.dp)

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 48.dp),
            onClick = onClick,
            enabled = enabled,
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = SGColor.positive,
                contentColor = SGColor.primaryB,
                disabledContainerColor = SGColor.gray300,
            ),
        ) {
            SGText(
                text = stringResource(id = R.string.btn_next_text),
                style = getSGNonScaleTextStyle(
                    color = SGColor.primaryB,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    lineHeight = 24.sp,
                    fontFamily = SGTypography.pretendard,
                )
            )
        }
    }
}

@DevicePreviews
@Composable
private fun SignUpBottomBarNegativePreview() {
    SignUpBottomBar(
        title = stringResource(id = R.string.btn_nickname_input_title),
        enabled = false
    ) {

    }
}

@DevicePreviews
@Composable
private fun SignUpBottomBarPositivePreview() {
    SignUpBottomBar(
        title = stringResource(id = R.string.btn_nickname_input_title),
        enabled = true
    ) {

    }
}