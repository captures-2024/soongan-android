package com.captures2024.soongan.feature.home.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.core.designsystem.icon.MyIconPack
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonFillInfo
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonFillRightArrow
import com.captures2024.soongan.core.designsystem.ui.component.text.SGText
import com.captures2024.soongan.core.designsystem.ui.component.text.getSGNonScaleTextStyle
import com.captures2024.soongan.core.designsystem.ui.theme.SGColor
import com.captures2024.soongan.core.designsystem.ui.theme.SGTypography
import com.captures2024.soongan.core.designsystem.ui.component.button.SGIconCircleButton
import com.captures2024.soongan.feature.home.R

@Composable
internal fun HomeScreenFooter(
    onClickInfo: () -> Unit,
    onClickRightArrow: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth()
            .padding(horizontal = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        IconBox(
            imageVector = MyIconPack.IconNonFillInfo,
            text = stringResource(id = R.string.contest_info),
            iconWidth = 24.dp,
            iconHeight = 24.dp,
            onClick = onClickInfo,
        )
        IconBox(
            imageVector = MyIconPack.IconNonFillRightArrow,
            text = stringResource(id = R.string.participated_photo),
            iconWidth = 20.dp,
            iconHeight = 16.dp,
            onClick = onClickRightArrow,
        )
    }
}

@Composable
private fun IconBox(
    imageVector: ImageVector,
    text: String,
    iconWidth: Dp,
    iconHeight: Dp,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        SGIconCircleButton(
            imageVector = imageVector,
            contentDescription = text,
            color = SGColor.primaryA,
            iconWidth = iconWidth,
            iconHeight = iconHeight,
            onClick = onClick,
        )

        SGText(
            text = text,
            style = getSGNonScaleTextStyle(
                color = SGColor.primaryA,
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                lineHeight = 16.sp,
                fontFamily = SGTypography.nanumSquareNeo,
                letterSpacing = 0.em,
            ),
        )
    }
}

@Preview
@Composable
private fun HomeScreenFooterPreview() {
    Surface {
        HomeScreenFooter(
            onClickInfo = {},
            onClickRightArrow = {},
        )
    }
}
