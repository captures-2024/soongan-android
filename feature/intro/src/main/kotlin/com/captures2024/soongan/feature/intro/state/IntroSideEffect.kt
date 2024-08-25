package com.captures2024.soongan.feature.intro.state

import com.captures2024.soongan.core.common.base.UISideEffect

sealed interface IntroSideEffect : UISideEffect {

    data object NavigateToSign : IntroSideEffect

    data object NavigateToMain : IntroSideEffect

}