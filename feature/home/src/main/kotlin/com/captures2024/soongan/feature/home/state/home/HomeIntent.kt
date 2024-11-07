package com.captures2024.soongan.feature.home.state.home

import com.captures2024.soongan.core.common.base.UIIntent
import com.captures2024.soongan.core.model.UserPost

internal sealed interface HomeIntent : UIIntent {

    data object OnClickPlus : HomeIntent

    data class OnClickMyPost(val myPost: UserPost.PhotoPost) : HomeIntent

    data object OnToggleWeeklyDaily : HomeIntent

    data object OnClickInfo : HomeIntent

    data object OnClickRightArrow : HomeIntent
}
