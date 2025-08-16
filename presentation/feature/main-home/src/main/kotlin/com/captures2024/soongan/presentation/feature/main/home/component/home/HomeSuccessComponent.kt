package com.captures2024.soongan.presentation.feature.main.home.component.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.core.model.dto.HomeContestInfoDto
import com.captures2024.soongan.core.model.dto.PostInfoDto
import com.captures2024.soongan.presentation.designsystem.ui.component.WeightSpacer
import com.captures2024.soongan.presentation.designsystem.ui.component.text.SGText
import com.captures2024.soongan.presentation.designsystem.ui.component.text.getSGNonScaleTextStyle
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGColor
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGTheme
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGTypography
import com.captures2024.soongan.presentation.designsystem.ui.util.DevicePreviews
import com.captures2024.soongan.presentation.feature.main.home.R
import com.captures2024.soongan.presentation.viewmodel.main.home.HomeViewModel

@Composable
internal fun HomeSuccessComponent(
    isLoading: Boolean,
    homeInfo: HomeViewModel.State.HomeInfo,
    modifier: Modifier = Modifier,
    onClickRegister: () -> Unit,
    onClickPost: (PostInfoDto) -> Unit,
    onClickPostList: () -> Unit,
    onClickContestInfo: () -> Unit,
) {
    Column(
        modifier = modifier
            .padding(
                vertical = 40.dp,
                horizontal = 16.dp,
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(32.dp),
    ) {
        HomeTopBarComponent(
            subject = homeInfo.homeContestInfo?.subject ?: "",
        )

        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = when (homeInfo.postInfos?.isEmpty()) {
                null, true -> Alignment.CenterHorizontally
                false -> Alignment.Start
            },
        ) {
            when (homeInfo.postInfos?.isEmpty()) {
                null, true -> HomeBodyEmptyComponent(
                    isLoading = isLoading,
                    onClick = onClickRegister,
                )

                false -> HomeBodyNotEmptyComponent(
                    isLoading = isLoading,
                    maxCount = homeInfo.maxRegisterPostCount,
                    postInfoList = homeInfo.postInfos ?: emptyList(),
                    onClickRegister = onClickRegister,
                    onClickPost = onClickPost,
                )
            }

            WeightSpacer(1f)

            ContentDeadlineComponent(
                startAt = homeInfo.homeContestInfo?.startAt ?: "",
                endAt = homeInfo.homeContestInfo?.endAt ?: ""
            )
        }

        HomeBottomBarComponent(
            isLoading = isLoading,
            onClickContestInfo = onClickContestInfo,
            onClickPostList = onClickPostList,
        )
    }
}

@Composable
private fun ContentDeadlineComponent(
    startAt: String,
    endAt: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ContestDateComponent(
            text = stringResource(R.string.start_date_title),
            date = startAt,
        )
        ContestDateComponent(
            text = stringResource(R.string.end_date_title),
            date = endAt,
        )
    }
}

@Composable
private fun ContestDateComponent(
    text: String,
    date: String,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        SGText(
            text = text,
            style = getSGNonScaleTextStyle(
                color = SGColor.Grayscale.black100,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 14.sp,
                fontFamily = SGTypography.pretendard,
                letterSpacing = 0.em,
            ),
        )

        SGText(
            text = "|",
            style = getSGNonScaleTextStyle(
                color = SGColor.Grayscale.black100,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                lineHeight = 14.sp,
                fontFamily = SGTypography.pretendard,
                letterSpacing = 0.em,
            ),
        )

        SGText(
            text = date,
            style = getSGNonScaleTextStyle(
                color = SGColor.Grayscale.black100,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                lineHeight = 14.sp,
                fontFamily = SGTypography.pretendard,
                letterSpacing = 0.em,
            ),
        )
    }
}

@DevicePreviews
@Composable
private fun PreviewHomeSuccessComponent_Empty() {
    SGTheme {
        HomeSuccessComponent(
            isLoading = false,
            homeInfo = HomeViewModel.State.HomeInfo(
                homeContestInfo = HomeContestInfoDto(
                    contestType = "weekly",
                    subject = "평화",
                    startAt = "2025.05.16",
                    endAt = "2025.05.31",
                ),
                postInfos = emptyList(),
            ),
            onClickRegister = {},
            onClickPost = {},
            onClickPostList = {},
            onClickContestInfo = {},
        )
    }
}

@DevicePreviews
@Composable
private fun PreviewHomeSuccessComponent_NotEmpty() {
    SGTheme {
        HomeSuccessComponent(
            isLoading = false,
            homeInfo = HomeViewModel.State.HomeInfo(
                homeContestInfo = HomeContestInfoDto(
                    contestType = "weekly",
                    subject = "평화",
                    startAt = "2025.05.16",
                    endAt = "2025.05.31",
                ),
                postInfos = listOf(
                    PostInfoDto(),
                ),
            ),
            onClickRegister = {},
            onClickPost = {},
            onClickPostList = {},
            onClickContestInfo = {},
        )
    }
}

@DevicePreviews
@Composable
private fun PreviewHomeSuccessComponent_Loading() {
    SGTheme {
        HomeSuccessComponent(
            isLoading = true,
            homeInfo = HomeViewModel.State.HomeInfo(
                homeContestInfo = HomeContestInfoDto(
                    contestType = "weekly",
                    subject = "평화",
                    startAt = "2025.05.16",
                    endAt = "2025.05.31",
                ),
                postInfos = listOf(
                    PostInfoDto(),
                ),
            ),
            onClickRegister = {},
            onClickPost = {},
            onClickPostList = {},
            onClickContestInfo = {},
        )
    }
}
