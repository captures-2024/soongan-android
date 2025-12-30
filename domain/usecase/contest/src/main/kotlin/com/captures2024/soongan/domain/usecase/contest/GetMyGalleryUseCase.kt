package com.captures2024.soongan.domain.usecase.contest

import com.captures2024.soongan.core.model.dto.MyGalleryDto

interface GetMyGalleryUseCase {

    suspend operator fun invoke(
        page: Int,
        pageSize: Int,
    ): Result<MyGalleryDto>
}
