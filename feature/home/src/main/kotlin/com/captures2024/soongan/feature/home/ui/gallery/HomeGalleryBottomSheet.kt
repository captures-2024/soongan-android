package com.captures2024.soongan.feature.home.ui.gallery

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import com.captures2024.soongan.core.designsystem.component.NonScaleText
import com.captures2024.soongan.core.designsystem.icon.MyIconPack
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconFilterLike
import com.captures2024.soongan.core.designsystem.theme.SGColor
import com.captures2024.soongan.core.designsystem.util.DevicePreviews
import com.captures2024.soongan.feature.home.R
import com.captures2024.soongan.feature.home.state.home_gallery.HomeGalleryUIState
import com.captures2024.soongan.feature.home.utils.PostOrderType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun HomeGalleryBottomSheet(
    uiState: HomeGalleryUIState,
    modifier: Modifier = Modifier,
    sheetState: SheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
    onDismissRequest: () -> Unit = {},
    onClickItem: (PostOrderType) -> Unit = {},
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
                vertical = 20.dp
            )
        ) {
            PostOrderType.entries.forEachIndexed { idx, postOrderType ->
                if (idx != 0) HorizontalDivider(color = SGColor.primaryA.copy(alpha = 0.3f))

                HomeGalleryFilterItem(
                    text = stringResource(id = postOrderType.textId),
                    icon = postOrderType.icon,
                    selected = uiState.postOrderType == postOrderType,
                    onClickItem = { onClickItem(postOrderType) }
                )
            }
        }
    }
}

@Composable
private fun HomeGalleryFilterItem(
    modifier: Modifier = Modifier,
    text: String,
    icon: ImageVector,
    selected: Boolean,
    onClickItem: () -> Unit = {},
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .clickable { onClickItem() }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(horizontal = 19.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            NonScaleText(
                text = text,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = when (selected) {
                    true -> SGColor.primaryA

                    false -> SGColor.primaryA.copy(alpha = 0.3f)
                }
            )
            Icon(
                modifier = Modifier.size(26.dp),
                imageVector = icon,
                contentDescription = text,
                tint = when (selected) {
                    true -> SGColor.primaryA

                    false -> SGColor.primaryA.copy(alpha = 0.3f)
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@DevicePreviews
@Composable
private fun HomeGalleryBottomSheetPreview() {
    val sheetState = SheetState(
        skipPartiallyExpanded = true,
        initialValue = SheetValue.Expanded,
        density = LocalDensity.current,
        skipHiddenState = false
    )

    HomeGalleryBottomSheet(
        uiState = HomeGalleryUIState(),
        sheetState = sheetState,
    )
}

@DevicePreviews
@Composable
private fun HomeGalleryFilterItemPreview() {
    HomeGalleryFilterItem(
        text = stringResource(id = R.string.filter_likes),
        icon = MyIconPack.IconFilterLike,
        selected = false
    )
}