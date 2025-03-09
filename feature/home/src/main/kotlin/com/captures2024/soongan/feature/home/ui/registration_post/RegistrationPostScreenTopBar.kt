package com.captures2024.soongan.feature.home.ui.registration_post

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.core.designsystem.ui.component.text.SGText
import com.captures2024.soongan.core.designsystem.ui.component.text.getSGNonScaleTextStyle
import com.captures2024.soongan.core.designsystem.icon.MyIconPack
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonFillLeftArrow
import com.captures2024.soongan.core.designsystem.ui.theme.SGColor
import com.captures2024.soongan.core.designsystem.ui.theme.SGTypography
import com.captures2024.soongan.core.designsystem.ui.component.button.SGIconCircleButton
import com.captures2024.soongan.core.designsystem.ui.util.DevicePreviews

@Composable
internal fun RegistrationPostScreenTopBar(
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit = {},
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 14.dp),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            SGIconCircleButton(
                imageVector = MyIconPack.IconNonFillLeftArrow,
                contentDescription = "back",
                color = SGColor.primaryA,
                iconWidth = 20.dp,
                iconHeight = 16.dp,
                onClick = onBackPressed,
            )
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            SGText(
                text = "1회차",
                style = getSGNonScaleTextStyle(
                    color = SGColor.primaryA,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 20.sp,
                    fontFamily = SGTypography.nanumSquareNeo,
                    letterSpacing = 0.em,
                ),
            )

            SGText(
                text = "|",
                style = getSGNonScaleTextStyle(
                    color = SGColor.primaryA,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    lineHeight = 20.sp,
                    fontFamily = SGTypography.poppins,
                    letterSpacing = 0.em,
                ),
            )

            SGText(
                text = "평화",
                style = getSGNonScaleTextStyle(
                    color = SGColor.primaryA,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 20.sp,
                    fontFamily = SGTypography.nanumSquareNeo,
                    letterSpacing = 0.em,
                ),
            )
        }
    }
}

@DevicePreviews
@Composable
private fun RegistrationPostScreenTopBarPreview() {
    RegistrationPostScreenTopBar()
}

