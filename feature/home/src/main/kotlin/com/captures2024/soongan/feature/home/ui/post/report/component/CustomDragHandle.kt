package com.captures2024.soongan.feature.home.ui.post.report.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.captures2024.soongan.core.designsystem.theme.SGColor

@Composable
internal fun CustomDragHandle(modifier: Modifier = Modifier) = Box(
    modifier
        .padding(top = 16.dp)
        .background(color = SGColor.primaryA, shape = RoundedCornerShape(50))
        .size(width = 40.dp, height = 4.dp)
)
