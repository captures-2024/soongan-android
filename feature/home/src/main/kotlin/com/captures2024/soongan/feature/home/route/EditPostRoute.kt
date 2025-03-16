package com.captures2024.soongan.feature.home.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.captures2024.soongan.core.viewmodel.post.EditPostViewModel
import com.captures2024.soongan.feature.home.ui.edit_post.EditPostScreen

@Composable
internal fun EditPostRoute(
    navigateToBack: () -> Unit,
    editPostViewModel: EditPostViewModel = hiltViewModel(),
) {
    val uiState by editPostViewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit) {
        editPostViewModel.sideEffect.collect { effect ->
            when (effect) {
                EditPostViewModel.Effect.NavigateToBack -> navigateToBack()
            }
        }
    }

    EditPostScreen(
        uiState = uiState,
        intent = editPostViewModel::intent,
    )
}
