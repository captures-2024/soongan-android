package com.captures2024.soongan.feature.home.state.home

import com.captures2024.soongan.core.analytics.utils.LogElementArgument
import com.captures2024.soongan.core.common.base.UIState
import com.captures2024.soongan.core.model.UserPost
import com.captures2024.soongan.core.model.dto.ContestInfoDto
import com.captures2024.soongan.core.model.dto.PostInfoDto
import com.captures2024.soongan.feature.home.utils.GalleryPhotoSortFilter

internal data class HomeUIState(
    val isLoading: Boolean = false,
    val contestInfo: ContestInfoDto = ContestInfoDto(),
    val postList: List<PostInfoDto> = emptyList(),
    val isWeeklySelected: Boolean = true,
    val isOpenBottomSheet: Boolean = false,
) : UIState {

    override fun toLoggingElements(): Array<LogElementArgument> = arrayOf(
        LogElementArgument("isLoading", isLoading.toString()),
        LogElementArgument("contestInfo", contestInfo.toString()),
        LogElementArgument("postList", postList.toString()),
        LogElementArgument("isWeeklySelected", isWeeklySelected.toString()),
        LogElementArgument("isOpenBottomSheet", isOpenBottomSheet.toString()),
    )
}
