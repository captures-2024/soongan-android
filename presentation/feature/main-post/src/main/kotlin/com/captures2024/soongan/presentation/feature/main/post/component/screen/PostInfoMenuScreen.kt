package com.captures2024.soongan.presentation.feature.main.post.component.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.captures2024.soongan.presentation.designsystem.icon.MyIconPack
import com.captures2024.soongan.presentation.designsystem.icon.myiconpack.IconNonFillEdit
import com.captures2024.soongan.presentation.designsystem.icon.myiconpack.IconNonFillPaperDelete
import com.captures2024.soongan.presentation.designsystem.icon.myiconpack.IconNonFillReport
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGColor
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGTheme
import com.captures2024.soongan.presentation.designsystem.ui.util.DevicePreviews
import com.captures2024.soongan.presentation.feature.main.post.R
import com.captures2024.soongan.presentation.feature.main.post.component.menu.PostInfoMenuItemComponent

@Composable
internal fun PostInfoMenuScreen(
    isMyPost: Boolean,
    isTop7: Boolean,
    onClickEdit: () -> Unit,
    onClickDelete: () -> Unit,
    onClickReport: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxWidth()
            .padding(
                horizontal = 20.dp,
                vertical = 16.dp,
            ),
    ) {
        PostInfoMenuItemComponent(
            text = stringResource(R.string.post_info_menu_edit_title),
            color = when (isTop7) {
                true -> SGColor.Grayscale.black100.copy(alpha = 0.3f)
                false -> when (isMyPost) {
                    true -> SGColor.Grayscale.black100
                    false -> SGColor.Grayscale.black100.copy(alpha = 0.3f)
                }
            },
            icon = MyIconPack.IconNonFillEdit,
            isVisibleDivider = true,
            isEnabled = isTop7.not() && isMyPost,
            onClick = onClickEdit,
        )
        PostInfoMenuItemComponent(
            text = stringResource(R.string.post_info_menu_delete_title),
            color = when (isTop7) {
                true -> SGColor.Grayscale.black100.copy(alpha = 0.3f)
                false -> when (isMyPost) {
                    true -> SGColor.Grayscale.black100
                    false -> SGColor.Grayscale.black100.copy(alpha = 0.3f)
                }
            },
            icon = MyIconPack.IconNonFillPaperDelete,
            isVisibleDivider = true,
            isEnabled = isTop7.not() && isMyPost,
            onClick = onClickDelete,
        )
        PostInfoMenuItemComponent(
            text = stringResource(R.string.post_info_menu_report_title),
            color = when (isMyPost) {
                true -> SGColor.Grayscale.black100.copy(alpha = 0.3f)
                false -> SGColor.negative
            },
            icon = MyIconPack.IconNonFillReport,
            isVisibleDivider = false,
            isEnabled = !isMyPost,
            onClick = onClickReport,
        )
    }
}

@DevicePreviews
@Composable
private fun PreviewPostInfoMenuScreen_Default() {
    SGTheme {
        PostInfoMenuScreen(
            isMyPost = false,
            isTop7 = false,
            onClickEdit = {},
            onClickDelete = {},
            onClickReport = {},
        )
    }
}

@DevicePreviews
@Composable
private fun PreviewPostInfoMenuScreen_My() {
    SGTheme {
        PostInfoMenuScreen(
            isMyPost = true,
            isTop7 = false,
            onClickEdit = {},
            onClickDelete = {},
            onClickReport = {},
        )
    }
}
