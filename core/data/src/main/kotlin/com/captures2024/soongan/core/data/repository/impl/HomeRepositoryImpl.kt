package com.captures2024.soongan.core.data.repository.impl

import com.captures2024.soongan.core.data.remote.HomeDataSource
import com.captures2024.soongan.core.data.repository.HomeRepository
import com.captures2024.soongan.core.model.dto.ContestInfoDto
import com.captures2024.soongan.core.model.dto.PostInfoDto
import javax.inject.Inject

class HomeRepositoryImpl
@Inject
constructor(
    private val homeDataSource: HomeDataSource,
) : HomeRepository {

    override suspend fun getHome(): Pair<ContestInfoDto, List<PostInfoDto>> {
        val response = homeDataSource.getHomeStatus()

        return response ?: error("response is null")
    }
}
