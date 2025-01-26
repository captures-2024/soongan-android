package com.captures2024.soongan.feature.profile.ui.profile.bottomSheet.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.core.designsystem.component.NonScaleText
import com.captures2024.soongan.core.designsystem.icon.MyIconPack
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonFillLeftArrow
import com.captures2024.soongan.core.designsystem.theme.NanumSquareNeoFontFamily
import com.captures2024.soongan.core.designsystem.theme.SGColor

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
                IconButton(
                    onClick = onBackPressed,
                    modifier = Modifier.align(Alignment.CenterStart)
                ) {
                    Icon(
                        imageVector = MyIconPack.IconNonFillLeftArrow,
                        contentDescription = MyIconPack.IconNonFillLeftArrow.name
                    )
                }
            }
            NonScaleText(
                text = itemTitle,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = NanumSquareNeoFontFamily,
                letterSpacing = (-5).em,
                lineHeight = 20.sp
            )
        }
        HorizontalDivider(color = SGColor.primaryA.copy(alpha = 0.12f))
    }
}