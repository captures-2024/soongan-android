package com.captures2024.soongan.domain.usecase.awards

import com.captures2024.soongan.core.model.dto.awards.AwardsDetailDto

interface GetAwardsInfoUseCase {

    suspend operator fun invoke(awardsId: Long): Result<AwardsDetailDto>
}
