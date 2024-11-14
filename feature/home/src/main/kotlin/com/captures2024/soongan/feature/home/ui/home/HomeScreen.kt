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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.captures2024.soongan.core.design.R
import com.captures2024.soongan.core.designsystem.component.HeightSpacer
import com.captures2024.soongan.core.designsystem.component.WeightSpacer
import com.captures2024.soongan.core.designsystem.theme.dropShadow
import com.captures2024.soongan.core.designsystem.util.DevicePreviews
import com.captures2024.soongan.core.model.UserPost
import com.captures2024.soongan.feature.home.state.home.HomeUIState
import com.captures2024.soongan.feature.home.ui.home.component.ContestPeriodText
import com.captures2024.soongan.feature.home.ui.home.component.HomePeriodToggleButton

@Composable
internal fun HomeScreen(
    modifier: Modifier = Modifier,
    uiState: HomeUIState,
    onClickPlus: () -> Unit = {},
    onClickMyPost: (UserPost.PhotoPost) -> Unit = {},
    onToggleWeeklyDaily: () -> Unit = {},
    onClickInfo: () -> Unit = {},
    onClickRightArrow: () -> Unit = {},
) {
    Column(
        modifier = modifier.padding(horizontal = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HomeScreenTopBar()
        WeightSpacer(1f)
        HomeScreenBody(
            onClickPlus = onClickPlus,
            onClickMyPost = onClickMyPost,
            myPosts = uiState.myPosts,
        )
        HeightSpacer(64.dp)
//        HomeScreenToggle(
//            modifier = Modifier.align(Alignment.End),
//            onClick = onToggleWeeklyDaily,
//            isWeeklySelected = uiState.isWeeklySelected,
//        )
        HomeScreenDeadLine(modifier = Modifier.align(Alignment.End))
        WeightSpacer(1f)
        HomeScreenFooter(
            modifier = Modifier.padding(bottom = 40.dp),
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
            .background(color = Color.White, shape = RoundedCornerShape(4.dp))
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
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        ContestPeriodText(stringResource(id = com.captures2024.soongan.feature.home.R.string.start_date), "2024.05.10")
        ContestPeriodText(stringResource(id = com.captures2024.soongan.feature.home.R.string.end_date), "2024.05.10")
    }
}

@DevicePreviews
@Composable
private fun HomeScreenPreview() {
    val modifier = Modifier
        .fillMaxSize()
        .paint(
            painter = painterResource(id = R.drawable.background_home_gallery),
            contentScale = ContentScale.Crop
        )
        .padding(top = 100.dp)

    HomeScreen(
        modifier = modifier,
        uiState = HomeUIState()
    )
}