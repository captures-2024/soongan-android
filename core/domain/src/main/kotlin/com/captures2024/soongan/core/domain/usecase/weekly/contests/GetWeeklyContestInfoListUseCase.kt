package com.captures2024.soongan.core.domain.usecase.weekly.contests

import com.captures2024.soongan.core.data.repository.WeeklyContestRepository
import com.captures2024.soongan.core.domain.runSuspendCatching
import com.captures2024.soongan.core.model.dto.WeeklyContestInfoListDto
import javax.inject.Inject

class GetWeeklyContestInfoListUseCase
@Inject
constructor(
    private val weeklyContestRepository: WeeklyContestRepository,
) {

    suspend operator fun invoke(): Result<WeeklyContestInfoListDto> = runSuspendCatching {
        val myGalleryDto = weeklyContestRepository.getWeeklyContestInfoList()

        return@runSuspendCatching myGalleryDto
    }
}
