package com.captures2024.soongan.feature.profile.state.profile

import com.captures2024.soongan.core.analytics.utils.LogElementArgument
import com.captures2024.soongan.core.common.Validation
import com.captures2024.soongan.core.common.base.UIState
import com.captures2024.soongan.core.model.UserProfile
import com.captures2024.soongan.core.model.dto.GalleryPostDto
import com.captures2024.soongan.core.model.utils.PaginationStatus

internal data class ProfileUIState(
    val isLoading: Boolean = false,
    val userProfile: UserProfile = UserProfile(),
    val editingState: EditingState = EditingState(),
    val isRefreshing: Boolean = false,
    val paginationStatus: PaginationStatus = PaginationStatus.INACTIVE,
    val myPosts: List<GalleryPostDto> = emptyList(),
    val nextPage: Int = 0,
    val hasNextPage: Boolean = false,
    val hasNotification: Boolean = false,
    val isOpenBottomSheet: Boolean = false,
) : UIState {

    override fun toLoggingElements(): Array<LogElementArgument> = arrayOf(
        LogElementArgument("isLoading", isLoading.toString()),
        LogElementArgument("userProfile", userProfile.toString()),
        LogElementArgument("editingState", editingState.toString()),
        LogElementArgument("isRefreshing", isRefreshing.toString()),
        LogElementArgument("paginationStatus", paginationStatus.toString()),
        LogElementArgument("myPosts", myPosts.toString()),
        LogElementArgument("nextPage", nextPage.toString()),
        LogElementArgument("hasNextPage", hasNextPage.toString()),
        LogElementArgument("hasNotification", hasNotification.toString()),
        LogElementArgument("isOpenBottomSheet", isOpenBottomSheet.toString()),
    )
}

internal data class EditingState(
    val editingProfile: UserProfile = UserProfile(),
    val isEditable: Boolean = false,
    val isDuplicatedNickname: Boolean = false,
) {
    val isValidNickname: Validation.NicknameValidState
        get() = Validation.isValidNickname(editingProfile.nickname)

    val isValidIntroduction: Validation.IntroductionValidState
        get() = Validation.isValidSelfIntroduction(editingProfile.selfIntroduction)
}