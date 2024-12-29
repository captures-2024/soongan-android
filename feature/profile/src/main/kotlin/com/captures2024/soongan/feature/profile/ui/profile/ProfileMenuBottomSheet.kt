package com.captures2024.soongan.feature.profile.ui.profile

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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.core.designsystem.component.NonScaleText
import com.captures2024.soongan.core.designsystem.theme.NanumSquareNeoFontFamily
import com.captures2024.soongan.core.designsystem.theme.SGColor
import com.captures2024.soongan.core.designsystem.util.DevicePreviews
import com.captures2024.soongan.feature.profile.navigation.ProfileMenuItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ProfileMenuBottomSheet(
    modifier: Modifier = Modifier,
    sheetState: SheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
    closeSheet: () -> Unit = {},
    onClickMenuItem: (ProfileMenuItem) -> Unit = {},
) {
    ModalBottomSheet(
        onDismissRequest = closeSheet,
        modifier = modifier.fillMaxWidth(),
        sheetState = sheetState,
        containerColor = Color.White
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 24.dp)
        ) {
            ProfileMenuItem.entries.forEachIndexed { idx, item ->
                ProfileMenuRow(
                    item = item,
                    onClick = onClickMenuItem
                )
                if (idx != ProfileMenuItem.entries.lastIndex) {
                    HorizontalDivider(color = SGColor.primaryA.copy(alpha = 0.3f))
                }
            }
        }
    }
}

@Composable
private fun ProfileMenuRow(
    modifier: Modifier = Modifier,
    item: ProfileMenuItem,
    onClick: (ProfileMenuItem) -> Unit = {},
) {
    val interactionSource = remember { MutableInteractionSource() }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(horizontal = 16.dp)
            .clickable(
                onClick = { onClick(item) },
                interactionSource = interactionSource,
                indication = null
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        NonScaleText(
            text = stringResource(item.titleRes),
            fontSize = 16.sp,
            color = item.color,
            fontWeight = FontWeight.Bold,
            fontFamily = NanumSquareNeoFontFamily,
            letterSpacing = 0.sp,
            lineHeight = 24.sp
        )
        Box(
            modifier = Modifier.size(24.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = item.icon,
                contentDescription = stringResource(item.titleRes),
                tint = item.color
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@DevicePreviews
@Composable
private fun ProfileMenuBottomSheetPreview() {
    val sheetState = SheetState(
        skipPartiallyExpanded = true,
        initialValue = SheetValue.Expanded,
        density = LocalDensity.current,
        skipHiddenState = false
    )

    ProfileMenuBottomSheet(sheetState = sheetState)
}