package com.captures2024.soongan.feature.home.state.post

import com.captures2024.soongan.core.common.base.UIIntent

internal sealed interface HomePostIntent : UIIntent {

    data object OnClickPhoto : HomePostIntent

    data object OnClickMenu : HomePostIntent

    data object OnClickHeart : HomePostIntent

    data object OnClickComment : HomePostIntent

    data object OnClosedModal : HomePostIntent

    data class OnCommentValueChanged(
        val inWritingComment: String
    ) : HomePostIntent

}