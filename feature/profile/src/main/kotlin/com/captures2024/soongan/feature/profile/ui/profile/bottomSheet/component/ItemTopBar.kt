package com.captures2024.soongan.feature.profile.ui.profile.bottomSheet.component

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
import com.captures2024.soongan.core.designsystem.component.button.SGIconButton
import com.captures2024.soongan.core.designsystem.component.text.SGText
import com.captures2024.soongan.core.designsystem.component.text.getSGNonScaleTextStyle
import com.captures2024.soongan.core.designsystem.icon.MyIconPack
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonFillLeftArrow
import com.captures2024.soongan.core.designsystem.theme.SGColor
import com.captures2024.soongan.core.designsystem.theme.SGTypography
import com.captures2024.soongan.core.designsystem.util.DevicePreviews

@Composable
internal fun ItemTopBar(
    itemTitle: String,
    modifier: Modifier = Modifier,
    hasBackIcon: Boolean = false,
    onBackPressed: () -> Unit = {},
) {
    Column(modifier = modifier) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .minimumInteractiveComponentSize(),
            contentAlignment = Alignment.Center
        ) {
            if (hasBackIcon) {
                SGIconButton(
                    imageVector = MyIconPack.IconNonFillLeftArrow,
                    contentDescription = MyIconPack.IconNonFillLeftArrow.name,
                    onClick = onBackPressed,
                    modifier = Modifier.align(Alignment.CenterStart)
                )
            }
            SGText(
                text = itemTitle,
                style = getSGNonScaleTextStyle(
                    color = SGColor.primaryA,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 20.sp,
                    fontFamily = SGTypography.nanumSquareNeo,
                    letterSpacing = (-5).em,
                ),
            )
        }

        HorizontalDivider(color = SGColor.primaryA.copy(alpha = 0.12f))
    }
}

@DevicePreviews
@Composable
private fun ItemTopBarPreview() {
    ItemTopBar(itemTitle = "회원탈퇴", hasBackIcon = true)
}