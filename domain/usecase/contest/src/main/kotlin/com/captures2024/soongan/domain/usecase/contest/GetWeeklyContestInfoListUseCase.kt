package com.captures2024.soongan.domain.usecase.contest

import com.captures2024.soongan.core.model.dto.WeeklyContestInfoListDto

interface GetWeeklyContestInfoListUseCase {

    suspend operator fun invoke(): Result<WeeklyContestInfoListDto>
}
