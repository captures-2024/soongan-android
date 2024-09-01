package com.captures2024.soongan.feature.termsofuse.state

import com.captures2024.soongan.core.common.base.UISideEffect

internal sealed interface TermsOfUseSideEffect : UISideEffect {

    data object NavigateToBack : TermsOfUseSideEffect

}