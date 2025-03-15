package com.captures2024.soongan.core.data.remote

import com.captures2024.soongan.core.model.dto.GalleryDto
import com.captures2024.soongan.core.model.dto.MyGalleryDto
import com.captures2024.soongan.core.model.dto.PostInfoDto

interface WeeklyContestDataSource {

    suspend fun getGalleryInfo(
        round: Int?,
        orderType: String,
        page: Int,
        pageSize: Int,
    ): GalleryDto?

    suspend fun registerPost(
        title: String,
        imageFile: String,
    ): PostInfoDto?

    suspend fun getPostInfo(
        postId: Long,
    ): PostInfoDto?

    suspend fun deletePost(
        postId: Long,
    ): Boolean

    suspend fun editPostTitle(
        postId: Long,
        title: String,
    ): String?

    suspend fun getMyGalleryInfo(
        page: Int,
        pageSize: Int,
    ): MyGalleryDto?
}
