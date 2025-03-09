package com.captures2024.soongan.feature.privacypolicy.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.core.designsystem.icon.MyIconPack
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonFillLeftArrow
import com.captures2024.soongan.core.designsystem.ui.component.WidthSpacer
import com.captures2024.soongan.core.designsystem.ui.theme.SGColor
import com.captures2024.soongan.core.designsystem.ui.theme.SGTypography
import com.captures2024.soongan.core.designsystem.ui.component.button.SGIconCircleButton
import com.captures2024.soongan.core.designsystem.ui.component.text.SGText
import com.captures2024.soongan.core.designsystem.ui.component.text.getSGNonScaleTextStyle
import com.captures2024.soongan.core.designsystem.ui.util.DevicePreviews

@Composable
internal fun PrivacyPolicyTopBarScreen(
    modifier: Modifier = Modifier,
    onClickBack: () -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(SGColor.white),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        WidthSpacer(8.dp)

        SGIconCircleButton(
            imageVector = MyIconPack.IconNonFillLeftArrow,
            contentDescription = "back",
            iconWidth = 20.dp,
            iconHeight = 16.dp,
            onClick = onClickBack,
        )

        WidthSpacer(32.dp)

        SGText(
            text = "개인정보 보호정책",
            style = getSGNonScaleTextStyle(
                color = SGColor.primaryA,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 18.sp,
                fontFamily = SGTypography.nanumSquareNeo,
                letterSpacing = 0.em,
            )
        )
    }
}

@DevicePreviews
@Composable
private fun PrivacyPolicyTopBarScreenPreview() {
    PrivacyPolicyTopBarScreen(onClickBack = {})
}