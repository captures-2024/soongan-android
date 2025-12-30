package com.captures2024.soongan.domain.usecase.member

import com.captures2024.soongan.core.model.dto.UserInfoDto

interface GetMemberInfoUseCase {

    suspend operator fun invoke(): Result<UserInfoDto>
}
