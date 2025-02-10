package com.captures2024.soongan.feature.home.ui.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.core.designsystem.component.text.SGText
import com.captures2024.soongan.core.designsystem.component.text.getSGNonScaleTextStyle
import com.captures2024.soongan.core.designsystem.theme.SGColor
import com.captures2024.soongan.core.designsystem.theme.SGTypography

@Composable
internal fun ContestPeriodText(
    text: String,
    period: String,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        SGText(
            text = text,
            style = getSGNonScaleTextStyle(
                color = SGColor.primaryA,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 14.sp,
                fontFamily = SGTypography.nanumSquareNeo,
                letterSpacing = 0.em,
            ),
        )

        SGText(
            text = "|",
            style = getSGNonScaleTextStyle(
                color = SGColor.primaryA,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                lineHeight = 14.sp,
                fontFamily = SGTypography.poppins,
                letterSpacing = 0.em,
            ),
        )

        SGText(
            text = period,
            style = getSGNonScaleTextStyle(
                color = SGColor.primaryA,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                lineHeight = 14.sp,
                fontFamily = SGTypography.poppins,
                letterSpacing = 0.em,
            ),
        )
    }
}
