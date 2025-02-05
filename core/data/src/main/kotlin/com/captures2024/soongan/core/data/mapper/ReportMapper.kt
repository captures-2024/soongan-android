package com.captures2024.soongan.core.data.mapper

import com.captures2024.soongan.core.model.dto.ReportInfoDto
import com.captures2024.soongan.core.model.network.response.report.PostReportResponse

fun PostReportResponse.toReportInfoDto(): ReportInfoDto = ReportInfoDto(
    id = id,
    reportMemberId = reportMemberId,
    targetMemberId = targetMemberId,
    targetId = targetId,
    targetType = targetType,
    reportType = reportType,
    reason = reason
)