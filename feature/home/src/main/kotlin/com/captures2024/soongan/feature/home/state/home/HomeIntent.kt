package com.captures2024.soongan.feature.home.state.home

import com.captures2024.soongan.core.common.base.UIIntent
import com.captures2024.soongan.core.model.UserPost
import com.captures2024.soongan.core.model.dto.PostInfoDto

internal sealed interface HomeIntent : UIIntent {

    data object Init : HomeIntent

    data object OnClickPlus : HomeIntent

    data class OnClickPost(
        val postInfo: PostInfoDto,
    ) : HomeIntent

    data object OnToggleWeeklyDaily : HomeIntent

    data object OnClickInfo : HomeIntent

    data object OnCloseBottomSheet : HomeIntent

    data object OnClickRightArrow : HomeIntent
}
