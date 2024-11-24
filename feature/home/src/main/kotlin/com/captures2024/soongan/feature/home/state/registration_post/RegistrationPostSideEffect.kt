package com.captures2024.soongan.feature.home.state.registration_post

import com.captures2024.soongan.core.common.base.UISideEffect

internal sealed interface RegistrationPostSideEffect : UISideEffect {

    data object OpenMediaPicker : RegistrationPostSideEffect

    data object NavigateToBack : RegistrationPostSideEffect

    data object NavigateToPost : RegistrationPostSideEffect
}