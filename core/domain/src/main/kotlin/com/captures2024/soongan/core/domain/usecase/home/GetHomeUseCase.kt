package com.captures2024.soongan.core.domain.usecase.home

import com.captures2024.soongan.core.data.repository.HomeRepository
import com.captures2024.soongan.core.domain.runSuspendCatching
import com.captures2024.soongan.core.model.dto.ContestInfoDto
import com.captures2024.soongan.core.model.dto.PostInfoDto
import javax.inject.Inject

class GetHomeUseCase
@Inject
constructor(
    private val homeRepository: HomeRepository
) {

    suspend operator fun invoke(): Result<Pair<ContestInfoDto, List<PostInfoDto>>> = runSuspendCatching {
        homeRepository.getHome()
    }
}