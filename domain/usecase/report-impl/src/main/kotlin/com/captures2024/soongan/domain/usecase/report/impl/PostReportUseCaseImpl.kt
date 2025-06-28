package com.captures2024.soongan.domain.usecase.report.impl

import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.model.utils.ReportTargetType
import com.captures2024.soongan.core.model.utils.ReportType
import com.captures2024.soongan.domain.repository.member.MemberRepository
import com.captures2024.soongan.domain.repository.report.ReportRepository
import com.captures2024.soongan.domain.usecase.report.PostReportUseCase
import com.captures2024.soongan.domain.usecase.utils.runSuspendCatching
import javax.inject.Inject

class PostReportUseCaseImpl
@Inject
constructor(
    private val analyticsHelper: AnalyticsHelper,
    private val reportRepository: ReportRepository,
    private val memberRepository: MemberRepository,
) : PostReportUseCase {
    override suspend fun invoke(
        targetId: Long,
        targetType: ReportTargetType,
        reportType: ReportType,
        reason: String?,
    ): Result<Boolean> = runSuspendCatching {
        val reportInfoDto = reportRepository.postReport(
            targetId = targetId,
            targetType = targetType,
            reportType = reportType,
            reason = reason,
        )

        memberRepository.updateReportHistories(reportInfoDto.reportHistories)

        analyticsHelper.d { "currentMember reportHistories : ${memberRepository.currentMember.value?.reportHistories}" }

        return@runSuspendCatching true
    }
}
