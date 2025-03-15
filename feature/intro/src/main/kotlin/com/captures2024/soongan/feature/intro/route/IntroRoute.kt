package com.captures2024.soongan.feature.intro.route

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.captures2024.soongan.feature.intro.ui.IntroScreen

@Composable
fun IntroRoute() {
    Scaffold { padding ->
        IntroScreen(
            modifier = Modifier.padding(padding),
        )
    }
}
