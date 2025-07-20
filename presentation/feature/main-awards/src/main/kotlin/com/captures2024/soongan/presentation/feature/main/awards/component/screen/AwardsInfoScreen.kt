package com.captures2024.soongan.presentation.feature.main.awards.component.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.captures2024.soongan.core.model.dto.awards.AwardsDetailDto
import com.captures2024.soongan.core.model.dto.awards.AwardsPostDto
import com.captures2024.soongan.core.model.enums.AwardsPostStatusType
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGColor
import com.captures2024.soongan.presentation.designsystem.ui.util.DevicePreviews
import com.captures2024.soongan.presentation.feature.main.awards.component.common.AwardsCommonFailComponent
import com.captures2024.soongan.presentation.feature.main.awards.component.common.AwardsCommonInitComponent
import com.captures2024.soongan.presentation.feature.main.awards.component.info.AwardsInfoComponent
import com.captures2024.soongan.presentation.viewmodel.main.award.AwardsInfoViewModel

@Composable
internal fun AwardsInfoScreen(
    state: AwardsInfoViewModel.State,
    modifier: Modifier = Modifier,
    onClickBack: () -> Unit,
    onClickPost: (postId: Long) -> Unit,
    onClickAllPosts: () -> Unit,
    onClickRetry: () -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = SGColor.BG.background),
        contentAlignment = Alignment.Center,
    ) {
        when (state.initState) {
            AwardsInfoViewModel.State.InitState.INIT -> AwardsCommonInitComponent()

            AwardsInfoViewModel.State.InitState.SUCCESS -> when (val awardsInfo = state.awardsInfo) {
                null -> AwardsCommonFailComponent(
                    onClickRetry = onClickRetry,
                )

                else -> AwardsInfoComponent(
                    awardsInfo = awardsInfo,
                    onClickBack = onClickBack,
                    onClickPost = onClickPost,
                    onClickAllPosts = onClickAllPosts,
                )
            }

            AwardsInfoViewModel.State.InitState.FAIL -> AwardsCommonFailComponent(
                onClickRetry = onClickRetry,
            )
        }
    }
}

@DevicePreviews
@Composable
private fun AwardsInfoScreen_Preview() {
    AwardsInfoScreen(
        state = AwardsInfoViewModel.State(
            roundId = 0L,
            initState = AwardsInfoViewModel.State.InitState.SUCCESS,
            awardsInfo = AwardsDetailDto(
                subject = "subject",
                round = 1L,
                startAt = "startAt",
                endAt = "endAt",
                postsCount = 30L,
                prizePosts = List(7) { index ->
                    when (index) {
                        0 -> AwardsPostDto(
                            postId = 0,
                            title = "title_0",
                            imageUrl = "",
                            nickname = "nickname_0",
                            score = "0",
                            status = AwardsPostStatusType.ACTIVE,
                        )

                        else -> {
                            AwardsPostDto(
                                postId = index.toLong(),
                                title = "title_$index",
                                imageUrl = "",
                                nickname = "nickname_$index",
                                score = "$index",
                                status = AwardsPostStatusType.ACTIVE,
                            )
                        }
                    }
                },
            ),
        ),
        onClickBack = {},
        onClickPost = {},
        onClickAllPosts = {},
        onClickRetry = {},
    )
}
