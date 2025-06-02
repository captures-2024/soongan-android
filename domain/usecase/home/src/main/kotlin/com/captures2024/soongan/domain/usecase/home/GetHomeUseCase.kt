package com.captures2024.soongan.domain.usecase.home

import com.captures2024.soongan.core.model.dto.HomeContestInfoDto
import com.captures2024.soongan.core.model.dto.PostInfoDto

interface GetHomeUseCase {

    suspend operator fun invoke(): Result<Pair<HomeContestInfoDto, List<PostInfoDto>>>
}
