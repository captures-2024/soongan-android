package com.captures2024.soongan.feature.profile.state.profile

import com.captures2024.soongan.core.common.base.UISideEffect
import com.captures2024.soongan.core.model.UserPost
import com.captures2024.soongan.core.model.UserProfile

internal sealed interface ProfileSideEffect : UISideEffect {

    data object NavigateToNotification : ProfileSideEffect

    data class NavigateToEditProfile(
        val userProfile: UserProfile,
    ) : ProfileSideEffect

    data class NavigateToHomePost(
        val userPhoto: UserPost.PhotoPost,
    ) : ProfileSideEffect
}