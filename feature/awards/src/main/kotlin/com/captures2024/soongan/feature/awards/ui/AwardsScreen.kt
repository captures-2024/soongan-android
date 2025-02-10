package com.captures2024.soongan.feature.awards.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.core.designsystem.component.text.SGText
import com.captures2024.soongan.core.designsystem.component.text.getSGNonScaleTextStyle
import com.captures2024.soongan.core.designsystem.theme.SGColor
import com.captures2024.soongan.core.designsystem.theme.SGTypography
import com.captures2024.soongan.core.designsystem.util.DevicePreviews

@Composable
internal fun AwardsScreen(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxSize()
            .background(SGColor.primaryB),
        contentAlignment = Alignment.Center,
    ) {
        SGText(
            text = "awards",
            style = getSGNonScaleTextStyle(
                color = SGColor.black,
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 36.sp,
                fontFamily = SGTypography.nanumSquareNeo,
            )
        )
    }
}

@DevicePreviews
@Composable
private fun AwardsScreenPreview() {
    AwardsScreen()
}
