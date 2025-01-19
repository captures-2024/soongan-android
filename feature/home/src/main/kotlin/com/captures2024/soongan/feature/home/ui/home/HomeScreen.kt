package com.captures2024.soongan.feature.home.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.captures2024.soongan.core.design.R
import com.captures2024.soongan.core.designsystem.component.HeightSpacer
import com.captures2024.soongan.core.designsystem.component.WeightSpacer
import com.captures2024.soongan.core.designsystem.theme.SGColor
import com.captures2024.soongan.core.designsystem.theme.dropShadow
import com.captures2024.soongan.core.designsystem.util.DevicePreviews
import com.captures2024.soongan.core.model.dto.ContestInfoDto
import com.captures2024.soongan.core.model.dto.PostInfoDto
import com.captures2024.soongan.core.viewmodel.home.HomeViewModel
import com.captures2024.soongan.feature.home.ui.home.component.ContestPeriodText
import com.captures2024.soongan.feature.home.ui.home.component.HomePeriodToggleButton

@Composable
internal fun HomeScreen(
    uiState: HomeViewModel.State,
    modifier: Modifier = Modifier,
    onClickPlus: () -> Unit = {},
    onClickPost: (PostInfoDto) -> Unit = {},
    onToggleWeeklyDaily: () -> Unit = {},
    onClickInfo: () -> Unit = {},
    onClickRightArrow: () -> Unit = {},
) {
    Column(
        modifier = modifier
            .padding(top = 88.dp, bottom = 40.dp)
            .padding(horizontal = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HomeScreenTopBar(
            subject = uiState.contestInfo.subject,
        )
        WeightSpacer(1f)
        HomeScreenBody(
            postList = uiState.postList,
            onClickPlus = onClickPlus,
            onClickPost = onClickPost,
        )
        HeightSpacer(64.dp)
//        HomeScreenToggle(
//            modifier = Modifier.align(Alignment.End),
//            onClick = onToggleWeeklyDaily,
//            isWeeklySelected = uiState.isWeeklySelected,
//        )
        HomeScreenDeadLine(
            startAt = uiState.contestInfo.startAt,
            endAt = uiState.contestInfo.endAt,
            modifier = Modifier.align(Alignment.End),
        )
        WeightSpacer(1f)
        HomeScreenFooter(
            onClickInfo = onClickInfo,
            onClickRightArrow = onClickRightArrow
        )
    }
}

@Composable
private fun HomeScreenToggle(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    isWeeklySelected: Boolean,
) {
    Row(
        modifier = modifier
            .dropShadow(shape = RectangleShape)
            .background(
                color = SGColor.white,
                shape = RoundedCornerShape(4.dp),
            )
    ) {
        HomePeriodToggleButton(
            onClick = onClick,
            selected = isWeeklySelected,
        )
        HomePeriodToggleButton(
            onClick = onClick,
            selected = !isWeeklySelected
        )
    }
}

@Composable
private fun HomeScreenDeadLine(
    startAt: String,
    endAt: String,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        ContestPeriodText(
            text = stringResource(id = com.captures2024.soongan.feature.home.R.string.start_date),
            period = startAt,
        )
        ContestPeriodText(
            text = stringResource(id = com.captures2024.soongan.feature.home.R.string.end_date),
            period = endAt,
        )
    }
}

@DevicePreviews
@Composable
private fun HomeScreenPreview() {
    val modifier = Modifier
        .fillMaxSize()
        .paint(
            painter = painterResource(id = R.drawable.background_home_gallery),
            contentScale = ContentScale.Crop,
        )

    HomeScreen(
        modifier = modifier,
        uiState = HomeViewModel.State(
            contestInfo = ContestInfoDto(
                subject = stringResource(com.captures2024.soongan.feature.home.R.string.home_top_bar_topic_example),
                startAt = "2024.05.10",
                endAt = "2024.06.10",
            ),
        )
    )
}

@DevicePreviews
@Composable
private fun HomeScreenMultiPostPreview() {
    val modifier = Modifier
        .fillMaxSize()
        .paint(
            painter = painterResource(id = R.drawable.background_home_gallery),
            contentScale = ContentScale.Crop,
        )

    HomeScreen(
        modifier = modifier,
        uiState = HomeViewModel.State(
            contestInfo = ContestInfoDto(
                subject = stringResource(com.captures2024.soongan.feature.home.R.string.home_top_bar_topic_example),
                startAt = "2024.05.10",
                endAt = "2024.06.10",
            ),
            postList = emptyList(),
        )
    )
}