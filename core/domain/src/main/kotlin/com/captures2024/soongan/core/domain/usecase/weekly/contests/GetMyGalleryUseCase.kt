package com.captures2024.soongan.core.domain.usecase.weekly.contests

import com.captures2024.soongan.core.data.repository.WeeklyContestRepository
import com.captures2024.soongan.core.domain.runSuspendCatching
import com.captures2024.soongan.core.model.dto.MyGalleryDto
import javax.inject.Inject

class GetMyGalleryUseCase
@Inject
constructor(
    private val weeklyContestRepository: WeeklyContestRepository,
) {

    suspend operator fun invoke(params: Params): Result<MyGalleryDto> = runSuspendCatching {
        val myGalleryDto = weeklyContestRepository.getMyGalleryInfo(
            page = params.page,
            pageSize = params.pageSize
        )

        return@runSuspendCatching myGalleryDto
    }


    data class Params(
        val page: Int,
        val pageSize: Int,
    )
}