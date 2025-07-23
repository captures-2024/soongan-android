package com.captures2024.soongan.presentation.feature.main.feed.component.feed

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.core.model.AppConst
import com.captures2024.soongan.presentation.designsystem.ui.component.HeightSpacer
import com.captures2024.soongan.presentation.designsystem.ui.component.WeightSpacer
import com.captures2024.soongan.presentation.designsystem.ui.component.text.SGText
import com.captures2024.soongan.presentation.designsystem.ui.component.text.getSGNonScaleTextStyle
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGColor
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGTypography
import com.captures2024.soongan.presentation.designsystem.ui.theme.innerShadow
import com.captures2024.soongan.presentation.designsystem.ui.util.DevicePreviews
import com.captures2024.soongan.presentation.feature.main.feed.R
import com.captures2024.soongan.presentation.viewmodel.model.TitleOption
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter

@Composable
internal fun FeedScrollTitlePickerComponent(
    selectedOption: TitleOption,
    options: List<TitleOption>,
    modifier: Modifier = Modifier,
    onChangedOption: (Int) -> Unit,
    onSelectOption: () -> Unit,
    onDismissRequest: () -> Unit,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        SGText(
            text = stringResource(R.string.feed_scroll_title_picker_header_text),
            modifier = Modifier
                .align(Alignment.Start)
                .padding(start = 26.dp, top = 27.dp),
            style = getSGNonScaleTextStyle(
                color = SGColor.Grayscale.black100,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                lineHeight = 20.sp,
                fontFamily = SGTypography.pretendard,
            ),
        )

        ScrollPicker(
            selectedOption = selectedOption,
            options = options,
            modifier = Modifier.padding(horizontal = 10.dp),
            onChangedOption = onChangedOption,
            onItemClick = { clickedIndex ->
                onChangedOption(clickedIndex)
            },
        )

        HeightSpacer(32.dp)

        Row(
            modifier = Modifier
                .padding(bottom = 61.dp)
                .padding(horizontal = 48.dp),
        ) {
            TempPickerButton(
                text = stringResource(R.string.feed_scroll_title_picker_dismiss_text),
                textColor = SGColor.black,
                containerColor = SGColor.transparent,
                borderColor = SGColor.black,
                onClick = onDismissRequest,
            )
            WeightSpacer(1f)
            TempPickerButton(
                text = stringResource(R.string.feed_scroll_title_picker_confirm_text),
                textColor = SGColor.Grayscale.white,
                containerColor = SGColor.Main.primary,
                borderColor = SGColor.Main.primary,
                onClick = onSelectOption,
            )
        }
    }
}

