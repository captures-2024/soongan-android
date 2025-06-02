package com.captures2024.soongan.presentation.feature.main.home.component.gallery

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.captures2024.soongan.presentation.designsystem.icon.MyIconPack
import com.captures2024.soongan.presentation.designsystem.icon.myiconpack.IconNonFillFillter
import com.captures2024.soongan.presentation.designsystem.icon.myiconpack.IconNonFillLeftArrow
import com.captures2024.soongan.presentation.designsystem.ui.component.button.SGIconCircleButton
import com.captures2024.soongan.presentation.designsystem.ui.component.gallery.SGGalleryHeader
import com.captures2024.soongan.presentation.designsystem.ui.component.gallery.SGGalleryHeaderTitle
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGTheme
import com.captures2024.soongan.presentation.designsystem.ui.util.DevicePreviews

@Composable
internal fun GalleryTopBarComponent(
    round: String,
    subject: String,
    modifier: Modifier = Modifier,
    onClickBack: () -> Unit,
    onClickFilter: () -> Unit,
) {
    SGGalleryHeader(
        modifier = modifier,
        leadingIcon = {
            SGIconCircleButton(
                imageVector = MyIconPack.IconNonFillLeftArrow,
                contentDescription = "back",
                iconWidth = 20.dp,
                iconHeight = 16.dp,
                onClick = onClickBack,
            )
        },
        trailingIcon = {
            SGIconCircleButton(
                imageVector = MyIconPack.IconNonFillFillter,
                contentDescription = "filter",
                onClick = onClickFilter,
            )
        },
    ) {
        SGGalleryHeaderTitle(
            prefix = round,
            suffix = subject,
        )
    }
}

@DevicePreviews
@Composable
private fun PreviewGalleryTopBarComponent() {
    SGTheme {
        GalleryTopBarComponent(
            round = "testRound",
            subject = "testSubject",
            onClickBack = {},
            onClickFilter = {},
        )
    }
}
