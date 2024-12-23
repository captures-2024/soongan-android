package com.captures2024.soongan.feature.home.ui.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.core.designsystem.component.NonScaleText
import com.captures2024.soongan.core.designsystem.icon.MyIconPack
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonFillPlus
import com.captures2024.soongan.core.designsystem.theme.PrimaryA
import com.captures2024.soongan.core.designsystem.theme.SGColor
import com.captures2024.soongan.core.designsystem.theme.dropShadow
import com.captures2024.soongan.core.designsystem.util.DevicePreviews
import com.captures2024.soongan.feature.home.R

private const val MAX_EXHIBIT_CNT = 3

@Composable
internal fun HomeExhibitButton(
    onClick: () -> Unit,
    exhibitCount: Int,
    modifier: Modifier = Modifier,
) {
    val widthValue = when (exhibitCount) {
        0 -> 257
        else -> 60
    }

    Box(
        modifier = modifier
            .clickable {
                if (exhibitCount < MAX_EXHIBIT_CNT) {
                    onClick()
                }
            }
            .width(widthValue.dp)
            .height(257.dp)
            .dropShadow(shape = RectangleShape),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = when {
                        exhibitCount < MAX_EXHIBIT_CNT -> SGColor.white
                        else -> SGColor.tempPrimaryC
                    },
                ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = MyIconPack.IconNonFillPlus,
                contentDescription = "exhibit",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .size(
                        when (exhibitCount) {
                            0 -> 40.dp
                            else -> 28.dp
                        }
                    ),
                tint = when {
                    exhibitCount< MAX_EXHIBIT_CNT -> SGColor.black
                    else -> SGColor.white
                },
            )
            if (exhibitCount == 0) {
                Spacer(modifier = Modifier.height(8.dp))
                NonScaleText(
                    text = stringResource(id = R.string.exhibit),
                    fontSize = 14.sp,
                    color = PrimaryA,
                )
            }
        }
        if (exhibitCount > 0) {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                NonScaleText(
                    text = "$exhibitCount/$MAX_EXHIBIT_CNT",
                    color = when (exhibitCount) {
                        MAX_EXHIBIT_CNT -> SGColor.white
                        else -> SGColor.primaryA
                    },
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 24.dp),
                )
            }
        }
    }
}

@DevicePreviews
@Composable
private fun HomeExhibitButtonPreview() {
    Column(
        modifier = Modifier.background(SGColor.white)
            .padding(40.dp)
    ) {
        HomeExhibitButton(
            onClick = {},
            exhibitCount = 0
        )
        Spacer(modifier = Modifier.height(30.dp))
        HomeExhibitButton(
            onClick = {},
            exhibitCount = 1
        )
        Spacer(modifier = Modifier.height(30.dp))
        HomeExhibitButton(
            onClick = {},
            exhibitCount = 3
        )
    }
}