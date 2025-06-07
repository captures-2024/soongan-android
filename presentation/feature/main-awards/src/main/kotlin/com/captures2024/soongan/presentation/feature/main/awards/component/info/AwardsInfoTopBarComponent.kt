package com.captures2024.soongan.presentation.feature.main.awards.component.info

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.captures2024.soongan.presentation.designsystem.icon.MyIconPack
import com.captures2024.soongan.presentation.designsystem.icon.myiconpack.IconNonFillLeftArrow
import com.captures2024.soongan.presentation.designsystem.ui.util.DevicePreviews

@Composable
internal fun AwardsInfoTopBarComponent(
    modifier: Modifier = Modifier,
    onClickIcon: () -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(12.dp)
            .padding(start = 3.dp),
        contentAlignment = Alignment.CenterStart,
    ) {
        IconButton(
            onClick = onClickIcon,
        ) {
            Icon(
                imageVector = MyIconPack.IconNonFillLeftArrow,
                contentDescription = "back",
                modifier = Modifier.size(
                    width = 20.dp,
                    height = 16.dp,
                ),
            )
        }
    }
}

@DevicePreviews
@Composable
private fun AwardsInfoTopBarComponent_Preview() {
    AwardsInfoTopBarComponent(
        onClickIcon = {},
    )
}
