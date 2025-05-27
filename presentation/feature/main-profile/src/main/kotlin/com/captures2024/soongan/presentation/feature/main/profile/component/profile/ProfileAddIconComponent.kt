package com.captures2024.soongan.presentation.feature.main.profile.component.profile

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
import androidx.compose.ui.unit.dp
import com.captures2024.soongan.core.designsystem.icon.MyIconPack
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonFillPlus
import com.captures2024.soongan.core.designsystem.ui.theme.SGColor
import com.captures2024.soongan.core.designsystem.ui.theme.SGTheme
import com.captures2024.soongan.core.designsystem.ui.util.DevicePreviews

@Composable
internal fun ProfileAddIconComponent(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(44.dp)
            .clip(CircleShape)
            .background(color = SGColor.black)
            .border(width = 1.dp, color = SGColor.Grayscale.white, shape = CircleShape),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            imageVector = MyIconPack.IconNonFillPlus,
            contentDescription = MyIconPack.IconNonFillPlus.name,
            modifier = Modifier.size(22.dp),
            tint = SGColor.Grayscale.white,
        )
    }
}

@DevicePreviews
@Composable
private fun PreviewProfileAddIconComponent() {
    SGTheme {
        ProfileAddIconComponent()
    }
}
