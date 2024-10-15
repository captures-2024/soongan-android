package com.captures2024.soongan.feature.home.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.captures2024.soongan.core.design.R
import com.captures2024.soongan.core.designsystem.util.DevicePreviews

@Composable
internal fun _HomeScreen(
    modifier: Modifier = Modifier
) {
    val commonModifier = modifier.fillMaxSize()

    Image(
        modifier = commonModifier,
        painter = painterResource(id = R.drawable.background_home_gallery),
        contentScale = ContentScale.Crop,
        contentDescription = "background"
    )
    Column(
        modifier = commonModifier
            .padding(top = 40.dp)
    ) {
        HomeScreenTopBar()
        Spacer(modifier = Modifier.height(68.dp))
        HomeScreenBody()
    }
}

@DevicePreviews
@Composable
private fun HomeScreenPreview() {
    _HomeScreen()
}