package com.captures2024.soongan.feature.welcome.state

import com.captures2024.soongan.core.common.base.UISideEffect

internal sealed interface WelcomeSideEffect : UISideEffect {

    data object NavigateToHome : WelcomeSideEffect
}