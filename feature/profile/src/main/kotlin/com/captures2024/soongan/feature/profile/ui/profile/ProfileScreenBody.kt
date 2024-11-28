package com.captures2024.soongan.feature.profile.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.core.designsystem.component.HeightSpacer
import com.captures2024.soongan.core.designsystem.util.DevicePreviews
import com.captures2024.soongan.core.model.UserPost
import com.captures2024.soongan.core.model.mock.samplePhotos
import com.captures2024.soongan.feature.profile.R
import com.captures2024.soongan.feature.profile.utils.nonScaleAnnotatedTitle

@Composable
internal fun ProfileScreenBody(
    modifier: Modifier = Modifier,
    myPhotos: List<UserPost.PhotoPost>,
) {
    val title = nonScaleAnnotatedTitle(
        title = stringResource(R.string.profile_tab_gallery_title),
        titleFontSize = 14.sp,
        count = myPhotos.size,
        countFontSize = 10.sp
    )

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(title)
        HeightSpacer(8.dp)
        HorizontalDivider(
            modifier = Modifier
                .width(120.dp), color = Color.Black,
            thickness = 2.dp
        )
        ProfileGallery(myPhotos = myPhotos)
    }
}

/*
기존 TapRow 1차 MVP 제거

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun _ProfileTapRow(
    modifier: Modifier = Modifier,
    myPhotos: List<UserPost.PhotoPost>,
    participationHistory: List<String>,
) {
    val tabs = listOf("갤러리", "참가내역")
    val pagerState = rememberPagerState(
        pageCount = { tabs.size },
        initialPageOffsetFraction = 0f,
        initialPage = 0,
    )
    val tabIndex = pagerState.currentPage
    val scope = rememberCoroutineScope()

    SecondaryTabRow(
        selectedTabIndex = tabIndex,
        modifier = modifier,
        containerColor = PrimaryB,
        indicator = {
            TabRowDefaults.SecondaryIndicator(
                Modifier
                    .tabIndicatorOffset(tabIndex, matchContentSize = false)
                    .padding(horizontal = 44.dp),
                height = 2.dp,
                color = Color.Black
            )
        },
        divider = { HorizontalDivider(color = Color.Transparent) }
    ) {
        tabs.forEachIndexed { index, tab ->
            Tab(
                selected = tabIndex == index,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
                text = {
                    NonScaleText(
                        text = tab,
                        fontSize = 14.sp,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            )
        }
    }
    HorizontalPager(
        state = pagerState
    ) {
        when(tabIndex) {
            0 -> ProfileGallery(myPhotos = myPhotos)
            1 -> ProfileParticipationHistory(participationHistory = participationHistory)
        }
    }
}

*/

@DevicePreviews
@Composable
private fun ProfileScreenBodyPreview() {
    val samples = samplePhotos.map { it as UserPost.PhotoPost }

    Box(modifier = Modifier.background(color = Color.White)) {
        ProfileScreenBody(myPhotos = samples)
    }
}