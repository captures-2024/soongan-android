package com.captures2024.soongan.presentation.feature.main.profile.component.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.core.designsystem.ui.component.button.SGTextButtonType2
import com.captures2024.soongan.core.designsystem.ui.component.text.SGText
import com.captures2024.soongan.core.designsystem.ui.component.text.getSGNonScaleTextStyle
import com.captures2024.soongan.core.designsystem.ui.theme.SGColor
import com.captures2024.soongan.core.designsystem.ui.theme.SGTheme
import com.captures2024.soongan.core.designsystem.ui.theme.SGTypography
import com.captures2024.soongan.core.designsystem.ui.util.DevicePreviews
import com.captures2024.soongan.presentation.feature.main.profile.R
import com.captures2024.soongan.presentation.feature.main.profile.component.menu.ProfileMenuItemTopBarComponent

@Composable
internal fun ProfileMenuWithdrawDoneScreen(
    modifier: Modifier = Modifier,
    onClickDone: () -> Unit,
) {
    Column(modifier = modifier) {
        ProfileMenuItemTopBarComponent(
            title = stringResource(R.string.profile_menu_withdraw_done_title),
        )

        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(all = 40.dp),
        ) {
            SGText(
                text = stringResource(R.string.profile_menu_withdraw_done_description),
                style = getSGNonScaleTextStyle(
                    color = SGColor.Grayscale.black100,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    lineHeight = 24.sp,
                    fontFamily = SGTypography.pretendard,
                    letterSpacing = (-5).em,
                ),
            )

            SGTextButtonType2(
                text = stringResource(R.string.button_confirm),
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .padding(bottom = 28.dp),
                enabled = true,
                onClick = onClickDone,
            )
        }
    }
}

@DevicePreviews
@Composable
private fun PreviewProfileMenuWithdrawDoneScreen() {
    SGTheme {
        ProfileMenuWithdrawDoneScreen(
            onClickDone = {},
        )
    }
}
