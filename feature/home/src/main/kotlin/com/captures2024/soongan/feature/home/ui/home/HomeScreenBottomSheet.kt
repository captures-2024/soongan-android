package com.captures2024.soongan.feature.home.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.core.designsystem.component.NonScaleText
import com.captures2024.soongan.core.designsystem.util.DevicePreviews

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun HomeScreenBottomSheet(
    modifier: Modifier = Modifier,
    closeSheet: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = false)

    ModalBottomSheet(
        modifier = modifier,
        onDismissRequest = closeSheet,
        sheetState = sheetState,
        containerColor = Color.White,
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NonScaleText(
                "대회 정보",
                fontSize = 28.sp
            )
            HorizontalDivider(color = Color(0x4D252525))
            NonScaleText(
                "리워드",
                fontSize = 12.sp
            )
            NonScaleText(
                "선정 방식",
                fontSize = 12.sp
            )
            NonScaleText(
                "참여 횟수 제한",
                fontSize = 12.sp
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@DevicePreviews
@Composable
private fun HomeScreenBottomSheetPreview() {
    // preview 표시 안됨. 빌드 시 문제 없음.
    BottomSheetScaffold(
        sheetContent = { HomeScreenBottomSheet { } }
    ) {
        Box(modifier = Modifier
            .fillMaxSize()
            .background(Color.Black))
    }
}