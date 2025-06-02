package com.captures2024.soongan.feature.feed.ui

import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.core.designsystem.ui.component.WidthSpacer
import com.captures2024.soongan.core.designsystem.ui.component.text.SGText
import com.captures2024.soongan.core.designsystem.ui.component.text.getSGNonScaleTextStyle
import com.captures2024.soongan.core.designsystem.ui.theme.SGColor
import com.captures2024.soongan.core.designsystem.ui.theme.SGTypography
import com.captures2024.soongan.core.model.mock.mockFeedTitleOptions
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter

@Composable
internal fun FeedScrollTitlePicker(
    selectedOption: Pair<Int, String>,
    options: List<Pair<Int, String>>,
    modifier: Modifier = Modifier,
    onChangedOption: (Int) -> Unit,
    onSelectTitle: () -> Unit,
    onDismissRequest: () -> Unit,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        SGText(
            text = "회차 선택",
            modifier = Modifier
                .align(Alignment.Start)
                .padding(start = 24.dp),
            style = getSGNonScaleTextStyle(
                color = SGColor.Grayscale.black100,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                lineHeight = 20.sp,
                fontFamily = SGTypography.pretendard,
            )
        )

        ScrollPicker(
            selectedOption = selectedOption,
            options = options,
            onChangedOption = onChangedOption,
        )

        Row(
            modifier = Modifier.padding(bottom = 136.dp)
        ) {
            TempPickerButton(
                text = "취소",
                textColor = SGColor.black,
                containerColor = SGColor.transparent,
                onClick = onDismissRequest,
            )
            WidthSpacer(54.dp)
            TempPickerButton(
                text = "확인",
                textColor = SGColor.white,
                containerColor = SGColor.black100,
                onClick = onSelectTitle,
            )
        }
    }
}

@Composable
private fun ScrollPicker(
    selectedOption: Pair<Int, String>,
    options: List<Pair<Int, String>>,
    modifier: Modifier = Modifier,
    visibleOptionCount: Int = 7,
    onChangedOption: (Int) -> Unit,
) {
    val median = visibleOptionCount / 2
    val spaceOptions = List(median) { null }
    val adjustedOptions = spaceOptions + options + spaceOptions
    val listCount = adjustedOptions.size

    fun getOptionTitle(index: Int): String = adjustedOptions.getOrNull(index)?.let {
        "${it.first}회차 | ${it.second}"
    } ?: ""

    val listState =
        rememberLazyListState(initialFirstVisibleItemIndex = selectedOption.first - 1)
    val flingBehavior = rememberSnapFlingBehavior(lazyListState = listState)

    val currentCenterIndex = remember {
        derivedStateOf {
            listState.firstVisibleItemIndex + median
        }
    }

    val fadingEdgeGradient = Brush.verticalGradient(
        0f to Color.Transparent,
        0.5f to Color.Black,
        1f to Color.Transparent
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

    HorizontalDivider(
        modifier = Modifier.offset(y = upperDividerYOffset),
        thickness = 0.5.dp,
        color = Color(0xFFCCCCCC)
    )
    HorizontalDivider(
        modifier = Modifier.offset(y = underDividerYOffset),
        thickness = 0.5.dp,
        color = Color(0xFFCCCCCC)
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(height = boxHeight)
            .nestedScroll(object : NestedScrollConnection {
                override fun onPostScroll(
                    consumed: Offset,
                    available: Offset,
                    source: NestedScrollSource
                ): Offset = available
            }),
    ) {
        LazyColumn(
            state = listState,
            flingBehavior = flingBehavior,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .align(Alignment.Center)
                .wrapContentSize()
                .height(itemSize * visibleOptionCount)
                .fadingEdge(fadingEdgeGradient)
        ) {
            items(
                count = listCount,
                key = { it },
            ) { index ->
                val distanceFromCenter = kotlin.math.abs(index - currentCenterIndex.value)
                val fontSize = when (distanceFromCenter) {
                    0 -> 23.sp
                    1 -> 18.sp
                    2 -> 14.sp
                    else -> 12.sp
                }

                Box(
                    modifier = Modifier.height(itemSize),
                    contentAlignment = Alignment.Center,
                ) {
                    SGText(
                        text = getOptionTitle(index = index),
                        style = getSGNonScaleTextStyle(
                            color = SGColor.Grayscale.black100,
                            fontSize = fontSize,
                            fontWeight = FontWeight.Normal,
                            lineHeight = 20.sp,
                            fontFamily = SGTypography.pretendard,
                        )
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

//private fun Modifier.scalingEdge(scale: Float) = this
//    .graphicsLayer(scaleX = scale, scaleY = scale)

@Composable
private fun TempPickerButton(
    text: String,
    textColor: Color,
    containerColor: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TextButton(
        onClick = onClick,
        modifier = modifier
            .height(48.dp)
            .width(120.dp),
        shape = RoundedCornerShape(20.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
        )
    ) {
        SGText(
            text = text,
            style = getSGNonScaleTextStyle(
                color = textColor,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                lineHeight = 20.sp,
                fontFamily = SGTypography.pretendard,
            )
        )
    }
}

@Preview
@Composable
private fun FeedScrollTitlePicker_Preview() {
    FeedScrollTitlePicker(
        selectedOption = mockFeedTitleOptions.first(),
        options = mockFeedTitleOptions,
        onChangedOption = {},
        onSelectTitle = {},
        onDismissRequest = {},
    )
}
