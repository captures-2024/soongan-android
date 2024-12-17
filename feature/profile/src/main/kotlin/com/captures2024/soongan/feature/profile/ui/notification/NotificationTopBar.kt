package com.captures2024.soongan.feature.profile.ui.notification

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.core.designsystem.component.NonScaleText
import com.captures2024.soongan.core.designsystem.icon.MyIconPack
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonFillLeftArrow
import com.captures2024.soongan.core.designsystem.theme.NanumSquareNeoFontFamily
import com.captures2024.soongan.core.designsystem.util.DevicePreviews

@Composable
internal fun NotificationTopBar(
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 16.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        IconButton(
            onClick = onBackPressed,
            modifier = Modifier.size(height = 20.dp, width = 16.dp)
        ) {
            Icon(
                MyIconPack.IconNonFillLeftArrow,
                contentDescription = "back pressed"
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(24.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            NonScaleText(
                text = "알림",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = NanumSquareNeoFontFamily,
                lineHeight = 24.sp
            )
        }
    }
}

@DevicePreviews
@Composable
private fun NotificationTopBarPreview() {
    NotificationTopBar { }
}