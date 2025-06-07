package com.captures2024.soongan.presentation.feature.main.awards.component.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGColor
import com.captures2024.soongan.presentation.designsystem.ui.util.DevicePreviews
import com.captures2024.soongan.presentation.feature.main.awards.component.common.AwardsCommonFailComponent
import com.captures2024.soongan.presentation.feature.main.awards.component.common.AwardsCommonInitComponent
import com.captures2024.soongan.presentation.feature.main.awards.component.info.AwardsInfoComponent
import com.captures2024.soongan.presentation.viewmodel.main.award.AwardsInfoViewModel
import com.captures2024.soongan.presentation.viewmodel.main.award.ContestInfo
import com.captures2024.soongan.presentation.viewmodel.main.award.TopPost
import com.captures2024.soongan.presentation.viewmodel.main.award.WinnerPost

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
        contentAlignment = Alignment.Center
    ) {
        when (state.initState) {
            AwardsInfoViewModel.State.InitState.INIT -> AwardsCommonInitComponent()

            AwardsInfoViewModel.State.InitState.SUCCESS -> AwardsInfoComponent(
                state = state,
                onClickBack = onClickBack,
                onClickPost = onClickPost,
                onClickAllPosts = onClickAllPosts,
            )

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
            initState = AwardsInfoViewModel.State.InitState.SUCCESS,
            winnerPost = WinnerPost(),
            contestInfo = ContestInfo(),
            topPosts = List(6) { TopPost() },
        ),
        onClickBack = {},
        onClickPost = {},
        onClickAllPosts = {},
        onClickRetry = {},
    )
}
