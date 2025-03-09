package com.captures2024.soongan.feature.home.ui.post.report

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.core.designsystem.ui.component.HeightSpacer
import com.captures2024.soongan.core.designsystem.ui.component.button.SGTextButtonType2
import com.captures2024.soongan.core.designsystem.ui.component.text.getSGNonScaleTextStyle
import com.captures2024.soongan.core.designsystem.ui.component.text.SGText
import com.captures2024.soongan.core.designsystem.ui.theme.NanumSquareNeoFontFamily
import com.captures2024.soongan.core.designsystem.ui.theme.SGColor
import com.captures2024.soongan.core.designsystem.ui.util.DevicePreviews
import com.captures2024.soongan.feature.home.R

@Composable
internal fun ReportErrorScreen(
    modifier: Modifier = Modifier,
    onClickButton: () -> Unit = {},
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 20.dp)
            .padding(top = 40.dp, bottom = 28.dp),
    ) {
        SGText(
            text = stringResource(R.string.report_error_text),
            style = getSGNonScaleTextStyle(
                color = SGColor.primaryA,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                lineHeight = 24.sp,
                fontFamily = NanumSquareNeoFontFamily,
                letterSpacing = (-5).em,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
        )

        HeightSpacer(40.dp)

        SGTextButtonType2(
            text = stringResource(id = R.string.report_submit_text),
            modifier = Modifier.fillMaxWidth(),
            onClick = onClickButton,
        )
    }
}

@DevicePreviews
@Composable
private fun ReportErrorScreenPreview() {
    ReportErrorScreen()
}
