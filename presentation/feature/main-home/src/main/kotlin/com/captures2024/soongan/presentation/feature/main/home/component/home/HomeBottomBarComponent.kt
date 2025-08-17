package com.captures2024.soongan.presentation.feature.main.home.component.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.presentation.designsystem.icon.MyIconPack
import com.captures2024.soongan.presentation.designsystem.icon.myiconpack.IconNonFillInfo
import com.captures2024.soongan.presentation.designsystem.icon.myiconpack.IconNonFillRightArrow
import com.captures2024.soongan.presentation.designsystem.ui.component.button.SGIconCircleButton
import com.captures2024.soongan.presentation.designsystem.ui.component.text.SGText
import com.captures2024.soongan.presentation.designsystem.ui.component.text.getSGNonScaleTextStyle
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGColor
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGTheme
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGTypography
import com.captures2024.soongan.presentation.designsystem.ui.util.DevicePreviews
import com.captures2024.soongan.presentation.feature.main.home.R

@Composable
internal fun HomeBottomBarComponent(
    isLoading: Boolean,
    modifier: Modifier = Modifier,
    onClickContestInfo: () -> Unit,
    onClickPostList: () -> Unit,
) {
    Row(
        modifier = modifier.fillMaxWidth()
            .padding(horizontal = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        IconComponent(
            imageVector = MyIconPack.IconNonFillInfo,
            content = stringResource(R.string.contest_info_title),
            description = stringResource(R.string.contest_info_description),
            iconWidth = 24.dp,
            iconHeight = 24.dp,
            enabled = isLoading.not(),
            onClick = onClickContestInfo,
        )

        IconComponent(
            imageVector = MyIconPack.IconNonFillRightArrow,
            content = stringResource(R.string.post_list_title),
            description = stringResource(R.string.post_list_description),
            iconWidth = 20.dp,
            iconHeight = 16.dp,
            enabled = isLoading.not(),
            onClick = onClickPostList,
        )
    }
}

@Composable
private fun IconComponent(
    imageVector: ImageVector,
    content: String,
    description: String,
    iconWidth: Dp,
    iconHeight: Dp,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        SGIconCircleButton(
            imageVector = imageVector,
            contentDescription = description,
            color = SGColor.Grayscale.black100,
            iconWidth = iconWidth,
            iconHeight = iconHeight,
            enabled = enabled,
            onClick = onClick,
        )

        SGText(
            text = content,
            style = getSGNonScaleTextStyle(
                color = SGColor.Grayscale.black100,
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                lineHeight = 16.sp,
                fontFamily = SGTypography.pretendard,
                letterSpacing = 0.em,
            ),
        )
    }
}

@DevicePreviews
@Composable
private fun PreviewHomeBottomBarComponent() {
    SGTheme {
        HomeBottomBarComponent(
            isLoading = false,
            onClickContestInfo = {},
            onClickPostList = {},
        )
    }
}
