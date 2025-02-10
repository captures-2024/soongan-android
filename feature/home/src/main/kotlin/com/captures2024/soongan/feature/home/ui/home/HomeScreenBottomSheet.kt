package com.captures2024.soongan.feature.home.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.core.designsystem.component.HeightSpacer
import com.captures2024.soongan.core.designsystem.component.button.SGButtonType2
import com.captures2024.soongan.core.designsystem.component.button.SGTextButtonType2
import com.captures2024.soongan.core.designsystem.component.text.SGText
import com.captures2024.soongan.core.designsystem.component.text.getSGNonScaleTextStyle
import com.captures2024.soongan.core.designsystem.theme.SGColor
import com.captures2024.soongan.core.designsystem.theme.SGTypography
import com.captures2024.soongan.core.designsystem.util.DevicePreviews

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun HomeScreenBottomSheet(
    modifier: Modifier = Modifier,
    closeSheet: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    val titleStyle = getSGNonScaleTextStyle(
        color = SGColor.primaryA,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        lineHeight = 24.sp,
        fontFamily = SGTypography.nanumSquareNeo,
        letterSpacing = 0.em,
    )

    val contentStyle = getSGNonScaleTextStyle(
        color = SGColor.primaryA,
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 24.sp,
        fontFamily = SGTypography.nanumSquareNeo,
        letterSpacing = 0.em,
    )

    ModalBottomSheet(
        modifier = modifier,
        onDismissRequest = closeSheet,
        sheetState = sheetState,
        containerColor = SGColor.white,
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
                .wrapContentHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            SGText(
                text = "대회 정보",
                style = getSGNonScaleTextStyle(
                    color = SGColor.primaryA,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 20.sp,
                    fontFamily = SGTypography.nanumSquareNeo,
                    letterSpacing = (-5).em,
                ),
            )

            HeightSpacer(12.dp)

            HorizontalDivider(color = SGColor.primaryA.copy(alpha = 0.3f))

            Column(
                modifier = Modifier.fillMaxWidth()
                    .wrapContentHeight()
                    .padding(
                        top = 40.dp,
                        bottom = 28.dp,
                    )
                    .padding(horizontal = 20.dp),
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth()
                        .wrapContentHeight()
                        .padding(horizontal = 18.dp),
                ) {
                    SGText(
                        text = "규칙",
                        style = titleStyle,
                    )

                    HeightSpacer(4.dp)

                    SGText(
                        text = "작품 총 3개 출품 가능",
                        style = contentStyle,
                        modifier = Modifier.padding(start = 16.dp)
                    )

                    HeightSpacer(10.dp)

                    SGText(
                        text = "선정 방식",
                        style = titleStyle,
                    )

                    HeightSpacer(4.dp)

                    SGText(
                        text = "투표: 좋아요 개수로 TOP 7 선정",
                        style = contentStyle,
                        modifier = Modifier.padding(start = 16.dp)
                    )

                    HeightSpacer(4.dp)

                    SGText(
                        text = "기간 : 1달",
                        style = contentStyle,
                        modifier = Modifier.padding(start = 16.dp)
                    )

                    HeightSpacer(4.dp)

                    SGText(
                        text = "매달 1일 오전 9시 ~ 다음 달 1일 00시",
                        style = contentStyle,
                        modifier = Modifier.padding(start = 16.dp)
                    )

                    HeightSpacer(8.dp)

                    SGText(
                        text = "동점자 처리 방식",
                        style = titleStyle,
                    )

                    HeightSpacer(4.dp)

                    SGText(
                        text = "1. 직전 회차 참가자",
                        style = contentStyle,
                        modifier = Modifier.padding(start = 16.dp)
                    )

                    HeightSpacer(4.dp)

                    SGText(
                        text = "2. 업로드 시간을 비교해 더 일찍 참가한 작품",
                        style = contentStyle,
                        modifier = Modifier.padding(start = 16.dp)
                    )

                    HeightSpacer(8.dp)

                    SGText(
                        text = "리워드",
                        style = titleStyle,
                    )

                    HeightSpacer(4.dp)

                    SGText(
                        text = "최종 1위한 작품은\n앱 접속 시 나오는 화면에 배경 사진으로 사용",
                        style = contentStyle,
                        modifier = Modifier.padding(start = 16.dp)
                    )

                    HeightSpacer(4.dp)

                    SGText(
                        text = "(차기 콘테스트 종료 시까지)",
                        style = contentStyle,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                }

                HeightSpacer(44.dp)

                SGTextButtonType2(
                    text = "확인",
                    modifier = Modifier.fillMaxWidth(),
                    onClick = closeSheet,
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@DevicePreviews
@Composable
private fun HomeScreenBottomSheetPreview() {
    BottomSheetScaffold(
        sheetContent = @Composable {
            HomeScreenBottomSheet(
                closeSheet = {},
            )
        }
    ) {
        Box(
            modifier = Modifier
            .fillMaxSize()
            .background(SGColor.black),
        )
    }
}