package com.captures2024.soongan.feature.profile.ui.edit

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.core.designsystem.component.NonScaleText
import com.captures2024.soongan.core.designsystem.theme.NanumSquareNeoFontFamily
import com.captures2024.soongan.core.designsystem.theme.SGColor
import com.captures2024.soongan.core.designsystem.util.DevicePreviews

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun EditProfileBottomSheet(
    modifier: Modifier = Modifier,
    sheetState: SheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
    closeSheet: () -> Unit = {},
    onClickDefaultProfileImage: () -> Unit = {},
    openPhotoPicker: () -> Unit = {},
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
            BottomSheetRow(
                itemText = "갤러리에서 사진 선택",
                onClick = openPhotoPicker
            )
            HorizontalDivider(color = SGColor.primaryA.copy(alpha = 0.3f))
            BottomSheetRow(
                itemText = "기본 프로필로 돌아가기",
                onClick = onClickDefaultProfileImage
            )
        }
    }
}

@Composable
private fun BottomSheetRow(
    itemText: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(horizontal = 16.dp)
            .clickable(
                onClick = onClick,
                interactionSource = interactionSource,
                indication = null
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        NonScaleText(
            text = itemText,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = NanumSquareNeoFontFamily,
            letterSpacing = (-0.5).sp,
            lineHeight = 20.sp
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@DevicePreviews
@Composable
private fun EditProfileBottomSheetPreview() {
    val sheetState = SheetState(
        skipPartiallyExpanded = true,
        initialValue = SheetValue.Expanded,
        density = LocalDensity.current,
        skipHiddenState = false
    )

    EditProfileBottomSheet(sheetState = sheetState)
}