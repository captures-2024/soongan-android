package com.captures2024.soongan.feature.home.ui.registration_post

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.core.designsystem.component.NonScaleText
import com.captures2024.soongan.core.designsystem.icon.MyIconPack
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonFillLeftArrow
import com.captures2024.soongan.core.designsystem.theme.NanumSquareNeoFontFamily
import com.captures2024.soongan.core.designsystem.theme.SGColor
import com.captures2024.soongan.core.designsystem.util.DevicePreviews
import com.captures2024.soongan.feature.home.R

@Composable
internal fun SubmitBottomSheetDialogTopBar(
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit = {}
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Icon(
                    imageVector = MyIconPack.IconNonFillLeftArrow,
                    contentDescription = "back",
                    modifier = Modifier.clickable(onClick = onBackPressed),
                    tint = SGColor.primaryA,
                )
            }
            NonScaleText(
                text = stringResource(id = R.string.submit_bottom_sheet_title),
                color = SGColor.black,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 24.sp,
                fontFamily = NanumSquareNeoFontFamily
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        HorizontalDivider(
            color = SGColor.primaryB.copy(alpha = 0.3f),
            thickness = 1.dp
        )
    }
}

@DevicePreviews
@Composable
private fun SubmitBottomSheetDialogTopBarPreview() {
    SubmitBottomSheetDialogTopBar()
}