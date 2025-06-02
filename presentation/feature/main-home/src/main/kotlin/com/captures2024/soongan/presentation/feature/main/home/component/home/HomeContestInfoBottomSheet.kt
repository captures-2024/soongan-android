package com.captures2024.soongan.presentation.feature.main.home.component.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.presentation.designsystem.ui.component.HeightSpacer
import com.captures2024.soongan.presentation.designsystem.ui.component.button.SGTextButtonType2
import com.captures2024.soongan.presentation.designsystem.ui.component.text.SGText
import com.captures2024.soongan.presentation.designsystem.ui.component.text.getSGNonScaleTextStyle
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGColor
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGTheme
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGTypography
import com.captures2024.soongan.presentation.designsystem.ui.util.DevicePreviews
import com.captures2024.soongan.presentation.feature.main.home.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun HomeContestInfoBottomSheet(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
) {
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true,
    )

    ModalBottomSheet(
        modifier = modifier,
        onDismissRequest = onDismissRequest,
        sheetState = sheetState,
        containerColor = SGColor.Grayscale.white,
        content = @Composable {
            HomeContestInfoComponent(
                onDismissRequest = onDismissRequest,
            )
        },
    )
}

@Composable
private fun ColumnScope.HomeContestInfoComponent(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
) {
    val titleStyle = getSGNonScaleTextStyle(
        color = SGColor.Grayscale.black100,
        fontSize = 16.sp,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 24.sp,
        fontFamily = SGTypography.pretendard,
        letterSpacing = 0.em,
    )

    val contentStyle = getSGNonScaleTextStyle(
        color = SGColor.Grayscale.black100,
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 24.sp,
        fontFamily = SGTypography.pretendard,
        letterSpacing = 0.em,
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        SGText(
            text = stringResource(R.string.contest_info_bs_title),
            style = getSGNonScaleTextStyle(
                color = SGColor.Grayscale.black100,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 20.sp,
                fontFamily = SGTypography.pretendard,
                letterSpacing = (-5).em,
            ),
        )

        HeightSpacer(12.dp)

        HorizontalDivider(color = SGColor.primaryA.copy(alpha = 0.3f))

        Column(
            modifier = Modifier.fillMaxWidth()
                .wrapContentHeight()
                .padding(
                    top = 40.dp,
                    bottom = 28.dp,
                )
                .padding(horizontal = 20.dp),
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
                    .wrapContentHeight()
                    .padding(horizontal = 18.dp),
            ) {
                SGText(
                    text = stringResource(R.string.contest_info_bs_title_1),
                    style = titleStyle,
                )

                HeightSpacer(4.dp)

                SGText(
                    text = stringResource(R.string.contest_info_bs_content_1),
                    style = contentStyle,
                    modifier = Modifier.padding(start = 16.dp),
                )

                HeightSpacer(10.dp)

                SGText(
                    text = stringResource(R.string.contest_info_bs_title_2),
                    style = titleStyle,
                )

                HeightSpacer(4.dp)

                SGText(
                    text = stringResource(R.string.contest_info_bs_content_2_by_1),
                    style = contentStyle,
                    modifier = Modifier.padding(start = 16.dp),
                )

                HeightSpacer(4.dp)

                SGText(
                    text = stringResource(R.string.contest_info_bs_content_2_by_2),
                    style = contentStyle,
                    modifier = Modifier.padding(start = 16.dp),
                )

                HeightSpacer(4.dp)

                SGText(
                    text = stringResource(R.string.contest_info_bs_content_2_by_3),
                    style = contentStyle,
                    modifier = Modifier.padding(start = 16.dp),
                )

                HeightSpacer(8.dp)

                SGText(
                    text = stringResource(R.string.contest_info_bs_title_3),
                    style = titleStyle,
                )

                HeightSpacer(4.dp)

                SGText(
                    text = stringResource(R.string.contest_info_bs_content_3_by_1),
                    style = contentStyle,
                    modifier = Modifier.padding(start = 16.dp),
                )

                HeightSpacer(4.dp)

                SGText(
                    text = stringResource(R.string.contest_info_bs_content_3_by_2),
                    style = contentStyle,
                    modifier = Modifier.padding(start = 16.dp),
                )

                HeightSpacer(8.dp)

                SGText(
                    text = stringResource(R.string.contest_info_bs_title_4),
                    style = titleStyle,
                )

                HeightSpacer(4.dp)

                SGText(
                    text = stringResource(R.string.contest_info_bs_content_4_by_1),
                    style = contentStyle,
                    modifier = Modifier.padding(start = 16.dp),
                )

                HeightSpacer(4.dp)

                SGText(
                    text = stringResource(R.string.contest_info_bs_content_4_by_2),
                    style = contentStyle,
                    modifier = Modifier.padding(start = 16.dp),
                )
            }

            HeightSpacer(44.dp)

            SGTextButtonType2(
                text = stringResource(R.string.contest_info_bs_button_content),
                modifier = Modifier.fillMaxWidth(),
                onClick = onDismissRequest,
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@DevicePreviews
@Composable
private fun PreviewHomeContestInfoBottomSheet() {
    SGTheme {
        HomeContestInfoBottomSheet(
            onDismissRequest = {},
        )
    }
}

@DevicePreviews
@Composable
private fun PreviewHomeContestInfoComponent() {
    SGTheme {
        Column {
            HomeContestInfoComponent(
                onDismissRequest = {},
            )
        }
    }
}
