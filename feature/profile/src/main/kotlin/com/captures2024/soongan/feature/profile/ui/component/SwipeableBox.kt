package com.captures2024.soongan.feature.profile.ui.component

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.core.designsystem.icon.MyIconPack
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonFillDelete
import com.captures2024.soongan.core.designsystem.ui.theme.SGColor
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

// actions width 만큼 swipe 가능한 box component (only from right to left)
@Composable
internal fun SwipeableBox(
    actions: @Composable RowScope.() -> Unit,
    modifier: Modifier = Modifier,
    onExpanded: () -> Unit = {},
    onCollapsed: () -> Unit = {},
    content: @Composable () -> Unit,
) {
    var boxWidth by remember { mutableFloatStateOf(0f) }
    val offset = remember { Animatable(0f) }
    val scope = rememberCoroutineScope()

    Box(
        modifier = modifier
            .fillMaxSize()
            .height(IntrinsicSize.Min),
    ) {
        Row(
            modifier = Modifier
                .onSizeChanged { boxWidth = it.width.toFloat() }
                .align(Alignment.CenterEnd),
        ) {
            actions()
        }

        Surface(
            modifier = Modifier
                .fillMaxSize()
                .offset { IntOffset(offset.value.roundToInt(), 0) }
                .pointerInput(boxWidth) {
                    detectHorizontalDragGestures(
                        onHorizontalDrag = { _, dragAmount ->
                            scope.launch {
                                val newOffset = (offset.value + dragAmount).coerceIn(-boxWidth, 0f)
                                offset.snapTo(newOffset)
                            }
                        },
                        onDragEnd = {
                            when {
                                offset.value <= -boxWidth / 2f -> {
                                    scope.launch {
                                        offset.animateTo(-boxWidth)
                                        onExpanded()
                                    }
                                }

                                else -> {
                                    scope.launch {
                                        offset.animateTo(0f)
                                        onCollapsed()
                                    }
                                }
                            }
                        },
                    )
                },
            color = SGColor.white,
        ) {
            content()
        }
    }
}

@Preview
@Composable
private fun SwipeableBoxPreview() {
    SwipeableBox(
        modifier = Modifier.height(100.dp),
        actions = {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .background(color = SGColor.negative)
                    .padding(horizontal = 12.dp),
                contentAlignment = Alignment.Center,
            ) {
                Icon(MyIconPack.IconNonFillDelete, null, tint = SGColor.white)
            }
        },
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(text = "SwipeBox from left to right", fontSize = 20.sp)
        }
    }
}
