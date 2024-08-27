package com.captures2024.soongan.core.data.mapper

import com.captures2024.soongan.core.model.dto.FcmDto
import com.captures2024.soongan.core.model.network.response.fcm.InitFcmResponse

internal fun InitFcmResponse.toDto(): FcmDto = FcmDto(
    token = this.token,
    deviceId = this.deviceId
)