package com.captures2024.soongan.feature.home.state.home

import com.captures2024.soongan.core.analytics.utils.LogElementArgument
import com.captures2024.soongan.core.common.base.UIState
import com.captures2024.soongan.core.model.UserPost
import com.captures2024.soongan.feature.home.utils.GalleryPhotoSortFilter

internal data class HomeUIState(
    val isLoading: Boolean = false,
    val myPosts: List<UserPost.PhotoPost> = emptyList(),
    val isWeeklySelected: Boolean = true,
    val contestDeadline: String = "",
    val isOpenBottomSheet: Boolean = false,
) : UIState {

    override fun toLoggingElements(): Array<LogElementArgument> = arrayOf(
        LogElementArgument("isLoading", isLoading.toString()),
        LogElementArgument("posts", myPosts.toString()),
        LogElementArgument("isWeeklySelected", isWeeklySelected.toString()),
        LogElementArgument("contestDeadline", contestDeadline.toString()),
        LogElementArgument("isOpenBottomSheet", isOpenBottomSheet.toString()),
    )
}
