package com.captures2024.soongan.domain.usecase.awards

import com.captures2024.soongan.core.model.dto.awards.AwardsDefaultDto

interface GetAwardsListUseCase {

    suspend operator fun invoke(): Result<List<AwardsDefaultDto>>
}
