package com.captures2024.soongan.presentation.feature.main.profile.component.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.captures2024.soongan.presentation.designsystem.icon.MyIconPack
import com.captures2024.soongan.presentation.designsystem.icon.myiconpack.IconNonFillLeftArrow
import com.captures2024.soongan.presentation.designsystem.ui.component.button.SGIconCircleButton
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGTheme
import com.captures2024.soongan.presentation.designsystem.ui.util.DevicePreviews
import com.captures2024.soongan.presentation.feature.main.profile.R

@Composable
internal fun ProfileEditTopBarComponent(
    modifier: Modifier = Modifier,
    onClickBack: () -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp),
        contentAlignment = Alignment.CenterStart,
    ) {
        SGIconCircleButton(
            imageVector = MyIconPack.IconNonFillLeftArrow,
            contentDescription = stringResource(R.string.back_description),
            iconWidth = 20.dp,
            iconHeight = 16.dp,
            onClick = onClickBack,
        )
    }
}

@DevicePreviews
@Composable
private fun PreviewProfileEditTopBarComponent() {
    SGTheme {
        ProfileEditTopBarComponent(
            onClickBack = {},
        )
    }
}
