package com.captures2024.soongan.presentation.feature.main.feed.component.feed

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.presentation.designsystem.icon.MyIconPack
import com.captures2024.soongan.presentation.designsystem.icon.myiconpack.IconFilterLike
import com.captures2024.soongan.presentation.designsystem.ui.component.text.SGText
import com.captures2024.soongan.presentation.designsystem.ui.component.text.getSGNonScaleTextStyle
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGColor
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGTypography
import com.captures2024.soongan.presentation.designsystem.ui.util.DevicePreviews
import com.captures2024.soongan.presentation.feature.main.feed.utils.extensions.getIcon
import com.captures2024.soongan.presentation.feature.main.feed.utils.extensions.getStringResId
import com.captures2024.soongan.presentation.viewmodel.model.PostOrderType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun FeedFilterBottomSheetComponent(
    orderType: PostOrderType,
    modifier: Modifier = Modifier,
    sheetState: SheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
    onDismissRequest: () -> Unit,
    onClickItem: (PostOrderType) -> Unit,
) {
    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        modifier = modifier,
        sheetState = sheetState,
        containerColor = Color.White,
    ) {
        Column(
            modifier = Modifier.padding(
                horizontal = 20.dp,
                vertical = 20.dp,
            ),
        ) {
            PostOrderType.entries.forEachIndexed { idx, postOrderType ->
                if (idx != 0) HorizontalDivider(color = SGColor.primaryA.copy(alpha = 0.3f))

                FeedGalleryFilterItem(
                    text = stringResource(id = postOrderType.getStringResId()),
                    icon = postOrderType.getIcon(),
                    selected = (orderType == postOrderType),
                    onClickItem = { onClickItem(postOrderType) },
                )
            }
        }
    }
}

@Composable
private fun FeedGalleryFilterItem(
    text: String,
    icon: ImageVector,
    selected: Boolean,
    modifier: Modifier = Modifier,
    onClickItem: () -> Unit,
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .clickable { onClickItem() },
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
                        true -> SGColor.primaryA

                        false -> SGColor.primaryA.copy(alpha = 0.3f)
                    },
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 24.sp,
                    fontFamily = SGTypography.nanumSquareNeo,
                ),
            )

            Icon(
                modifier = Modifier.size(24.dp),
                imageVector = icon,
                contentDescription = text,
                tint = when (selected) {
                    true -> SGColor.primaryA

                    false -> SGColor.primaryA.copy(alpha = 0.3f)
                },
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@DevicePreviews
@Composable
private fun FeedFilterBottomSheet_Preview() {
    val sheetState = SheetState(
        skipPartiallyExpanded = true,
        initialValue = SheetValue.Expanded,
        density = LocalDensity.current,
        skipHiddenState = false,
    )

    FeedFilterBottomSheetComponent(
        orderType = PostOrderType.MOST_LIKED,
        sheetState = sheetState,
        onClickItem = {},
        onDismissRequest = {},
    )
}

@DevicePreviews
@Composable
private fun FeedGalleryFilterItem_Preview() {
    FeedGalleryFilterItem(
        text = "좋아요 순",
        icon = MyIconPack.IconFilterLike,
        selected = true,
        onClickItem = {},
    )
}
