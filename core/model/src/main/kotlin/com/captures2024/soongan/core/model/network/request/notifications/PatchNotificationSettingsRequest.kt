package com.captures2024.soongan.core.model.network.request.notifications

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PatchNotificationSettingsRequest(
    @SerialName("contestPush")
    val contestPush: Boolean,
    @SerialName("activityPush")
    val activityPush: Boolean,
    @SerialName("noticePush")
    val noticePush: Boolean,
)
