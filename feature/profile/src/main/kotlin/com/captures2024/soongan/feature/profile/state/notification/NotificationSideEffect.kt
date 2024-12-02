package com.captures2024.soongan.feature.profile.state.notification

internal sealed interface NotificationSideEffect {
    data object NavigateToBack : NotificationSideEffect
}