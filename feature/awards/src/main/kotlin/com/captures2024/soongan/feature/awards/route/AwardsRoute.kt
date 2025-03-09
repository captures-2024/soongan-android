package com.captures2024.soongan.feature.awards.route

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.captures2024.soongan.core.designsystem.ui.util.extension.sgBottomBarPadding
import com.captures2024.soongan.feature.awards.ui.AwardsScreen

@Composable
internal fun AwardsRoute() {
    AwardsScreen(
        modifier = Modifier.sgBottomBarPadding()
    )
}
