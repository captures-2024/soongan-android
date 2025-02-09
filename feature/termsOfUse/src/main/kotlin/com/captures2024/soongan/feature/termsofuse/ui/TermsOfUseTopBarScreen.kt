package com.captures2024.soongan.feature.termsofuse.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.core.designsystem.component.WidthSpacer
import com.captures2024.soongan.core.designsystem.component.text.SGText
import com.captures2024.soongan.core.designsystem.component.text.getSGNonScaleTextStyle
import com.captures2024.soongan.core.designsystem.theme.SGColor
import com.captures2024.soongan.core.designsystem.theme.SGTypography
import com.captures2024.soongan.core.designsystem.util.DevicePreviews

@Composable
internal fun TermsOfUseTopBarScreen(
    modifier: Modifier = Modifier,
    onClickBack: () -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(SGColor.black),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        WidthSpacer(8.dp)

        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "",
            tint = Color.White,
            modifier = Modifier.clickable(onClick = onClickBack)
        )

        WidthSpacer(8.dp)

        SGText(
            text = "TermsOfUseTopBarScreen",
            style = getSGNonScaleTextStyle(
                color = SGColor.white,
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal,
                lineHeight = 18.sp,
                fontFamily = SGTypography.nanumSquareNeo,
                letterSpacing = 0.em,
            )
        )
    }
}

@DevicePreviews
@Composable
private fun TermsOfUseTopBarScreenPreview() {
    TermsOfUseTopBarScreen(onClickBack = {})
}