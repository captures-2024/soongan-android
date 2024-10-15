package com.captures2024.soongan.feature.termsofuse.state

import com.captures2024.soongan.core.common.base.UIIntent

internal sealed interface TermsOfUseIntent : UIIntent {

    data object OnClickBack : TermsOfUseIntent

}