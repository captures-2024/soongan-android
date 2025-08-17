package com.captures2024.soongan.presentation.feature.main.home.component.home

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.presentation.designsystem.icon.MyIconPack
import com.captures2024.soongan.presentation.designsystem.icon.myiconpack.Logo
import com.captures2024.soongan.presentation.designsystem.ui.component.HeightSpacer
import com.captures2024.soongan.presentation.designsystem.ui.component.text.SGText
import com.captures2024.soongan.presentation.designsystem.ui.component.text.getSGNonScaleTextStyle
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGColor
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGTheme
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGTypography
import com.captures2024.soongan.presentation.designsystem.ui.util.DevicePreviews
import com.captures2024.soongan.presentation.feature.main.home.R

@Composable
internal fun HomeEmptyComponent(
    isLoading: Boolean,
    modifier: Modifier = Modifier,
    onClickPostList: () -> Unit,
) {
    Column(
        modifier = modifier
            .padding(
                vertical = 40.dp,
                horizontal = 16.dp,
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(32.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
                .wrapContentHeight(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(modifier = Modifier.padding(20.dp)) {
                Box(
                    modifier = Modifier.wrapContentSize()
                        .offset(
                            x = (-20).dp,
                            y = (-10).dp,
                        ),
                ) {
                    Canvas(
                        modifier = Modifier
                            .size(40.dp)
                            .align(Alignment.Center),
                    ) {
                        drawCircle(
                            color = SGColor.Main.primary,
                            radius = size.width / 2,
                        )
                    }
                }

                SGText(
                    text = "-회차 종료-",
                    style = getSGNonScaleTextStyle(
                        color = SGColor.Grayscale.black100,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.ExtraBold,
                        lineHeight = 40.sp,
                        fontFamily = SGTypography.pretendard,
                    ),
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Image(
                imageVector = MyIconPack.Logo,
                contentDescription = stringResource(R.string.logo_description),
                modifier = Modifier
                    .width(33.dp)
                    .height(50.dp),
            )
        }

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            SGText(
                text = "회차가 끝나고\n잠시 쉬어가는 중이에요\n\n어떤 작품들이 나왔는지 보러가고 싶다면",
                style = SGTypography.p1().copy(
                    color = SGColor.Grayscale.black100,
                    textAlign = TextAlign.Center,
                ),
            )

            HeightSpacer(50.dp)

            SGText(
                text = "보러가기",
                style = SGTypography.p1().copy(
                    color = SGColor.Grayscale.black100,
                    textAlign = TextAlign.Center,
                    textDecoration = TextDecoration.Underline,
                ),
                modifier = Modifier.clickable(
                    enabled = isLoading.not(),
                    onClick = onClickPostList,
                ),
            )
        }
    }
}

@DevicePreviews
@Composable
private fun PreviewHomeEmptyComponent() {
    SGTheme {
        HomeEmptyComponent(
            isLoading = false,
            onClickPostList = {},
        )
    }
}
