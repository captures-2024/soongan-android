package com.captures2024.soongan.presentation.feature.main.awards.component.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGColor
import com.captures2024.soongan.presentation.designsystem.ui.util.DevicePreviews
import com.captures2024.soongan.presentation.feature.main.awards.component.awards.AwardsComponent
import com.captures2024.soongan.presentation.feature.main.awards.component.awards.AwardsNoContestComponent
import com.captures2024.soongan.presentation.feature.main.awards.component.common.AwardsCommonFailComponent
import com.captures2024.soongan.presentation.feature.main.awards.component.common.AwardsCommonInitComponent
import com.captures2024.soongan.presentation.viewmodel.main.award.AwardsViewModel

@Composable
internal fun AwardsScreen(
    state: AwardsViewModel.State,
    modifier: Modifier = Modifier,
    onClickContestSubject: (round: Int) -> Unit,
    onClickRetry: () -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = SGColor.BG.background),
        contentAlignment = Alignment.Center,
    ) {
        when (state.initState) {
            AwardsViewModel.State.InitState.INIT -> AwardsCommonInitComponent()

            AwardsViewModel.State.InitState.NO_CONTEST -> AwardsNoContestComponent()

            AwardsViewModel.State.InitState.SUCCESS -> AwardsComponent(
                contestInfo = state.awardsContestInfoList,
                onCLickContestSubject = onClickContestSubject,
            )

            AwardsViewModel.State.InitState.FAIL -> AwardsCommonFailComponent(
                onClickRetry = onClickRetry,
            )
        }
    }
}

@DevicePreviews
@Composable
private fun AwardsScreen_Preview_NO_CONTEST() {
    AwardsScreen(
        state = AwardsViewModel.State(
            initState = AwardsViewModel.State.InitState.NO_CONTEST,
            awardsContestInfoList = emptyList(),
        ),
        onClickContestSubject = {},
        onClickRetry = {},
    )
}

@DevicePreviews
@Composable
private fun AwardsScreen_Preview_SUCCESS() {
    AwardsScreen(
        state = AwardsViewModel.State(
            initState = AwardsViewModel.State.InitState.SUCCESS,
            awardsContestInfoList = emptyList(),
        ),
        onClickContestSubject = {},
        onClickRetry = {},
    )
}
