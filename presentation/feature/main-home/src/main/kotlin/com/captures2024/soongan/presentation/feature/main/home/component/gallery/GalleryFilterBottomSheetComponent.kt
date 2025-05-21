package com.captures2024.soongan.presentation.feature.main.home.component.gallery

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.core.designsystem.ui.component.text.SGText
import com.captures2024.soongan.core.designsystem.ui.component.text.getSGNonScaleTextStyle
import com.captures2024.soongan.core.designsystem.ui.theme.SGColor
import com.captures2024.soongan.core.designsystem.ui.theme.SGTypography
import com.captures2024.soongan.presentation.feature.main.home.utils.extension.getIcon
import com.captures2024.soongan.presentation.feature.main.home.utils.extension.getStringResId
import com.captures2024.soongan.presentation.viewmodel.model.PostOrderType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun GalleryFilterBottomSheetComponent(
    selectedPostOrderType: PostOrderType,
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
    onClickItem: (PostOrderType) -> Unit,
) {
    val sheetState: SheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        modifier = modifier,
        sheetState = sheetState,
        containerColor = SGColor.Grayscale.white,
    ) {
        PostOrderType.entries.forEachIndexed { idx, postOrderType ->
            if (idx != 0) {
                HorizontalDivider(
                    color = SGColor.Grayscale.black100
                        .copy(
                            alpha = 0.3f,
                        ),
                )
            }

            GalleryFilterItem(
                text = stringResource(id = postOrderType.getStringResId()),
                icon = postOrderType.getIcon(),
                selected = selectedPostOrderType == postOrderType,
                onClickItem = { onClickItem(postOrderType) },
            )
        }
    }
}

@Composable
private fun GalleryFilterItem(
    text: String,
    icon: ImageVector,
    selected: Boolean,
    modifier: Modifier = Modifier,
    onClickItem: () -> Unit = {},
) {
    Box(
        modifier = modifier
            .clip(shape = RoundedCornerShape(8.dp))
            .clickable(onClick = onClickItem),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 56.dp)
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            SGText(
                text = text,
                style = getSGNonScaleTextStyle(
                    color = when (selected) {
                        true -> SGColor.Grayscale.black100

                        false -> SGColor.Grayscale.black100.copy(alpha = 0.3f)
                    },
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 24.sp,
                    fontFamily = SGTypography.pretendard,
                ),
            )

            Icon(
                modifier = Modifier.size(24.dp),
                imageVector = icon,
                contentDescription = text,
                tint = when (selected) {
                    true -> SGColor.Grayscale.black100

                    false -> SGColor.Grayscale.black100.copy(alpha = 0.3f)
                },
            )
        }
    }
}
