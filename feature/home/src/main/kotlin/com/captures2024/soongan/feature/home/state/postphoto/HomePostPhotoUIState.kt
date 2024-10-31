package com.captures2024.soongan.feature.home.state.postphoto

import com.captures2024.soongan.core.analytics.utils.LogElementArgument
import com.captures2024.soongan.core.common.base.UIState

internal data class HomePostPhotoUIState(
    val isLoading: Boolean = false,
    val url: String,
    val isShowShimmer: Boolean = true,
) : UIState {

    override fun toLoggingElements(): Array<LogElementArgument> = arrayOf(
        LogElementArgument("isLoading", isLoading.toString()),
        LogElementArgument("url", url),
        LogElementArgument("isShowShimmer", isShowShimmer.toString()),
    )
}
