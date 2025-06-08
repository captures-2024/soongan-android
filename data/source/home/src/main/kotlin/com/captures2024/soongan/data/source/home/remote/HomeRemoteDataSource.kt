package com.captures2024.soongan.data.source.home.remote

import com.captures2024.soongan.core.model.dto.HomeContestInfoDto
import com.captures2024.soongan.core.model.dto.PostInfoDto

interface HomeRemoteDataSource {

    suspend fun getHomeStatus(): Pair<HomeContestInfoDto, List<PostInfoDto>>?

    suspend fun getHomeStatusByGuest(): Pair<HomeContestInfoDto, List<PostInfoDto>>?
}
