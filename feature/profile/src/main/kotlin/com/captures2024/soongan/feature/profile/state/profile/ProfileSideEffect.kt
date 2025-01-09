package com.captures2024.soongan.feature.profile.state.profile

import com.captures2024.soongan.core.common.base.UISideEffect
import com.captures2024.soongan.core.model.UserPost

internal sealed interface ProfileSideEffect : UISideEffect {

    // Profile Screen - SideEffect
    sealed interface ProfileSE : ProfileSideEffect {

        data object NavigateToNotification : ProfileSE

        data class NavigateToHomePost(
            val userPhoto: UserPost.PhotoPost,
        ) : ProfileSE
    }

    // Profile Menu Bottom Sheet - SideEffect
    sealed interface BottomSheetSE : ProfileSideEffect {

        data object NavigateToEditProfile : BottomSheetSE
    }

    // Edit Screen - SideEffect
    sealed interface EditSE : ProfileSideEffect {

        data object NavigateToBack : EditSE

        data object OpenMediaPicker : EditSE
    }
}