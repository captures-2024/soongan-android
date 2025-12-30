package com.captures2024.soongan.domain.usecase.member

import com.captures2024.soongan.core.model.dto.UserInfoDto
import kotlinx.coroutines.flow.StateFlow

interface GetCurrentMemberFlowUseCase {

    operator fun invoke(): StateFlow<UserInfoDto?>
}
