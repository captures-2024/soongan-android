package com.captures2024.soongan.feature.home.ui.component

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
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.core.designsystem.component.NonScaleText
import com.captures2024.soongan.core.designsystem.icon.MyIconPack
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonFillPlus
import com.captures2024.soongan.core.designsystem.theme.PrimaryA
import com.captures2024.soongan.core.designsystem.theme.PrimaryC
import com.captures2024.soongan.core.designsystem.theme.dropShadow
import com.captures2024.soongan.core.designsystem.util.DevicePreviews
import com.captures2024.soongan.feature.home.R

private const val MAX_EXHIBIT_CNT = 3

@Composable
internal fun HomeExhibitButton(
    modifier: Modifier = Modifier,
    width: Int,
    exhibitCount: Int
) {
    Box(
        modifier = modifier
            .width(width.dp)
            .height(257.dp)
            .dropShadow(shape = RectangleShape),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = when (exhibitCount) {
                        MAX_EXHIBIT_CNT -> PrimaryC
                        else -> Color.White
                    }
                ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = MyIconPack.IconNonFillPlus,
                contentDescription = "exhibit",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .clickable { }
                    .size(
                        when (exhibitCount) {
                            0 -> 40.dp
                            else -> 28.dp
                        }
                    ),
                tint = when (exhibitCount) {
                    MAX_EXHIBIT_CNT -> Color.White
                    else -> Color.Black
                }

            )

            if (exhibitCount == 0) {
                Spacer(modifier = Modifier.height(8.dp))
                NonScaleText(
                    text = stringResource(id = R.string.exhibit),
                    style = TextStyle(fontSize = 14.sp, color = PrimaryA)
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
                        MAX_EXHIBIT_CNT -> Color.White
                        else -> PrimaryA
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
        modifier = Modifier.background(Color.White)
            .padding(40.dp)
    ) {
        HomeExhibitButton(
            width = 90,
            exhibitCount = 0
        )
        Spacer(modifier = Modifier.height(30.dp))
        HomeExhibitButton(
            width = 90,
            exhibitCount = 3
        )
    }
}