package com.captures2024.soongan.feature.profile.ui.edit.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.captures2024.soongan.core.designsystem.icon.MyIconPack
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonFillPlus
import com.captures2024.soongan.core.designsystem.ui.util.DevicePreviews

@Composable
internal fun MiniAddIcon(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(44.dp)
            .clip(CircleShape)
            .background(color = Color.Black)
            .border(width = 1.dp, color = Color.White, shape = CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = MyIconPack.IconNonFillPlus,
            contentDescription = MyIconPack.IconNonFillPlus.name,
            modifier = Modifier.size(22.dp),
            tint = Color.White
        )
    }
}

@DevicePreviews
@Composable
private fun MiniAddIconPreview() {
    MiniAddIcon()
}