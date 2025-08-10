package com.captures2024.soongan.presentation.feature.main.profile.component.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.presentation.designsystem.icon.MyIconPack
import com.captures2024.soongan.presentation.designsystem.icon.myiconpack.IconNonFillBackArrow
import com.captures2024.soongan.presentation.designsystem.ui.component.button.SGIconButton
import com.captures2024.soongan.presentation.designsystem.ui.component.button.SGTextButtonType2
import com.captures2024.soongan.presentation.designsystem.ui.component.text.SGText
import com.captures2024.soongan.presentation.designsystem.ui.component.text.getSGNonScaleTextStyle
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGColor
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGTheme
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGTypography
import com.captures2024.soongan.presentation.designsystem.ui.util.DevicePreviews

@Composable
internal fun CompleteExplainScreen(
    modifier: Modifier = Modifier,
    onClickBack: () -> Unit,
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = @Composable {
            Column {
                Box(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 16.dp),
                    contentAlignment = Alignment.CenterStart,
                ) {
                    SGIconButton(onClick = onClickBack) {
                        Icon(
                            imageVector = MyIconPack.IconNonFillBackArrow,
                            contentDescription = "back pressed",
                            modifier = Modifier.size(height = 20.dp, width = 16.dp),
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(24.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        SGText(
                            text = "확인",
                            style = getSGNonScaleTextStyle(
                                color = SGColor.primaryA,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                lineHeight = 20.sp,
                                fontFamily = SGTypography.pretendard,
                                letterSpacing = 0.em,
                            ),
                        )
                    }
                }

                HorizontalDivider(color = SGColor.buttonDisableGray)
            }
        },
        bottomBar = {
            Box(
                modifier = Modifier.padding(20.dp),
            ) {
                SGTextButtonType2(
                    text = "확인",
                    onClick = onClickBack,
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        },
        containerColor = SGColor.BG.background,
    ) { paddingValues ->
        Box(
            modifier = Modifier.fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center,
        ) {
            SGText(
                text = "소명절차가 완료됐습니다.\n운영진 검토 하에 추가 소명 요청이 있을 수 있습니다.\n\n추가 소명은 이메일로 진행합니다.",
                style = getSGNonScaleTextStyle(
                    color = SGColor.primaryA,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 24.sp,
                    fontFamily = SGTypography.pretendard,
                    letterSpacing = 0.em,
                ),
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 20.dp),
            )
        }
    }
}

@DevicePreviews
@Composable
private fun PreviewCompleteExplainScreen() {
    SGTheme {
        CompleteExplainScreen(
            onClickBack = {},
        )
    }
}
