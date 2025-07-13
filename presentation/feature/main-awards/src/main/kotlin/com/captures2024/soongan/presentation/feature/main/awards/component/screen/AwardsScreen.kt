package com.captures2024.soongan.presentation.feature.main.awards.component.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.captures2024.soongan.core.model.dto.awards.AwardsDefaultDto
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
    onClickContestSubject: (AwardsDefaultDto) -> Unit,
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

            AwardsViewModel.State.InitState.SUCCESS -> when (state.awardsList.isEmpty()) {
                true -> AwardsNoContestComponent()

                false -> AwardsComponent(
                    contestInfo = state.awardsList,
                    onClickContestSubject = onClickContestSubject,
                )
            }

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
            initState = AwardsViewModel.State.InitState.SUCCESS,
            awardsList = emptyList(),
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
            awardsList = listOf(
                AwardsDefaultDto(
                    id = 0L,
                    round = 0,
                    subject = "subject",
                    startAt = "startAt",
                    endAt = "endAt",
                    announcedAt = "announcedAt",
                    thumbnailImageUrl = "thumbnailImageUrl",
                ),
            ),
        ),
        onClickContestSubject = {},
        onClickRetry = {},
    )
}
