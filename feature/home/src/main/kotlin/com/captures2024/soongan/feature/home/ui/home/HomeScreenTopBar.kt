package com.captures2024.soongan.feature.home.ui.home

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.core.designsystem.ui.component.text.SGText
import com.captures2024.soongan.core.designsystem.ui.component.text.getSGNonScaleTextStyle
import com.captures2024.soongan.core.designsystem.icon.MyIconPack
import com.captures2024.soongan.core.designsystem.icon.myiconpack.Logo
import com.captures2024.soongan.core.designsystem.ui.theme.SGColor
import com.captures2024.soongan.core.designsystem.ui.theme.SGTypography
import com.captures2024.soongan.core.designsystem.ui.util.DevicePreviews
import com.captures2024.soongan.feature.home.R

@Composable
internal fun HomeScreenTopBar(
    subject: String,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        Box(
            modifier = Modifier.offset(
                x = (-20).dp,
                y = (-10).dp
            )
        ) {
            Canvas(
                modifier = Modifier
                    .size(40.dp)
                    .align(Alignment.Center)
            ) {
                drawCircle(
                    color = SGColor.accent,
                    radius = size.width / 2,
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            SGText(
                text = subject,
                style = getSGNonScaleTextStyle(
                    color = SGColor.primaryA,
                    fontSize = 42.sp,
                    fontWeight = FontWeight.ExtraBold,
                    lineHeight = 40.sp,
                    fontFamily = SGTypography.nanumSquareNeo,
                )
            )

            Image(
                imageVector = MyIconPack.Logo,
                contentDescription = "logo",
                modifier = Modifier
                    .width(33.1f.dp)
                    .height(50.1f.dp)
            )
        }
    }
}

@DevicePreviews
@Composable
private fun HomeScreenTopBarPreview() {
    Box(
        modifier = Modifier
            .background(SGColor.white)
            .padding(30.dp)
    ) {
        HomeScreenTopBar(
            subject = stringResource(R.string.home_top_bar_topic_example),
        )
    }
}