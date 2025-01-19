package com.captures2024.soongan.feature.profile.state.profile

import com.captures2024.soongan.core.common.base.UISideEffect

internal sealed interface ProfileSideEffect : UISideEffect {

    // Profile Screen - SideEffect
    sealed interface ProfileSE : ProfileSideEffect {

        data object NavigateToNotification : ProfileSE

        data class NavigateToHomePost(
            val postId: Int,
        ) : ProfileSE

        data object NavigateToRegistrationPost : ProfileSE
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