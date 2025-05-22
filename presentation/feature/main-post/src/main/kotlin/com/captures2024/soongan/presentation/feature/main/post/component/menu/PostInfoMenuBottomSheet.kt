package com.captures2024.soongan.presentation.feature.main.post.component.menu

import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.captures2024.soongan.core.designsystem.ui.theme.SGColor
import com.captures2024.soongan.presentation.feature.main.post.component.screen.PostInfoMenuScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun PostInfoMenuBottomSheet(
    isMyPost: Boolean,
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
    onClickDelete: () -> Unit,
    onClickEditPost: () -> Unit,
    onClickReport: () -> Unit,
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    ModalBottomSheet(
        modifier = modifier
            .heightIn(min = 240.dp),
        onDismissRequest = onDismissRequest,
        sheetState = sheetState,
        containerColor = SGColor.Grayscale.white,
    ) {
        PostInfoMenuScreen(
            isMyPost = isMyPost,
            onClickEdit = {
                onDismissRequest()
                onClickDelete()
            },
            onClickDelete = {
                onDismissRequest()
                onClickEditPost()
            },
            onClickReport = {
                onDismissRequest()
                onClickReport()
            },
        )
    }
}
