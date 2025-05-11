package com.captures2024.soongan.core.domain.usecase.weekly.contests

import com.captures2024.soongan.core.data.repository.MembersRepository
import com.captures2024.soongan.core.data.repository.WeeklyContestRepository
import com.captures2024.soongan.core.domain.runSuspendCatching
import com.captures2024.soongan.core.model.dto.GalleryDto
import com.captures2024.soongan.core.model.utils.ReportTargetType
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class GetFilteredGalleryByReportTargetIdsUseCase
@Inject
constructor(
    private val membersRepository: MembersRepository,
    private val weeklyContestRepository: WeeklyContestRepository,
) {

    suspend operator fun invoke(params: Params): Result<GalleryDto> = runSuspendCatching {
        val galleryDto = weeklyContestRepository.getGalleryInfo(
            round = params.round,
            orderType = params.orderType,
            page = params.page,
            pageSize = params.pageSize,
        )

        val reportHistories = membersRepository.currentMember.first()?.reportHistories
            ?: error("current userInfo is null")

        val reportTargetIds = reportHistories
            .filter { it.targetType == ReportTargetType.WEEKLY_POST.name }
            .map { it.targetId }
            .toSet()

        return@runSuspendCatching galleryDto.copy(
            posts = galleryDto.posts.filter { it.postId !in reportTargetIds },
        )
    }

    data class Params(
        val round: Int?,
        val orderType: String,
        val page: Int,
        val pageSize: Int,
    )
}
