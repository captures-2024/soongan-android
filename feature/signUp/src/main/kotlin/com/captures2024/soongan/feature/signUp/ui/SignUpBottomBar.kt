package com.captures2024.soongan.feature.signUp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.core.designsystem.component.NonScaleText
import com.captures2024.soongan.core.designsystem.theme.Positive
import com.captures2024.soongan.core.designsystem.theme.PrimaryA
import com.captures2024.soongan.core.designsystem.theme.PrimaryB
import com.captures2024.soongan.core.designsystem.util.DevicePreviews
import com.captures2024.soongan.feature.signUp.R

@Composable
internal fun SignUpBottomBar(
    modifier: Modifier = Modifier,
    title: String,
    enabled: Boolean,
    onClick: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(115.dp)
            .background(color = PrimaryA)
            .padding(
                horizontal = 40.dp,
                vertical = 15.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        NonScaleText(
            text = title,
            color = PrimaryB,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium
        )
        Spacer(modifier = Modifier.height(12.dp))
        Button(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .height(48.dp),
            onClick = onClick,
            enabled = enabled,
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Positive,
                contentColor = PrimaryB,
                disabledContainerColor = Color(0xFFA7A7A7),
            ),
        ) {
            NonScaleText(
                text = stringResource(id = R.string.btn_next_text),
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@DevicePreviews
@Composable
private fun SignUpBottomBarNegativePreview() {
    SignUpBottomBar(
        title = "다음이 마지막 단계입니다!",
        enabled = false
    )
}

@DevicePreviews
@Composable
private fun SignUpBottomBarPositivePreview() {
    SignUpBottomBar(
        title = "다음이 마지막 단계입니다!",
        enabled = true
    )
}