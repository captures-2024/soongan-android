package com.captures2024.soongan.feature.home.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.core.designsystem.component.NonScaleText
import com.captures2024.soongan.core.designsystem.util.DevicePreviews
import com.captures2024.soongan.feature.home.state.PhotoDetailModalState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun PhotoDetailBottomSheetDialog(
    modifier: Modifier = Modifier,
    modalState: PhotoDetailModalState
) {
    val modalBottomSheetState = rememberModalBottomSheetState()

    when (modalState) {
        PhotoDetailModalState.Open -> {
            LaunchedEffect(key1 = true) {
                modalBottomSheetState.show()
            }
        }

        PhotoDetailModalState.Close -> {
            LaunchedEffect(key1 = true) {
                modalBottomSheetState.hide()
            }
        }
    }

    ModalBottomSheet(
        modifier = modifier
            .height(600.dp),
        onDismissRequest = { /* TODO */ },
        sheetState = modalBottomSheetState,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NonScaleText(
                text = "댓글",
                color = Color.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            HorizontalDivider(
                color = Color(0x4D252525),
                thickness = 1.dp
            )

        }
    }

}

@DevicePreviews
@Composable
private fun PhotoDetailBottomSheetDialogPreview() {
    PhotoDetailBottomSheetDialog(
        modalState = PhotoDetailModalState.Open
    )
}