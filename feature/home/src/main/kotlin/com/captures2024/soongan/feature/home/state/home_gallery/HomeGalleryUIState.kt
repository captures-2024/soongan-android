package com.captures2024.soongan.feature.home.state.home_gallery

import com.captures2024.soongan.core.analytics.utils.LogElementArgument
import com.captures2024.soongan.core.common.base.UIState
import com.captures2024.soongan.core.model.dto.GalleryPostDto
import com.captures2024.soongan.feature.home.utils.PaginationStatus
import com.captures2024.soongan.feature.home.utils.PostOrderType

internal data class HomeGalleryUIState(
    val isLoading: Boolean = false,
    val isShowBottomSheet: Boolean = false,
    val isRefreshing: Boolean = false,
    val postOrderType: PostOrderType = PostOrderType.MOST_LIKED,
    val paginationStatus: PaginationStatus = PaginationStatus.INACTIVE,
    val posts: List<GalleryPostDto> = emptyList(),
    val nextPage: Int = 0,
    val hasNextPage: Boolean = false,
) : UIState {

    override fun toLoggingElements(): Array<LogElementArgument> = arrayOf(
        LogElementArgument("isLoading", isLoading.toString()),
        LogElementArgument("isShowBottomSheet", isShowBottomSheet.toString()),
        LogElementArgument("postOrderType", postOrderType.toString()),
        LogElementArgument("paginationStatus", paginationStatus.toString()),
        LogElementArgument("posts", posts.toString()),
    )
}
