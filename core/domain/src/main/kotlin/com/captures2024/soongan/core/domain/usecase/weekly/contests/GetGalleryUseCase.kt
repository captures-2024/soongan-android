package com.captures2024.soongan.core.domain.usecase.weekly.contests

import com.captures2024.soongan.core.data.repository.WeeklyContestRepository
import com.captures2024.soongan.core.domain.runSuspendCatching
import com.captures2024.soongan.core.model.dto.GalleryDto
import javax.inject.Inject

class GetGalleryUseCase
@Inject
constructor(
    private val weeklyContestRepository: WeeklyContestRepository,
) {

    suspend operator fun invoke(params: Params): Result<GalleryDto> = runSuspendCatching {
        val galleryDto = weeklyContestRepository.getGalleryInfo(
            round = params.round,
            orderType = params.orderType,
            page = params.page,
            pageSize = params.pageSize,
        )

        return@runSuspendCatching galleryDto
    }

    data class Params(
        val round: Int?,
        val orderType: String,
        val page: Int,
        val pageSize: Int,
    )
}
