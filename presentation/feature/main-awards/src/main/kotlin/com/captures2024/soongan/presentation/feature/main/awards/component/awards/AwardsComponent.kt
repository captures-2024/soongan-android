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
import com.captures2024.soongan.core.model.dto.awards.AwardsDefaultDto
import com.captures2024.soongan.presentation.designsystem.ui.util.DevicePreviews

@Composable
internal fun AwardsComponent(
    contestInfo: List<AwardsDefaultDto>,
    modifier: Modifier = Modifier,
    onClickContestSubject: (AwardsDefaultDto) -> Unit,
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
            key = { it.id },
        ) {
            AwardsRoundItemComponent(
                awards = it,
                onClickContestSubject = onClickContestSubject,
            )
        }
    }
}

@DevicePreviews
@Composable
private fun AwardsComponent_Preview() {
    AwardsComponent(
        contestInfo = listOf(
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
        onClickContestSubject = {},
    )
}
