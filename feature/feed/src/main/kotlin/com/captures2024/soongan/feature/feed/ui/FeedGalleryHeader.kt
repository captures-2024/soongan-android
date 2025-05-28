package com.captures2024.soongan.feature.feed.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.captures2024.soongan.core.designsystem.icon.MyIconPack
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonFillFillter
import com.captures2024.soongan.core.designsystem.ui.component.WidthSpacer
import com.captures2024.soongan.core.designsystem.ui.component.button.SGIconCircleButton
import com.captures2024.soongan.core.designsystem.ui.component.gallery.SGGalleryHeader
import com.captures2024.soongan.core.designsystem.ui.component.gallery.SGGalleryHeaderTitle
import com.captures2024.soongan.core.designsystem.ui.theme.SGColor
import com.captures2024.soongan.core.designsystem.ui.util.DevicePreviews
import com.captures2024.soongan.core.viewmodel.feed.FeedViewModel

@Composable
internal fun FeedGalleryHeader(
    selectedOption: Pair<Int, String>,
    modifier: Modifier = Modifier,
    onClickTitle: () -> Unit = {},
    onClickFilter: () -> Unit = {},
) {
    SGGalleryHeader(
        modifier = modifier,
        trailingIcon = {
            SGIconCircleButton(
                imageVector = MyIconPack.IconNonFillFillter,
                contentDescription = "select gallery posts order type",
                onClick = onClickFilter,
            )
        },
    ) {
        // open scroll picker
        Row(
            modifier = Modifier.clickable(onClick = onClickTitle),
        ) {
            SGGalleryHeaderTitle(
                prefix = "${selectedOption.first}회차",
                suffix = selectedOption.second,
            )
            WidthSpacer(26.dp)
            TempArrowDownIcon()
        }

        // drop down menu
//        FeedDropDownMenu(
//            selectedOption = selectedOption,
//            options = options,
//            onClickRound = onClickRound,
//        )
    }
}

@Composable
private fun TempArrowDownIcon(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .background(color = SGColor.black40, shape = CircleShape)
            .size(20.dp),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            imageVector = Icons.Default.KeyboardArrowDown,
            contentDescription = null,
            tint = SGColor.black100,
        )
    }
}

@DevicePreviews
@Composable
private fun FeedHeaderPreview() {
    val state = FeedViewModel.State()

    FeedGalleryHeader(
        selectedOption = state.currentTitleOption,
        onClickTitle = {},
    )
}
