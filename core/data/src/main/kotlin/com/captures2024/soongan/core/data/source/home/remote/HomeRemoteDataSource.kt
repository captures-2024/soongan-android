package com.captures2024.soongan.core.data.source.home.remote

import com.captures2024.soongan.core.model.dto.ContestInfoDto
import com.captures2024.soongan.core.model.dto.PostInfoDto

interface HomeRemoteDataSource {

    suspend fun getHomeStatus(): Pair<ContestInfoDto, List<PostInfoDto>>?
}
