package com.captures2024.soongan.presentation.feature.main.profile.component.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.presentation.designsystem.ui.component.text.SGText
import com.captures2024.soongan.presentation.designsystem.ui.component.text.getSGNonScaleTextStyle
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGColor
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGTheme
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGTypography
import com.captures2024.soongan.presentation.designsystem.ui.util.DevicePreviews
import com.captures2024.soongan.presentation.feature.main.profile.utils.extension.getColor
import com.captures2024.soongan.presentation.feature.main.profile.utils.extension.getIcon
import com.captures2024.soongan.presentation.feature.main.profile.utils.extension.getStringResId
import com.captures2024.soongan.presentation.viewmodel.model.ProfileBottomSheetMenuItem

@Composable
internal fun ProfileMenuDefaultScreen(
    modifier: Modifier = Modifier,
    onClickMenuItem: (ProfileBottomSheetMenuItem) -> Unit,
) {
    Column(modifier = modifier) {
        ProfileBottomSheetMenuItem.entries.forEachIndexed { idx, item ->
            ProfileMenuRow(
                item = item,
                onClick = onClickMenuItem,
                modifier = Modifier.padding(horizontal = 24.dp),
            )

            if (idx != ProfileBottomSheetMenuItem.entries.lastIndex) {
                HorizontalDivider(
                    modifier = Modifier.padding(horizontal = 24.dp),
                    color = SGColor.Grayscale.black100.copy(alpha = 0.3f),
                )
            }
        }
    }
}

@Composable
private fun ProfileMenuRow(
    item: ProfileBottomSheetMenuItem,
    modifier: Modifier = Modifier,
    onClick: (ProfileBottomSheetMenuItem) -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(horizontal = 18.dp)
            .clickable(
                onClick = { onClick(item) },
                interactionSource = interactionSource,
                indication = null,
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        SGText(
            text = stringResource(item.getStringResId()),
            style = getSGNonScaleTextStyle(
                color = item.getColor(),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 20.sp,
                fontFamily = SGTypography.pretendard,
                letterSpacing = (-5).em,
            ),
        )

        Box(
            modifier = Modifier.size(24.dp),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                imageVector = item.getIcon(),
                contentDescription = stringResource(item.getStringResId()),
                tint = item.getColor(),
            )
        }
    }
}

@DevicePreviews
@Composable
private fun PreviewProfileMenuDefaultScreen() {
    SGTheme {
        ProfileMenuDefaultScreen(
            onClickMenuItem = {},
        )
    }
}
