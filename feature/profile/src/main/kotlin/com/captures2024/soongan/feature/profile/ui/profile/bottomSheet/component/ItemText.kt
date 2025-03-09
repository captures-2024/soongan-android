package com.captures2024.soongan.feature.profile.ui.profile.bottomSheet.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.core.designsystem.ui.component.text.SGText
import com.captures2024.soongan.core.designsystem.ui.component.text.getSGNonScaleTextStyle
import com.captures2024.soongan.core.designsystem.ui.theme.SGColor
import com.captures2024.soongan.core.designsystem.ui.theme.SGTypography

@Composable
internal fun ItemText(text: String) = SGText(
    text = text,
    style = getSGNonScaleTextStyle(
        color = SGColor.black,
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 24.sp,
        fontFamily = SGTypography.nanumSquareNeo,
        letterSpacing = (-5).em,
    ),
)