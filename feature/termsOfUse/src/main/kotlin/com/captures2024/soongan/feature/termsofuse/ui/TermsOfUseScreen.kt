package com.captures2024.soongan.feature.termsofuse.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.core.designsystem.component.button.SGTextButtonType2
import com.captures2024.soongan.core.designsystem.component.text.SGText
import com.captures2024.soongan.core.designsystem.component.text.getSGNonScaleTextStyle
import com.captures2024.soongan.core.designsystem.theme.SGColor
import com.captures2024.soongan.core.designsystem.theme.SGTheme
import com.captures2024.soongan.core.designsystem.theme.SGTypography
import com.captures2024.soongan.core.designsystem.util.DevicePreviews
import com.captures2024.soongan.core.viewmodel.sign.TermsOfUseViewModel

@Composable
internal fun TermsOfUseScreen(
    intent: (TermsOfUseViewModel.Intent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize()
            .background(SGColor.white)
    ) {
        TermsOfUseTopBarScreen(onClickBack = { intent(TermsOfUseViewModel.Intent.OnClickBack) })

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                SGText(
                    text = "자동으로 이용 약관이 열립니다.",
                    style = getSGNonScaleTextStyle(
                        color = SGColor.primaryA,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        lineHeight = 14.sp,
                        fontFamily = SGTypography.nanumSquareNeo,
                        letterSpacing = 0.em,
                    )
                )

                SGTextButtonType2(
                    text = "수동으로 열기",
                    onClick = { intent(TermsOfUseViewModel.Intent.OnClickTerms) },
                )
            }
        }
    }
}

@DevicePreviews
@Composable
private fun PreviewTermsOfUseScreen() {
    SGTheme {
        TermsOfUseScreen(
            intent = {},
        )
    }
}
