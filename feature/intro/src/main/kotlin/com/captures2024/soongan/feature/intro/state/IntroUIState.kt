package com.captures2024.soongan.feature.intro.state

import com.captures2024.soongan.core.common.base.UIState

data class IntroUIState(
    val useDarkTheme: Boolean = false,
    val disableDynamicTheming: Boolean = false,
    val useAndroidTheme: Boolean = false
) : UIState {
}