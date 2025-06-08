package com.captures2024.soongan.domain.usecase.contest.impl

import com.captures2024.soongan.core.model.dto.WeeklyContestInfoListDto
import com.captures2024.soongan.data.repository.contest.WeeklyContestRepository
import com.captures2024.soongan.domain.usecase.contest.GetWeeklyContestInfoListUseCase
import com.captures2024.soongan.domain.usecase.utils.runSuspendCatching
import javax.inject.Inject

class GetWeeklyContestInfoListUseCaseImpl
@Inject
constructor(
    private val weeklyContestRepository: WeeklyContestRepository,
) : GetWeeklyContestInfoListUseCase {

    override suspend fun invoke(): Result<WeeklyContestInfoListDto> = runSuspendCatching {
        val myGalleryDto = weeklyContestRepository.getWeeklyContestInfoList()

        return@runSuspendCatching myGalleryDto
    }
}
