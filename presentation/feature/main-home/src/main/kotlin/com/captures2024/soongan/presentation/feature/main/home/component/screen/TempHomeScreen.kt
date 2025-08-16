package com.captures2024.soongan.presentation.feature.main.home.component.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGColor
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGTheme
import com.captures2024.soongan.presentation.designsystem.ui.util.DevicePreviews
import com.captures2024.soongan.presentation.feature.main.home.component.home.HomeFailedComponent
import com.captures2024.soongan.presentation.feature.main.home.component.home.HomeInitComponent
import com.captures2024.soongan.presentation.viewmodel.main.home.TempHomeViewModel
import com.captures2024.soongan.presentation.viewmodel.model.enums.HomeInfoState

@Composable
internal fun TempHomeScreen(
    state: TempHomeViewModel.State,
    onClickRetry: () -> Unit,
) {
    val commonModifier = Modifier
        .fillMaxSize()
        .background(color = SGColor.BG.background)

    when (state.homeInfo.homeInfoState) {
        HomeInfoState.INIT -> HomeInitComponent(
            modifier = commonModifier,
        )

        HomeInfoState.ERROR -> HomeFailedComponent(
            isLoading = state.isLoading,
            modifier = commonModifier,
            onClickRetry = onClickRetry,
        )

        HomeInfoState.EMPTY -> TODO()

        HomeInfoState.SUCCESS -> TODO()
    }
}

@DevicePreviews
@Composable
private fun PreviewTempHomeScreen_Init() {
    SGTheme {
        TempHomeScreen(
            state = TempHomeViewModel.State(),
            onClickRetry = {},
        )
    }
}
