package com.captures2024.soongan.domain.usecase.contest.impl

import com.captures2024.soongan.core.model.dto.GalleryDto
import com.captures2024.soongan.core.model.utils.ReportTargetType
import com.captures2024.soongan.domain.repository.contest.WeeklyContestRepository
import com.captures2024.soongan.domain.repository.member.MemberRepository
import com.captures2024.soongan.domain.usecase.contest.GetFilteredGalleryByReportTargetIdsUseCase
import com.captures2024.soongan.domain.usecase.utils.runSuspendCatching
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class GetFilteredGalleryByReportTargetIdsUseCaseImpl
@Inject
constructor(
    private val membersRepository: MemberRepository,
    private val weeklyContestRepository: WeeklyContestRepository,
) : GetFilteredGalleryByReportTargetIdsUseCase {

    override suspend fun invoke(
        round: Int?,
        orderType: String,
        page: Int,
        pageSize: Int,
    ): Result<GalleryDto> = runSuspendCatching {
        val galleryDto = weeklyContestRepository.getGalleryInfo(
            round = round,
            orderType = orderType,
            page = page,
            pageSize = pageSize,
        )

        val reportHistories = membersRepository.currentMember.first()?.reportHistories

        return@runSuspendCatching when (reportHistories) {
            null -> galleryDto

            else -> {
                val reportTargetIds = reportHistories
                    .filter { it.targetType == ReportTargetType.WEEKLY_POST.name }
                    .map { it.targetId }
                    .toSet()

                galleryDto.copy(
                    posts = galleryDto.posts.filter { it.postId !in reportTargetIds },
                )
            }
        }
    }
}
