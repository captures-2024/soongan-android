package com.captures2024.soongan.presentation.feature.main.profile.component.menu

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.core.designsystem.icon.MyIconPack
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonFillLeftArrow
import com.captures2024.soongan.core.designsystem.ui.component.button.SGIconButton
import com.captures2024.soongan.core.designsystem.ui.component.text.SGText
import com.captures2024.soongan.core.designsystem.ui.component.text.getSGNonScaleTextStyle
import com.captures2024.soongan.core.designsystem.ui.theme.SGColor
import com.captures2024.soongan.core.designsystem.ui.theme.SGTheme
import com.captures2024.soongan.core.designsystem.ui.theme.SGTypography
import com.captures2024.soongan.core.designsystem.ui.util.DevicePreviews

@Composable
internal fun ProfileMenuItemTopBarComponent(
    title: String,
    modifier: Modifier = Modifier,
    hasBackIcon: Boolean = false,
    onClickBack: (() -> Unit)? = null,
) {
    Column(modifier = modifier) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .minimumInteractiveComponentSize(),
            contentAlignment = Alignment.Center,
        ) {
            if (hasBackIcon && onClickBack != null) {
                SGIconButton(
                    imageVector = MyIconPack.IconNonFillLeftArrow,
                    contentDescription = MyIconPack.IconNonFillLeftArrow.name,
                    onClick = onClickBack,
                    modifier = Modifier.align(Alignment.CenterStart),
                )
            }
            SGText(
                text = title,
                style = getSGNonScaleTextStyle(
                    color = SGColor.Grayscale.black100,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 20.sp,
                    fontFamily = SGTypography.pretendard,
                    letterSpacing = (-5).em,
                ),
            )
        }

        HorizontalDivider(color = SGColor.Grayscale.black100.copy(alpha = 0.12f))
    }
}

@DevicePreviews
@Composable
private fun PreviewProfileMenuItemTopBarComponent() {
    SGTheme {
        ProfileMenuItemTopBarComponent(
            title = "test",
            hasBackIcon = true,
            onClickBack = {},
        )
    }
}
