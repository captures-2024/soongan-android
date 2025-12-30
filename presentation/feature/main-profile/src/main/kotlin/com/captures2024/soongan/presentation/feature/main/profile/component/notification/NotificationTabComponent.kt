package com.captures2024.soongan.presentation.feature.main.profile.component.notification

import androidx.compose.material3.Tab
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.AnnotatedString
import com.captures2024.soongan.presentation.designsystem.ui.component.text.SGText
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGTheme
import com.captures2024.soongan.presentation.designsystem.ui.util.DevicePreviews
import com.captures2024.soongan.presentation.feature.main.profile.utils.nonScaleAnnotatedTitle

@Composable
internal fun NotificationTabComponent(
    annotatedString: AnnotatedString,
    selected: Boolean,
    onClick: () -> Unit,
) {
    Tab(
        modifier = when (selected) {
            true -> Modifier
            false -> Modifier.graphicsLayer(alpha = 0.4f)
        },
        selected = selected,
        onClick = onClick,
        text = @Composable {
            SGText(annotatedString = annotatedString)
        },
    )
}

@DevicePreviews
@Composable
private fun PreviewNotificationTabComponent() {
    SGTheme {
        NotificationTabComponent(
            annotatedString = nonScaleAnnotatedTitle(
                title = "test",
                count = 0,
            ),
            selected = true,
            onClick = {},
        )
    }
}
