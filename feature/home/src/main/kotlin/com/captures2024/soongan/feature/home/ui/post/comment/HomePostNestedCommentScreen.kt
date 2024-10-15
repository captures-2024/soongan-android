package com.captures2024.soongan.feature.home.ui.post.comment

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.captures2024.soongan.core.designsystem.util.DevicePreviews

@Composable
internal fun HomePostNestedCommentScreen(
    modifier: Modifier = Modifier
) {
    var isOpen by remember { mutableStateOf(false) }

    when (isOpen) {
        false -> HomePostNestedCommentClosedScreen(modifier) { isOpen = true }

        true -> HomePostNestedCommentOpenScreen(modifier) { isOpen = false }
    }
}

@DevicePreviews
@Composable
private fun HomePostNestedCommentScreenPreview() {
    HomePostNestedCommentScreen()
}