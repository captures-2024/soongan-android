package com.captures2024.soongan.presentation.feature.main.profile.component.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.captures2024.soongan.presentation.designsystem.ui.component.HeightSpacer
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGColor
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGTheme
import com.captures2024.soongan.presentation.designsystem.ui.util.DevicePreviews
import com.captures2024.soongan.presentation.designsystem.ui.util.extension.sgBottomBarPadding
import com.captures2024.soongan.presentation.feature.main.profile.component.menu.ProfileMenuBottomSheet
import com.captures2024.soongan.presentation.feature.main.profile.component.profile.ProfileBodyComponent
import com.captures2024.soongan.presentation.feature.main.profile.component.profile.ProfileTopBarComponent
import com.captures2024.soongan.presentation.viewmodel.main.profile.ProfileViewModel
import com.captures2024.soongan.presentation.viewmodel.model.PaginationStatus
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
    onRefresh: () -> Unit,
    onLoadNextPage: () -> Unit,
    onClickPost: (Long) -> Unit,
    onClickRegisterPost: () -> Unit,
) {
    Column(
        modifier = modifier.fillMaxSize()
            .sgBottomBarPadding()
            .padding(top = 26.dp),
    ) {
        ProfileTopBarComponent(
            userProfile = state.userProfile,
            isShowBadge = state.isNotReadNotification,
            modifier = Modifier.padding(
                start = 20.dp,
                end = 16.dp,
            ),
            onClickNotification = onClickNotification,
            onClickMenu = onClickMenu,
        )

        HeightSpacer(28.dp)

        HorizontalDivider(
            thickness = 1.dp,
            color = SGColor.buttonDisableGray,
        )

        ProfileBodyComponent(
            state = state.myGalleryState,
            onRefresh = onRefresh,
            onLoadNextPage = onLoadNextPage,
            onClickPost = onClickPost,
            onClickRegisterPost = onClickRegisterPost,
        )
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
                userProfile = UserProfile.guestUserProfile,
                myGalleryState = ProfileViewModel.State.MyGalleryState(
                    isRefreshing = false,
                    posts = emptyList(),
                    paginationStatus = PaginationStatus.DEFAULT,
                    loadPage = 0,
                    loadPageSize = 50,
                    hasNextPage = false,
                ),
                isShowMenuBottomSheet = false,
            ),
            onClickNotification = {},
            onClickMenu = {},
            onDismissRequestMenuBottomSheet = {},
            onClickEditProfile = {},
            onClickFaq = {},
            onClickTerms = {},
            onRefresh = {},
            onLoadNextPage = {},
            onClickPost = {},
            onClickRegisterPost = {},
        )
    }
}
