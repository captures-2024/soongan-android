package com.captures2024.soongan.presentation.feature.main.awards.component.info

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.captures2024.soongan.core.model.dto.awards.AwardsPostDto
import com.captures2024.soongan.core.model.enums.AwardsPostStatusType
import com.captures2024.soongan.presentation.designsystem.ui.util.DevicePreviews
import com.captures2024.soongan.presentation.feature.main.awards.R

@Composable
internal fun AwardsInfoTopPostComponent(
    topPost: AwardsPostDto,
    modifier: Modifier = Modifier,
    isWinnerPost: Boolean = false,
    onClickPost: (postId: Long) -> Unit,
) {
    when (topPost.status) {
        AwardsPostStatusType.ACTIVE -> AwardsInfoActiveTopPostComponent(
            topPost = topPost,
            modifier = modifier,
            isWinnerPost = isWinnerPost,
            onClickPost = onClickPost,
        )

        AwardsPostStatusType.BLINDED -> AwardsInfoHiddenTopPostComponent(
            message = when (isWinnerPost) {
                true -> stringResource(R.string.awards_info_top_post_component_winner_blinded_message)
                false -> stringResource(R.string.awards_info_top_post_component_blinded_message)
            },
        )

        AwardsPostStatusType.DELETED_BY_ADMIN -> AwardsInfoHiddenTopPostComponent(
            message = when (isWinnerPost) {
                true -> stringResource(R.string.awards_info_top_post_component_winner_deleted_by_admin_message)
                false -> stringResource(R.string.awards_info_top_post_component_deleted_by_admin_message)
            },
        )

        AwardsPostStatusType.DELETED_BY_CREATOR -> AwardsInfoHiddenTopPostComponent(
            message = when (isWinnerPost) {
                true -> stringResource(R.string.awards_info_top_post_component_winner_deleted_by_creator_message)
                false -> stringResource(R.string.awards_info_top_post_component_deleted_by_creator_message)
            },
        )
    }
}

@DevicePreviews
@Composable
private fun PreviewAwardsInfoTopPostComponent_Active() {
    AwardsInfoTopPostComponent(
        topPost = AwardsPostDto(
            postId = 0,
            title = "title_0",
            imageUrl = "",
            nickname = "nickname_0",
            score = "0",
            status = AwardsPostStatusType.ACTIVE,
        ),
        onClickPost = {},
    )
}

@DevicePreviews
@Composable
private fun PreviewAwardsInfoTopPostComponent_Blinded() {
    AwardsInfoTopPostComponent(
        topPost = AwardsPostDto(
            postId = 0,
            title = "title_0",
            imageUrl = "",
            nickname = "nickname_0",
            score = "0",
            status = AwardsPostStatusType.BLINDED,
        ),
        onClickPost = {},
    )
}

@DevicePreviews
@Composable
private fun PreviewAwardsInfoTopPostComponent_DeletedByAdmin() {
    AwardsInfoTopPostComponent(
        topPost = AwardsPostDto(
            postId = 0,
            title = "title_0",
            imageUrl = "",
            nickname = "nickname_0",
            score = "0",
            status = AwardsPostStatusType.DELETED_BY_ADMIN,
        ),
        onClickPost = {},
    )
}

@DevicePreviews
@Composable
private fun PreviewAwardsInfoTopPostComponent_DeletedByCeator() {
    AwardsInfoTopPostComponent(
        topPost = AwardsPostDto(
            postId = 0,
            title = "title_0",
            imageUrl = "",
            nickname = "nickname_0",
            score = "0",
            status = AwardsPostStatusType.DELETED_BY_CREATOR,
        ),
        onClickPost = {},
    )
}
