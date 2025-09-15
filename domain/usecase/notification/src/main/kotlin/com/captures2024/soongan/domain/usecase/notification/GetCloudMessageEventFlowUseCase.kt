package com.captures2024.soongan.domain.usecase.notification

import com.captures2024.soongan.core.model.dto.fcm.CloudMessage
import kotlinx.coroutines.flow.Flow

interface GetCloudMessageEventFlowUseCase {

    operator fun invoke(): Flow<CloudMessage?>
}
