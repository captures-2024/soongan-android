package com.captures2024.soongan.feature.home.state.home_gallery

import com.captures2024.soongan.core.common.base.UISideEffect
import com.captures2024.soongan.core.model.UserPost

internal sealed interface HomeGallerySideEffect : UISideEffect {

    data class NavigateToHomePost(
        val post: UserPost.PhotoPost
    ) : HomeGallerySideEffect

}