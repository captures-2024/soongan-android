package com.captures2024.soongan.domain.usecase.home.impl

import com.captures2024.soongan.core.model.dto.HomeContestInfoDto
import com.captures2024.soongan.core.model.dto.PostInfoDto
import com.captures2024.soongan.domain.repository.home.HomeRepository
import com.captures2024.soongan.domain.usecase.home.GetHomeUseCase
import com.captures2024.soongan.domain.usecase.utils.runSuspendCatching
import javax.inject.Inject

class GetHomeUseCaseImpl
@Inject
constructor(
    private val homeRepository: HomeRepository,
) : GetHomeUseCase {

    override suspend fun invoke(): Result<Pair<HomeContestInfoDto, List<PostInfoDto>>> = runSuspendCatching {
        homeRepository.getHome()
    }
}
