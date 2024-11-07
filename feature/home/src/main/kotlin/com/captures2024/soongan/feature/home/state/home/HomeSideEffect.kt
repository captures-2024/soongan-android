package com.captures2024.soongan.feature.home.state.home

import com.captures2024.soongan.core.common.base.UISideEffect
import com.captures2024.soongan.core.model.UserPost

internal sealed interface HomeSideEffect : UISideEffect {

    data object NavigateToHomeExhibition : HomeSideEffect

    data object NavigateToHomeGallery : HomeSideEffect

    data class NavigateToHomePost(
        val myPost: UserPost.PhotoPost
    ) : HomeSideEffect
}
