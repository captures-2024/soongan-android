package com.captures2024.soongan.data.source.fcm.impl.mapper

import com.captures2024.soongan.core.model.dto.FcmDto
import com.captures2024.soongan.core.model.network.response.fcm.InitFcmResponse

internal fun InitFcmResponse.toDto(): FcmDto = FcmDto(
    token = this.token,
    deviceId = this.deviceId,
)
