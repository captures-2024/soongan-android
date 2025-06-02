package com.captures2024.soongan.presentation.feature.main.home.component.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.presentation.designsystem.ui.component.WeightSpacer
import com.captures2024.soongan.presentation.designsystem.ui.component.text.SGText
import com.captures2024.soongan.presentation.designsystem.ui.component.text.getSGNonScaleTextStyle
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGColor
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGTheme
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGTypography
import com.captures2024.soongan.presentation.designsystem.ui.util.DevicePreviews
import com.captures2024.soongan.core.model.dto.HomeContestInfoDto
import com.captures2024.soongan.core.model.dto.PostInfoDto
import com.captures2024.soongan.presentation.feature.main.home.R
import com.captures2024.soongan.presentation.viewmodel.main.home.HomeViewModel

@Composable
internal fun ColumnScope.HomeBodyComponent(
    state: HomeViewModel.State,
    modifier: Modifier = Modifier,
    onClickRegister: () -> Unit,
    onClickPost: (PostInfoDto) -> Unit,
) {
    val isEmptyState = state.postInfoList.isEmpty()

    Column(
        modifier = modifier,
        horizontalAlignment = when (isEmptyState) {
            true -> Alignment.CenterHorizontally
            false -> Alignment.Start
        },
    ) {
        when (isEmptyState) {
            true -> HomeBodyEmptyComponent(
                onClick = onClickRegister,
            )

            false -> HomeBodyNotEmptyComponent(
                maxCount = state.maxRegisterPostCount,
                postInfoList = state.postInfoList,
                onClickRegister = onClickRegister,
                onClickPost = onClickPost,
            )
        }

        WeightSpacer(1f)

        ContentDeadlineComponent(
            startAt = state.contestInfo.startAt,
            endAt = state.contestInfo.endAt,
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
                fontFamily = SGTypography.poppins,
                letterSpacing = 0.em,
            ),
        )
    }
}

@DevicePreviews
@Composable
private fun PreviewHomeBodyComponent() {
    SGTheme {
        Column {
            HomeBodyComponent(
                state = HomeViewModel.State(
                    initState = HomeViewModel.State.InitState.SUCCESS,
                    contestInfo = HomeContestInfoDto(
                        contestType = "",
                        subject = "",
                        startAt = "",
                        endAt = "",
                    ),
                    postInfoList = emptyList(),
                    maxRegisterPostCount = 3,
                    isShowContestInfoBottomSheet = false,
                ),
                onClickRegister = {},
                onClickPost = {},
            )
        }
    }
}
