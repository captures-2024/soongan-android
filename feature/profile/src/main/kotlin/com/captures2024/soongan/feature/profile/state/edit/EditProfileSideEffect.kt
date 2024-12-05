package com.captures2024.soongan.feature.profile.state.edit

import com.captures2024.soongan.core.common.base.UISideEffect

internal sealed interface EditProfileSideEffect : UISideEffect {

    data object OpenMediaPicker : EditProfileSideEffect

    data object NavigateToBack : EditProfileSideEffect
}