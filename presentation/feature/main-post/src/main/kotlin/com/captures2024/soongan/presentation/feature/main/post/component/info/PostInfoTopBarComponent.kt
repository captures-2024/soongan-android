package com.captures2024.soongan.presentation.feature.main.post.component.info

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.captures2024.soongan.core.designsystem.icon.MyIconPack
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonFillLeftArrow
import com.captures2024.soongan.core.designsystem.ui.component.button.SGIconCircleButton
import com.captures2024.soongan.core.designsystem.ui.theme.SGColor
import com.captures2024.soongan.core.designsystem.ui.theme.SGTheme
import com.captures2024.soongan.core.designsystem.ui.util.DevicePreviews
import com.captures2024.soongan.presentation.feature.main.post.R

@Composable
internal fun PostInfoTopBarComponent(
    onClickBack: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        contentAlignment = Alignment.CenterStart,
    ) {
        SGIconCircleButton(
            imageVector = MyIconPack.IconNonFillLeftArrow,
            contentDescription = stringResource(R.string.back_description),
            iconWidth = 20.dp,
            iconHeight = 16.dp,
            color = SGColor.Grayscale.black100,
            onClick = onClickBack,
        )
    }
}

@DevicePreviews
@Composable
private fun PreviewPostInfoTopBarComponent() {
    SGTheme {
        PostInfoTopBarComponent(
            onClickBack = {},
        )
    }
}
