package com.captures2024.soongan.domain.usecase.awards.impl

import com.captures2024.soongan.core.model.dto.awards.AwardsDetailDto
import com.captures2024.soongan.data.repository.awards.AwardsRepository
import com.captures2024.soongan.domain.usecase.awards.GetAwardsInfoUseCase
import com.captures2024.soongan.domain.usecase.utils.runSuspendCatching
import javax.inject.Inject

class GetAwardsInfoUseCaseImpl
@Inject
constructor(
    private val awardsRepository: AwardsRepository,
) : GetAwardsInfoUseCase {

    override suspend fun invoke(awardsId: Long): Result<AwardsDetailDto> = runSuspendCatching {
        return@runSuspendCatching awardsRepository.getAwardsInfo(
            awardsId = awardsId,
        )
    }
}
