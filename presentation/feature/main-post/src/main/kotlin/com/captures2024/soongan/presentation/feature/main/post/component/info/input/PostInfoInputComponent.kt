package com.captures2024.soongan.presentation.feature.main.post.component.info.input

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import com.captures2024.soongan.presentation.designsystem.ui.component.HeightSpacer
import com.captures2024.soongan.presentation.designsystem.ui.component.WeightSpacer
import com.captures2024.soongan.presentation.designsystem.ui.component.button.SGTextButtonType2
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGTheme
import com.captures2024.soongan.presentation.designsystem.ui.util.DevicePreviews
import com.captures2024.soongan.presentation.feature.main.post.component.info.PostInfoImageComponent

@Composable
internal fun PostInfoInputComponent(
    model: Any?,
    value: String,
    maxInputLength: Int,
    isEnabledButton: Boolean,
    buttonContent: String,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
    onClickButton: () -> Unit,
) {
    val focusManager = LocalFocusManager.current
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
            .verticalScroll(scrollState)
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick = { focusManager.clearFocus() }
            )
            .imePadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        PostInfoImageComponent(
            model = model,
        )

        HeightSpacer(36.dp)

        PostInfoInputTitleComponent(
            value = value,
            maxInputLength = maxInputLength,
            onValueChange = onValueChange,
        )

        WeightSpacer(1f)

        SGTextButtonType2(
            text = buttonContent,
            enabled = isEnabledButton,
            onClick = onClickButton,
        )

        HeightSpacer(58.dp)
    }
}

@DevicePreviews
@Composable
private fun PreviewPostInfoInputComponent() {
    SGTheme {
        PostInfoInputComponent(
            model = null,
            value = "",
            maxInputLength = 15,
            isEnabledButton = true,
            buttonContent = "buttonContent",
            onValueChange = {},
            onClickButton = {},
        )
    }
}
