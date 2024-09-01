package com.captures2024.soongan.feature.privacypolicy.state

import com.captures2024.soongan.core.common.base.UISideEffect

internal sealed interface PrivacyPolicySideEffect : UISideEffect {

    data object NavigateToBack : PrivacyPolicySideEffect

}