package com.captures2024.soongan.feature.profile.ui.notification.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.captures2024.soongan.core.designsystem.icon.MyIconPack
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonFillDelete
import com.captures2024.soongan.core.designsystem.ui.theme.SGColor

// swipe 후 보여지는 삭제 ActionBox
@Composable
internal fun DeleteActionBox(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Box(
        modifier
            .fillMaxHeight()
            .background(SGColor.negative)
            .padding(horizontal = 12.dp)
            .clickable(onClick = onClick),
    ) {
        Icon(
            imageVector = MyIconPack.IconNonFillDelete,
            contentDescription = "notification delete icon",
            modifier = Modifier.align(Alignment.Center),
            tint = SGColor.white,
        )
    }
}
