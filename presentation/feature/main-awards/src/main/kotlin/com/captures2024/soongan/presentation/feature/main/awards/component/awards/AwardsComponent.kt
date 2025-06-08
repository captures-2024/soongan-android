package com.captures2024.soongan.presentation.feature.main.awards.component.awards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.captures2024.soongan.presentation.designsystem.ui.util.DevicePreviews
import com.captures2024.soongan.presentation.viewmodel.main.award.AwardsContestInfo

@Composable
internal fun AwardsComponent(
    contestInfo: List<AwardsContestInfo>,
    modifier: Modifier = Modifier,
    onClickContestSubject: (round: Int) -> Unit,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .padding(bottom = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        item {
            AwardsTopBarComponent()
        }

        items(
            items = contestInfo,
            key = { it.round },
        ) {
            AwardsRoundItemComponent(
                awardsContestInfo = it,
                onClickContestSubject = onClickContestSubject,
            )
        }
    }
}

@DevicePreviews
@Composable
private fun AwardsComponent_Preview() {
    AwardsComponent(
        contestInfo = emptyList(),
        onClickContestSubject = {},
    )
}
