package com.captures2024.soongan.presentation.feature.main.feed.component.feed

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.captures2024.soongan.presentation.designsystem.icon.MyIconPack
import com.captures2024.soongan.presentation.designsystem.icon.myiconpack.IconNonFillDownArrow
import com.captures2024.soongan.presentation.designsystem.icon.myiconpack.IconNonFillFillter
import com.captures2024.soongan.presentation.designsystem.ui.component.WidthSpacer
import com.captures2024.soongan.presentation.designsystem.ui.component.button.SGIconCircleButton
import com.captures2024.soongan.presentation.designsystem.ui.component.gallery.SGGalleryHeader
import com.captures2024.soongan.presentation.designsystem.ui.component.gallery.SGGalleryHeaderTitle
import com.captures2024.soongan.presentation.designsystem.ui.util.DevicePreviews
import com.captures2024.soongan.presentation.feature.main.feed.R
import com.captures2024.soongan.presentation.viewmodel.model.TitleOption

@Composable
internal fun FeedGalleryHeaderComponent(
    selectedOption: TitleOption,
    modifier: Modifier = Modifier,
    onClickTitle: () -> Unit,
    onClickFilter: () -> Unit,
) {
    SGGalleryHeader(
        modifier = modifier,
        trailingIcon = {
            SGIconCircleButton(
                imageVector = MyIconPack.IconNonFillFillter,
                contentDescription = "filter",
                onClick = onClickFilter,
            )
        },
    ) {
        Row(
            modifier = Modifier
                .height(44.dp)
                .clickable(
                    onClick = onClickTitle,
                    enabled = selectedOption.hasValidSubject,
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            Icon(imageVector = MyIconPack.IconNonFillDownArrow, contentDescription = "")
            WidthSpacer(10.dp)
            SGGalleryHeaderTitle(
                prefix = stringResource(
                    R.string.feed_gallery_header_title_round_unit,
                    selectedOption.round,
                ),
                suffix = selectedOption.subject,
            )
        }
    }
}

@DevicePreviews
@Composable
private fun FeedGalleryHeaderComponent_Preview() {
    FeedGalleryHeaderComponent(
        selectedOption = TitleOption(round = 1, subject = "주제"),
        onClickTitle = {},
        onClickFilter = {},
    )
}
