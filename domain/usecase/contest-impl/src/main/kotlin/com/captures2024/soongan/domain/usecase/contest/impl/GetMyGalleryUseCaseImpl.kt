package com.captures2024.soongan.domain.usecase.contest.impl

import com.captures2024.soongan.core.model.dto.MyGalleryDto
import com.captures2024.soongan.domain.repository.contest.WeeklyContestRepository
import com.captures2024.soongan.domain.usecase.contest.GetMyGalleryUseCase
import com.captures2024.soongan.domain.usecase.utils.runSuspendCatching
import javax.inject.Inject

class GetMyGalleryUseCaseImpl
@Inject
constructor(
    private val weeklyContestRepository: WeeklyContestRepository,
) : GetMyGalleryUseCase {

    override suspend fun invoke(
        page: Int,
        pageSize: Int,
    ): Result<MyGalleryDto> = runSuspendCatching {
        val myGalleryDto = weeklyContestRepository.getMyGalleryInfo(
            page = page,
            pageSize = pageSize,
        )

        return@runSuspendCatching myGalleryDto
    }
}
