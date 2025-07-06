package com.captures2024.soongan.domain.usecase.awards.impl

import com.captures2024.soongan.core.model.dto.awards.AwardsDefaultDto
import com.captures2024.soongan.data.repository.awards.AwardsRepository
import com.captures2024.soongan.domain.usecase.awards.GetAwardsListUseCase
import com.captures2024.soongan.domain.usecase.utils.runSuspendCatching
import javax.inject.Inject

class GetAwardsListUseCaseImpl
@Inject
constructor(
    private val awardsRepository: AwardsRepository,
) : GetAwardsListUseCase {

    override suspend operator fun invoke(): Result<List<AwardsDefaultDto>> = runSuspendCatching {
        return@runSuspendCatching awardsRepository.getAwardsList()
    }
}
