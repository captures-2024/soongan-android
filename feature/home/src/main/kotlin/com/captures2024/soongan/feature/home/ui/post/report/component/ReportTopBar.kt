package com.captures2024.soongan.feature.home.ui.post.report.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.core.designsystem.ui.component.text.SGText
import com.captures2024.soongan.core.designsystem.ui.component.text.getSGNonScaleTextStyle
import com.captures2024.soongan.core.designsystem.icon.MyIconPack
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonFillLeftArrow
import com.captures2024.soongan.core.designsystem.ui.theme.NanumSquareNeoFontFamily
import com.captures2024.soongan.core.designsystem.ui.theme.SGColor
import com.captures2024.soongan.core.designsystem.ui.component.button.SGIconButton
import com.captures2024.soongan.core.designsystem.ui.util.DevicePreviews
import com.captures2024.soongan.feature.home.R

@Composable
internal fun ReportTopBar(
    modifier: Modifier = Modifier,
    hasBackIcon: Boolean = false,
    onBackPressed: () -> Unit = {},
) {
    Column(modifier = modifier) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .minimumInteractiveComponentSize(),
            contentAlignment = Alignment.Center,
        ) {
            if (hasBackIcon) {
                SGIconButton(
                    imageVector = MyIconPack.IconNonFillLeftArrow,
                    contentDescription = MyIconPack.IconNonFillLeftArrow.name,
                    modifier = Modifier.align(Alignment.CenterStart),
                    onClick = onBackPressed,
                )
            }
            SGText(
                text = stringResource(id = R.string.report_title_text),
                getSGNonScaleTextStyle(
                    color = SGColor.primaryA,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = NanumSquareNeoFontFamily,
                    letterSpacing = (-5).em,
                    lineHeight = 20.sp,
                ),
            )
        }
        HorizontalDivider(color = SGColor.primaryA.copy(alpha = 0.12f))
    }
}

@DevicePreviews
@Composable
private fun ItemTopBarPreview() {
    ReportTopBar(hasBackIcon = true)
}
