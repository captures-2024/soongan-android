package com.captures2024.soongan.domain.usecase.contest

import com.captures2024.soongan.core.model.dto.GalleryDto

interface GetFilteredGalleryByReportTargetIdsUseCase {

    suspend operator fun invoke(
        round: Int?,
        orderType: String,
        page: Int,
        pageSize: Int,
    ): Result<GalleryDto>
}
