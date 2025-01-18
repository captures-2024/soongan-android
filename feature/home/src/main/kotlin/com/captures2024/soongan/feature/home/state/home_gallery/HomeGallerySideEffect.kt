package com.captures2024.soongan.feature.home.state.home_gallery

import com.captures2024.soongan.core.common.base.UISideEffect

internal sealed interface HomeGallerySideEffect : UISideEffect {

    data class NavigateToHomePost(
        val postId: Int
    ) : HomeGallerySideEffect

    data object NavigateToRegistrationPost: HomeGallerySideEffect
}