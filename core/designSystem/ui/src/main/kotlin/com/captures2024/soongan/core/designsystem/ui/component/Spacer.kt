package com.captures2024.soongan.core.designsystem.ui.component

import androidx.annotation.FloatRange
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

@Composable
fun HeightSpacer(height: Dp) = Spacer(modifier = Modifier.height(height))

@Composable
fun WidthSpacer(width: Dp) = Spacer(modifier = Modifier.width(width))

@Composable
fun ColumnScope.WeightSpacer(
    @FloatRange(from = 0.0, fromInclusive = false) weight: Float
) = Spacer(modifier = Modifier.weight(weight))

@Composable
fun RowScope.WeightSpacer(
    @FloatRange(from = 0.0, fromInclusive = false) weight: Float
) = Spacer(modifier = Modifier.weight(weight))
