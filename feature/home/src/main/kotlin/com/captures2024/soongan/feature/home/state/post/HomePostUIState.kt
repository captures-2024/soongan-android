package com.captures2024.soongan.feature.home.state.post

import com.captures2024.soongan.core.common.base.UIState
import com.captures2024.soongan.core.model.UserPost
import com.captures2024.soongan.feature.home.utils.HomePostBottomModal

internal data class HomePostUIState(
    val isLoading: Boolean = false,
    val post: UserPost.PhotoPost,
    val isOpenModal: HomePostBottomModal = HomePostBottomModal.CLOSED,
    val inWritingComment: String = "",

) : UIState
