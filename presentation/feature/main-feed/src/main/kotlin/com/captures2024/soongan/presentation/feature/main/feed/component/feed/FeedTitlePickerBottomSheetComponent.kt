package com.captures2024.soongan.presentation.feature.main.feed.component.feed

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import com.captures2024.soongan.core.designsystem.ui.util.DevicePreviews
import com.captures2024.soongan.core.model.mock.mockFeedTitleOptions
import com.captures2024.soongan.presentation.viewmodel.main.feed.TitleOption

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun FeedTitlePickerBottomSheetComponent(
    selectedOption: TitleOption,
    options: List<TitleOption>,
    modifier: Modifier = Modifier,
    sheetState: SheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
    onSelectTitle: (round: Int) -> Unit,
    onDismissRequest: () -> Unit,
) {
    var currentSelectedOption by remember { mutableStateOf(selectedOption) }
    val round = currentSelectedOption.first

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        modifier = modifier,
        sheetState = sheetState,
        containerColor = Color.White,
    ) {
        FeedScrollTitlePickerComponent(
            selectedOption = currentSelectedOption,
            options = options,
            onChangedOption = { idx -> currentSelectedOption = options[idx] },
            onSelectTitle = { onSelectTitle(round) },
            onDismissRequest = onDismissRequest
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@DevicePreviews
@Composable
private fun FeedScrollTitlePickerBottomSheet_Preview() {
    val sheetState = SheetState(
        skipPartiallyExpanded = true,
        initialValue = SheetValue.Expanded,
        density = LocalDensity.current,
        skipHiddenState = false,
    )

    FeedTitlePickerBottomSheetComponent(
        selectedOption = mockFeedTitleOptions.first(),
        options = mockFeedTitleOptions,
        sheetState = sheetState,
        onSelectTitle = {},
        onDismissRequest = {},
    )
}
