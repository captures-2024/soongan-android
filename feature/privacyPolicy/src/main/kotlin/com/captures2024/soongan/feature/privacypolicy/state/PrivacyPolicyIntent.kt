package com.captures2024.soongan.feature.privacypolicy.state

import com.captures2024.soongan.core.common.base.UIIntent

internal sealed interface PrivacyPolicyIntent : UIIntent {

    data object OnClickBack : PrivacyPolicyIntent

}