@Composable
private fun ScrollPicker(
    selectedOption: TitleOption,
    options: List<TitleOption>,
    modifier: Modifier = Modifier,
    visibleOptionCount: Int = 5,
    onChangedOption: (Int) -> Unit,
    onItemClick: ((Int) -> Unit)? = null,
) {
    val median = visibleOptionCount / 2
    val spaceOptions = List(median) { null }
    val adjustedOptions = spaceOptions + options + spaceOptions
    val listCount = adjustedOptions.size

    @Composable
    fun getTitleText(index: Int): String =
        adjustedOptions.getOrNull(index)?.let { (round, subject) ->
            "$round" + stringResource(R.string.scroll_picker_title_infix_text) + subject
        } ?: AppConst.EMPTY_STRING

    val listState = rememberLazyListState(
        initialFirstVisibleItemIndex = selectedOption.round - 1,
    )
    val flingBehavior = rememberSnapFlingBehavior(lazyListState = listState)

    val currentCenterIndex = remember {
        derivedStateOf {
            listState.firstVisibleItemIndex + median
        }
    }

    val fadingEdgeGradient = Brush.verticalGradient(
        0f to Color.Transparent,
        0.5f to Color.Black,
        1f to Color.Transparent,
    )

    val boxHeight = 264.dp
    val itemSize = 28.dp

    val upperDividerYOffset = boxHeight / 2 - itemSize / 2
    val underDividerYOffset = boxHeight / 2 + itemSize / 2

    LaunchedEffect(listState) {
        snapshotFlow { listState.firstVisibleItemIndex }
            .filter { it in options.indices }
            .distinctUntilChanged()
            .collect { round -> onChangedOption(round) }
    }

    LaunchedEffect(selectedOption) {
        val targetIndex = selectedOption.round - 1
        if (targetIndex != listState.firstVisibleItemIndex) {
            listState.animateScrollToItem(targetIndex)
        }
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(height = boxHeight)
            .innerShadow(
                shape = RectangleShape,
                blur = 0.dp,
                offsetX = 0.dp,
                offsetY = (-0.5).dp,
            ),
    ) {
        HorizontalDivider(
            modifier = Modifier.offset(y = upperDividerYOffset),
            thickness = 0.5.dp,
            color = Color(0xFFCCCCCC),
        )

        HorizontalDivider(
            modifier = Modifier.offset(y = underDividerYOffset),
            thickness = 0.5.dp,
            color = Color(0xFFCCCCCC),
        )

        LazyColumn(
            state = listState,
            flingBehavior = flingBehavior,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .align(Alignment.Center)
                .wrapContentSize()
                .height(itemSize * visibleOptionCount)
                .fadingEdge(fadingEdgeGradient),
            userScrollEnabled = false,
        ) {
            items(
                count = listCount,
                key = { it },
            ) { index ->
                val distanceFromCenter = kotlin.math.abs(index - currentCenterIndex.value)
                val centerIndex = index - median
                val fontSize = when (distanceFromCenter) {
                    0 -> 23.sp
                    1 -> 18.sp
                    2 -> 14.sp
                    else -> 12.sp
                }

                Box(
                    modifier = Modifier
                        .height(itemSize)
                        .fillMaxWidth()
                        .then(
                            if (onItemClick != null && centerIndex in options.indices) {
                                Modifier.clickable(
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = null,
                                ) { onItemClick(centerIndex) }
                            } else {
                                Modifier
                            },
                        ),
                    contentAlignment = Alignment.Center,
                ) {
                    SGText(
                        text = getTitleText(index = index),
                        style = getSGNonScaleTextStyle(
                            color = SGColor.Grayscale.black100,
                            fontSize = fontSize,
                            fontWeight = FontWeight.Normal,
                            lineHeight = 20.sp,
                            fontFamily = SGTypography.pretendard,
                        ),
                    )
                }
            }
        }
    }
}

private fun Modifier.fadingEdge(brush: Brush) = this
    .graphicsLayer(compositingStrategy = CompositingStrategy.Offscreen)
    .drawWithContent {
        drawContent()
        drawRect(brush = brush, blendMode = BlendMode.DstIn)
    }

@Composable
private fun TempPickerButton(
    text: String,
    textColor: Color,
    containerColor: Color,
    borderColor: Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    val shape = RoundedCornerShape(20.dp)

    TextButton(
        onClick = onClick,
        modifier = modifier
            .height(48.dp)
            .width(120.dp)
            .border(width = 1.dp, color = borderColor, shape = shape),
        shape = shape,
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
        ),
    ) {
        SGText(
            text = text,
            style = getSGNonScaleTextStyle(
                color = textColor,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                lineHeight = 20.sp,
                fontFamily = SGTypography.pretendard,
            ),
        )
    }
}

@DevicePreviews
@Composable
private fun FeedScrollTitlePicker_Preview() {
    val options = List(15) { TitleOption(round = 1, subject = "주제") }
    FeedScrollTitlePickerComponent(
        selectedOption = options[0],
        options = options,
        onChangedOption = {},
        onSelectOption = {},
        onDismissRequest = {},
    )
}

@DevicePreviews
@Composable
private fun ScrollPicker_Preview() {
    val options = List(15) { TitleOption(round = 1, subject = "주제") }
    ScrollPicker(
        selectedOption = options[0],
        options = options,
        visibleOptionCount = 5,
        onChangedOption = {},
    )
}
