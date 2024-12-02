package com.captures2024.soongan.feature.profile.ui.edit

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.captures2024.soongan.core.designsystem.component.SoonGanIconButton
import com.captures2024.soongan.core.designsystem.icon.MyIconPack
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonFillLeftArrow
import com.captures2024.soongan.core.designsystem.theme.PrimaryA
import com.captures2024.soongan.core.designsystem.util.DevicePreviews

@Composable
fun EditProfileScreenHeader(
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        SoonGanIconButton(
            onClick = onBackPressed
        ) {
            Icon(
                imageVector = MyIconPack.IconNonFillLeftArrow,
                contentDescription = MyIconPack.IconNonFillLeftArrow.name,
                tint = PrimaryA
            )
        }
    }
}

@DevicePreviews
@Composable
private fun EditProfileScreenHeaderPreview() {
    EditProfileScreenHeader {  }
}