package com.captures2024.soongan.feature.home.ui.post

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.core.designsystem.component.WidthSpacer
import com.captures2024.soongan.core.designsystem.component.text.SGText
import com.captures2024.soongan.core.designsystem.component.text.getSGNonScaleTextStyle
import com.captures2024.soongan.core.designsystem.icon.MyIconPack
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonFillEdit
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonFillPaperDelete
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonFillReport
import com.captures2024.soongan.core.designsystem.theme.SGColor
import com.captures2024.soongan.core.designsystem.theme.SGTypography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun HomePostMenuBottomSheetDialog(
    closeSheet: () -> Unit,
    onClickEdit: () -> Unit,
    onClickDelete: () -> Unit,
    onClickReport: () -> Unit,
    modifier: Modifier = Modifier,
    isMyPost: Boolean = false,
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    ModalBottomSheet(
        modifier = modifier
            .heightIn(min = 240.dp),
        onDismissRequest = closeSheet,
        sheetState = sheetState,
        containerColor = SGColor.white
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
                .padding(
                    horizontal = 20.dp,
                    vertical = 16.dp,
                ),
        ) {
            DialogItemComponent(
                text = "수정하기",
                color = when (isMyPost) {
                    true -> SGColor.primaryA

                    false -> SGColor.primaryA.copy(alpha = 0.3f)
                },
                icon = MyIconPack.IconNonFillEdit,
                isVisibleDivider = true,
                isEnabled = isMyPost,
                onClick = onClickEdit,
            )
            DialogItemComponent(
                text = "삭제하기",
                color = when (isMyPost) {
                    true -> SGColor.primaryA

                    false -> SGColor.primaryA.copy(alpha = 0.3f)
                },
                icon = MyIconPack.IconNonFillPaperDelete,
                isVisibleDivider = true,
                isEnabled = isMyPost,
                onClick = onClickDelete,
            )
            DialogItemComponent(
                text = "신고하기",
                color = when (isMyPost) {
                    true -> SGColor.primaryA.copy(alpha = 0.3f)

                    false -> Color(0xFFFC0000)
                },
                icon = MyIconPack.IconNonFillReport,
                isVisibleDivider = false,
                isEnabled = isMyPost.not(),
                onClick = onClickReport,
            )
        }
    }
}

@Composable
private fun DialogItemComponent(
    text: String,
    color: Color,
    icon: ImageVector,
    isVisibleDivider: Boolean,
    isEnabled: Boolean,
    onClick: () -> Unit,
) {
    Column(
        modifier = Modifier.clickable {
            if (isEnabled) {
                onClick()
            }
        }
    ) {
        Row(
            modifier = Modifier.heightIn(min = 56.dp)
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            SGText(
                text = text,
                style = getSGNonScaleTextStyle(
                    color = color,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 24.sp,
                    fontFamily = SGTypography.nanumSquareNeo,
                    letterSpacing = 0.em,
                ),
                modifier = Modifier.weight(0.9f),
            )

            WidthSpacer(4.dp)

            Icon(
                imageVector = icon,
                contentDescription = "back",
                tint = color,
                modifier = Modifier.size(24.dp)
            )
        }

        if (isVisibleDivider) {
            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(),
                color = SGColor.primaryA.copy(alpha = 0.3f),
            )
        }
    }
}


@Preview
@Composable
private fun HomePostMenuBottomSheetDialogPreview_other() {
    HomePostMenuBottomSheetDialog(
        closeSheet = {},
        onClickEdit = {},
        onClickDelete = {},
        onClickReport = {},
    )
}

@Preview
@Composable
private fun HomePostMenuBottomSheetDialogPreview_my() {
    HomePostMenuBottomSheetDialog(
        closeSheet = {},
        onClickEdit = {},
        onClickDelete = {},
        onClickReport = {},
        isMyPost = true,
    )
}