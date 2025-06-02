package com.captures2024.soongan.data.source.contest.impl.mapper

import com.captures2024.soongan.core.model.dto.WeeklyContestInfoListDto
import com.captures2024.soongan.core.model.network.response.weekly.contests.GetWeeklyContestInfoListResponse

internal fun GetWeeklyContestInfoListResponse.toWeeklyContestInfoListDto(): WeeklyContestInfoListDto = WeeklyContestInfoListDto(
    weeklyContestInfoList = this.weeklyContestInfoList.map { it.toWeeklyContestInfoDto() },
)
