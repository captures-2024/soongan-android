package com.captures2024.soongan.presentation.feature.main.profile.component.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.captures2024.soongan.presentation.designsystem.icon.MyIconPack
import com.captures2024.soongan.presentation.designsystem.icon.myiconpack.IconNonFillBackArrow
import com.captures2024.soongan.presentation.designsystem.ui.component.button.SGIconButton
import com.captures2024.soongan.presentation.designsystem.ui.component.button.SGTextButtonType2
import com.captures2024.soongan.presentation.designsystem.ui.component.text.SGText
import com.captures2024.soongan.presentation.designsystem.ui.component.text.field.SGTextFieldTypeLong
import com.captures2024.soongan.presentation.designsystem.ui.component.text.getSGNonScaleSpanStyle
import com.captures2024.soongan.presentation.designsystem.ui.component.text.getSGNonScaleTextStyle
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGColor
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGTheme
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGTypography
import com.captures2024.soongan.presentation.designsystem.ui.util.DevicePreviews
import com.captures2024.soongan.presentation.feature.main.profile.R
import com.captures2024.soongan.presentation.viewmodel.main.profile.ExplainViewModel

@Composable
internal fun ExplainScreen(
    state: ExplainViewModel.State,
    modifier: Modifier = Modifier,
    onClickBack: () -> Unit,
    onExplainValueChange: (String) -> Unit,
    onClickReport: () -> Unit,
) {
    val scrollState = rememberScrollState()

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = @Composable {
            Column {
                Box(
                    modifier = modifier
                        .fillMaxWidth()
                        .background(color = SGColor.white)
                        .padding(horizontal = 20.dp, vertical = 16.dp),
                    contentAlignment = Alignment.CenterStart,
                ) {
                    SGIconButton(onClick = onClickBack) {
                        Icon(
                            imageVector = MyIconPack.IconNonFillBackArrow,
                            contentDescription = "back pressed",
                            modifier = Modifier.size(height = 20.dp, width = 16.dp),
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(24.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        SGText(
                            text = stringResource(R.string.explain_screen_title),
                            style = getSGNonScaleTextStyle(
                                color = SGColor.primaryA,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                lineHeight = 20.sp,
                                fontFamily = SGTypography.nanumSquareNeo,
                                letterSpacing = 0.em,
                            ),
                        )
                    }
                }

                HorizontalDivider(color = SGColor.buttonDisableGray)
            }
        },
        bottomBar = {
            Box(
                modifier = Modifier.padding(20.dp),
            ) {
                SGTextButtonType2(
                    text = stringResource(R.string.explain_screen_button_content),
                    onClick = onClickReport,
                    enabled = state.isEnabled,
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        },
        containerColor = SGColor.white,
    ) { paddingValues: PaddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(20.dp)
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(40.dp),
        ) {
            AsyncImage(
                model = state.postImageUrl,
                contentDescription = stringResource(R.string.image_description),
                modifier = Modifier
                    .size(
                        width = 114.dp,
                        height = 109.dp,
                    ),
                contentScale = ContentScale.Crop,
                placeholder = painterResource(com.captures2024.soongan.presentation.designsystem.ui.R.drawable.sign_logo),
                error = painterResource(com.captures2024.soongan.presentation.designsystem.ui.R.drawable.sign_logo),
            )

            SGText(
                annotatedString = buildAnnotatedString {
                    withStyle(
                        style = getSGNonScaleSpanStyle(
                            color = SGColor.primaryA,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                            fontFamily = SGTypography.pretendard,
                            letterSpacing = 0.em,
                        ),
                    ) {
                        append(state.postTitle)
                        append(stringResource(R.string.explain_screen_content_1))
                    }
                    withStyle(
                        style = getSGNonScaleSpanStyle(
                            color = SGColor.primaryA,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = SGTypography.pretendard,
                            letterSpacing = 0.em,
                        ),
                    ) {
                        append(stringResource(R.string.explain_screen_content_2))
                    }
                    withStyle(
                        style = getSGNonScaleSpanStyle(
                            color = SGColor.primaryA,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                            fontFamily = SGTypography.pretendard,
                            letterSpacing = 0.em,
                        ),
                    ) {
                        append(stringResource(R.string.explain_screen_content_3))
                        append(stringResource(R.string.explain_screen_content_4))
                        append(stringResource(R.string.explain_screen_content_5))
                        append(stringResource(R.string.explain_screen_content_6))
                        append(stringResource(R.string.explain_screen_content_7))
                    }
                    withStyle(
                        style = getSGNonScaleSpanStyle(
                            color = SGColor.primaryA,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                            fontFamily = SGTypography.pretendard,
                            letterSpacing = 0.em,
                            textDecoration = TextDecoration.Underline,
                        ),
                    ) {
                        append(stringResource(R.string.explain_screen_content_8))
                        append(stringResource(R.string.explain_screen_content_9))
                    }
                },
            )

            SGTextFieldTypeLong(
                value = state.explainContent,
                textStyle = getSGNonScaleTextStyle(
                    color = SGColor.primaryA,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    lineHeight = 20.sp,
                    fontFamily = SGTypography.pretendard,
                    letterSpacing = (-5).em,
                ),
                onValueChange = onExplainValueChange,
                hint = stringResource(R.string.explain_screen_hint),
                placeholderText = "${state.explainContent.length}/${state.maxExplainContentSize}",
                placeholderStyle = getSGNonScaleTextStyle(
                    color = SGColor.primaryA,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    lineHeight = 16.sp,
                    fontFamily = SGTypography.pretendard,
                    letterSpacing = 0.em,
                    textAlign = TextAlign.End,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
            )
        }
    }
}

@DevicePreviews
@Composable
private fun PreviewExplainScreen() {
    SGTheme {
        ExplainScreen(
            state = ExplainViewModel.State(
                postId = -1L,
            ),
            onClickBack = {},
            onExplainValueChange = {},
            onClickReport = {},
        )
    }
}
