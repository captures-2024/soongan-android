package com.captures2024.soongan.feature.profile.state.profile

import com.captures2024.soongan.core.analytics.utils.LogElementArgument
import com.captures2024.soongan.core.common.base.UIState
import com.captures2024.soongan.core.model.UserPost
import com.captures2024.soongan.core.model.UserProfile
import com.captures2024.soongan.core.model.mock.samplePhotos

internal data class ProfileUIState(
    val isLoading: Boolean = false,
    val userProfile: UserProfile = UserProfile(),
    val editingState: EditingState = EditingState(),
    val userPosts: List<UserPost.PhotoPost> = samplePhotos.map { it as UserPost.PhotoPost },
    val hasNotification: Boolean = false,
    val isOpenBottomSheet: Boolean = false,
) : UIState {

    override fun toLoggingElements(): Array<LogElementArgument> = arrayOf(
        LogElementArgument("isLoading", isLoading.toString()),
        LogElementArgument("userProfile", userProfile.toString()),
        LogElementArgument("editingState", editingState.toString()),
        LogElementArgument("userPosts", userPosts.toString()),
        LogElementArgument("hasNotification", hasNotification.toString()),
        LogElementArgument("isOpenBottomSheet", isOpenBottomSheet.toString()),
    )
}

internal data class EditingState(
    val editingProfile: UserProfile = UserProfile(),
    val isEditable: Boolean = false,
)