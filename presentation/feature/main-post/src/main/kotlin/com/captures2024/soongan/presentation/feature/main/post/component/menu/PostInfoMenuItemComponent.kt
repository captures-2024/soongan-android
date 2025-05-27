package com.captures2024.soongan.presentation.feature.main.post.component.menu

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.core.designsystem.icon.MyIconPack
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonFillEdit
import com.captures2024.soongan.core.designsystem.ui.component.WidthSpacer
import com.captures2024.soongan.core.designsystem.ui.component.text.SGText
import com.captures2024.soongan.core.designsystem.ui.component.text.getSGNonScaleTextStyle
import com.captures2024.soongan.core.designsystem.ui.theme.SGColor
import com.captures2024.soongan.core.designsystem.ui.theme.SGTheme
import com.captures2024.soongan.core.designsystem.ui.theme.SGTypography
import com.captures2024.soongan.core.designsystem.ui.util.DevicePreviews
import com.captures2024.soongan.presentation.feature.main.post.R

@Composable
internal fun PostInfoMenuItemComponent(
    text: String,
    color: Color,
    icon: ImageVector,
    isVisibleDivider: Boolean,
    isEnabled: Boolean,
    onClick: () -> Unit,
) {
    Column(
        modifier = Modifier.let {
            when (isEnabled) {
                true -> it.clickable(onClick = onClick)
                false -> it
            }
        },
    ) {
        Row(
            modifier = Modifier
                .heightIn(min = 56.dp)
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            SGText(
                text = text,
                style = getSGNonScaleTextStyle(
                    color = color,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 24.sp,
                    fontFamily = SGTypography.pretendard,
                    letterSpacing = 0.em,
                ),
                modifier = Modifier.weight(1f),
            )

            WidthSpacer(4.dp)

            Icon(
                imageVector = icon,
                contentDescription = stringResource(R.string.back_description),
                tint = color,
                modifier = Modifier.size(24.dp),
            )
        }

        if (isVisibleDivider) {
            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(),
                color = SGColor.Grayscale.black100.copy(alpha = 0.3f),
            )
        }
    }
}

@DevicePreviews
@Composable
private fun PreviewPostInfoMenuItemComponent() {
    SGTheme {
        PostInfoMenuItemComponent(
            text = "수정하기",
            color = SGColor.Grayscale.black100,
            icon = MyIconPack.IconNonFillEdit,
            isVisibleDivider = true,
            isEnabled = true,
            onClick = {},
        )
    }
}
