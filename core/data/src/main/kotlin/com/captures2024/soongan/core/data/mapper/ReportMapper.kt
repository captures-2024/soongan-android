package com.captures2024.soongan.core.data.mapper

import com.captures2024.soongan.core.model.dto.ReportHistoryDto
import com.captures2024.soongan.core.model.dto.ReportInfoDto
import com.captures2024.soongan.core.model.network.response.report.PostReportResponse
import com.captures2024.soongan.core.model.network.response.report.ReportHistoryResponse

fun PostReportResponse.toReportInfoDto(): ReportInfoDto = ReportInfoDto(
    id = id,
    reportMemberId = reportMemberId,
    targetMemberId = targetMemberId,
    targetId = targetId,
    targetType = targetType,
    reportType = reportType,
    reason = reason,
    reportHistories = reportHistories.map { it.toReportHistoryDto() }
)

fun ReportHistoryResponse.toReportHistoryDto(): ReportHistoryDto = ReportHistoryDto(
    id = id,
    targetId = targetId,
    targetType = targetType,
)
