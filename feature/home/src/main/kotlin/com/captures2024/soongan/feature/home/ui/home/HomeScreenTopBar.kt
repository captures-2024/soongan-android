package com.captures2024.soongan.feature.home.ui.home

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
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
import com.captures2024.soongan.core.designsystem.component.NonScaleText
import com.captures2024.soongan.core.designsystem.icon.MyIconPack
import com.captures2024.soongan.core.designsystem.icon.myiconpack.Logo
import com.captures2024.soongan.core.designsystem.theme.Accent
import com.captures2024.soongan.core.designsystem.theme.PrimaryA
import com.captures2024.soongan.core.designsystem.util.DevicePreviews
import com.captures2024.soongan.feature.home.R

@Composable
internal fun HomeScreenTopBar(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp)
    ) {
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
                    color = Accent,
                    radius = size.width / 2
                )
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            NonScaleText(
                text = stringResource(id = R.string.home_top_bar_topic_example),
                fontSize = 42.sp,
                fontWeight = FontWeight.ExtraBold,
                color = PrimaryA,
                modifier = Modifier.padding(start = 4.dp)
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
    HomeScreenTopBar()
}