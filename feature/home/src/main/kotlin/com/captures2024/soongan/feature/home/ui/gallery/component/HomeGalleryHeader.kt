package com.captures2024.soongan.feature.home.ui.gallery.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.captures2024.soongan.core.designsystem.icon.MyIconPack
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonFillFillter
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonFillLeftArrow
import com.captures2024.soongan.core.designsystem.ui.component.button.SGIconCircleButton
import com.captures2024.soongan.core.designsystem.ui.component.gallery.SGGalleryHeader
import com.captures2024.soongan.core.designsystem.ui.component.gallery.SGGalleryHeaderTitle
import com.captures2024.soongan.core.designsystem.ui.util.DevicePreviews
import com.captures2024.soongan.feature.home.R

@Composable
internal fun HomeGalleryHeader(
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit = {},
    onClickFilter: () -> Unit = {},
) {
    SGGalleryHeader(
        modifier = modifier,
        leadingIcon = {
            SGIconCircleButton(
                imageVector = MyIconPack.IconNonFillLeftArrow,
                contentDescription = "back to previous screen",
                iconWidth = 20.dp,
                iconHeight = 16.dp,
                onClick = onBackPressed,
            )
        },
        trailingIcon = {
            SGIconCircleButton(
                imageVector = MyIconPack.IconNonFillFillter,
                contentDescription = "select gallery posts order type",
                onClick = onClickFilter,
            )
        },
    ) {
        SGGalleryHeaderTitle(
            prefix = stringResource(id = R.string.home_gallery_top_week_example),
            suffix = stringResource(id = R.string.home_gallery_top_topic_example),
        )
    }
}

@DevicePreviews
@Composable
private fun HomeGalleryHeaderPreview() {
    HomeGalleryHeader()
}
