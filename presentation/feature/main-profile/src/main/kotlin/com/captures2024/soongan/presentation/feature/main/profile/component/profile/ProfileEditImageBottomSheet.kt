package com.captures2024.soongan.presentation.feature.main.profile.component.profile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.presentation.designsystem.ui.component.text.SGText
import com.captures2024.soongan.presentation.designsystem.ui.component.text.getSGNonScaleTextStyle
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGColor
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGTheme
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGTypography
import com.captures2024.soongan.presentation.designsystem.ui.util.DevicePreviews
import com.captures2024.soongan.presentation.feature.main.profile.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ProfileEditImageBottomSheet(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
    onClickOpenPhotoPicker: () -> Unit,
    onClickDefaultImage: () -> Unit,
) {
    val sheetState: SheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        modifier = modifier.fillMaxWidth(),
        sheetState = sheetState,
        containerColor = SGColor.Grayscale.white,
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 24.dp),
        ) {
            BottomSheetRow(
                itemText = stringResource(R.string.profile_image_gallery),
                onClick = onClickOpenPhotoPicker,
            )

            HorizontalDivider(color = SGColor.Grayscale.black100.copy(alpha = 0.3f))

            BottomSheetRow(
                itemText = stringResource(R.string.profile_image_default),
                onClick = onClickDefaultImage,
            )
        }
    }
}

@Composable
private fun BottomSheetRow(
    itemText: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(horizontal = 16.dp)
            .clickable(onClick = onClick),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        SGText(
            text = itemText,
            style = getSGNonScaleTextStyle(
                color = SGColor.Grayscale.black100,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 20.sp,
                fontFamily = SGTypography.pretendard,
                letterSpacing = (-5).em,
            ),
        )
    }
}

@DevicePreviews
@Composable
private fun PreviewProfileEditImageBottomSheet() {
    SGTheme {
        ProfileEditImageBottomSheet(
            onDismissRequest = {},
            onClickOpenPhotoPicker = {},
            onClickDefaultImage = {},
        )
    }
}
