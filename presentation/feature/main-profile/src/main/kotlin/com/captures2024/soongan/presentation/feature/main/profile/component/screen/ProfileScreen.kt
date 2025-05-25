package com.captures2024.soongan.presentation.feature.main.profile.component.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.captures2024.soongan.core.designsystem.ui.component.HeightSpacer
import com.captures2024.soongan.core.designsystem.ui.theme.SGColor
import com.captures2024.soongan.core.designsystem.ui.theme.SGTheme
import com.captures2024.soongan.core.designsystem.ui.util.DevicePreviews
import com.captures2024.soongan.presentation.feature.main.profile.component.menu.ProfileMenuBottomSheet
import com.captures2024.soongan.presentation.feature.main.profile.component.profile.ProfileTopBarComponent
import com.captures2024.soongan.presentation.viewmodel.main.profile.ProfileViewModel
import com.captures2024.soongan.presentation.viewmodel.model.UserProfile

@Composable
internal fun ProfileScreen(
    state: ProfileViewModel.State,
    modifier: Modifier = Modifier,
    onClickNotification: () -> Unit,
    onClickMenu: () -> Unit,
    onDismissRequestMenuBottomSheet: () -> Unit,
    onClickEditProfile: () -> Unit,
    onClickFaq: () -> Unit,
    onClickTerms: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = SGColor.Grayscale.white)
            .padding(top = 26.dp),
    ) {
        ProfileTopBarComponent(
            userProfile = state.userProfile,
            modifier = Modifier.padding(
                start = 20.dp,
                end = 16.dp,
            ),
            onClickNotification = onClickNotification,
            onClickMenu = onClickMenu,
        )

        HeightSpacer(28.dp)

        // TODO POST
    }

    if (state.isShowMenuBottomSheet) {
        ProfileMenuBottomSheet(
            onDismissRequest = onDismissRequestMenuBottomSheet,
            navigateToEditProfile = onClickEditProfile,
            navigateToFaq = onClickFaq,
            navigateToTerms = onClickTerms,
        )
    }
}

@DevicePreviews
@Composable
private fun PreviewProfileScreen() {
    SGTheme {
        ProfileScreen(
            state = ProfileViewModel.State(
                userProfile = UserProfile(),
                isShowMenuBottomSheet = false,
            ),
            onClickNotification = {},
            onClickMenu = {},
            onDismissRequestMenuBottomSheet = {},
            onClickEditProfile = {},
            onClickFaq = {},
            onClickTerms = {},
        )
    }
}
