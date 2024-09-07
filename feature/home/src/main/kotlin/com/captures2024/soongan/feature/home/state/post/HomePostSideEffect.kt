package com.captures2024.soongan.feature.home.state.post

import com.captures2024.soongan.core.common.base.UISideEffect

internal sealed interface HomePostSideEffect : UISideEffect {

    data class NavigateToHomePostPhoto(
        val url: String
    ) : HomePostSideEffect

}