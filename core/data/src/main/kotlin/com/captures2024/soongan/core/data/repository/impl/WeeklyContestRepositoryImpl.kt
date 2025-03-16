package com.captures2024.soongan.core.data.repository.impl

import com.captures2024.soongan.core.data.remote.WeeklyContestDataSource
import com.captures2024.soongan.core.data.repository.WeeklyContestRepository
import com.captures2024.soongan.core.model.dto.GalleryDto
import com.captures2024.soongan.core.model.dto.MyGalleryDto
import com.captures2024.soongan.core.model.dto.PostInfoDto
import javax.inject.Inject

class WeeklyContestRepositoryImpl
@Inject
constructor(
    private val weeklyContestDataSource: WeeklyContestDataSource,
) : WeeklyContestRepository {

    override suspend fun getGalleryInfo(
        round: Int?,
        orderType: String,
        page: Int,
        pageSize: Int,
    ): GalleryDto {
        val galleryInfo = weeklyContestDataSource.getGalleryInfo(
            round = round,
            orderType = orderType,
            page = page,
            pageSize = pageSize,
        )

        return galleryInfo ?: throw java.lang.NullPointerException("galleryDto is null")
    }

    override suspend fun registerPost(
        title: String,
        imageFile: String,
    ): PostInfoDto {
        val postInfoDto = weeklyContestDataSource.registerPost(
            title = title,
            imageFile = imageFile,
        )

        return postInfoDto ?: throw NullPointerException("postInfoDto is null")
    }

    override suspend fun getPostInfo(postId: Long): PostInfoDto {
        val postInfoDto = weeklyContestDataSource.getPostInfo(
            postId = postId,
        )

        return postInfoDto ?: throw java.lang.NullPointerException("postInfoDto is null")
    }

    override suspend fun deletePost(postId: Long): Boolean =
        weeklyContestDataSource.deletePost(postId = postId)

    override suspend fun editPostTitle(postId: Long, title: String): String {
        val editedTitle = weeklyContestDataSource.editPostTitle(
            postId = postId,
            title = title,
        )

        return editedTitle ?: throw java.lang.NullPointerException("editedTitle is null")
    }

    override suspend fun getMyGalleryInfo(page: Int, pageSize: Int): MyGalleryDto {
        val myGalleryInfo = weeklyContestDataSource.getMyGalleryInfo(
            page = page,
            pageSize = pageSize,
        )

        return myGalleryInfo ?: throw java.lang.NullPointerException("myGalleryDto is null")
    }
}
