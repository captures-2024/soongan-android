package com.captures2024.soongan.feature.home.ui.registration_post

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.core.designsystem.component.NonScaleText
import com.captures2024.soongan.core.designsystem.icon.MyIconPack
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonFillLeftArrow
import com.captures2024.soongan.core.designsystem.theme.NanumSquareNeoFontFamily
import com.captures2024.soongan.core.designsystem.theme.PrimaryA
import com.captures2024.soongan.core.designsystem.util.DevicePreviews
import com.captures2024.soongan.feature.home.ui.component.HomeGalleryButton

@Composable
internal fun RegistrationPostScreenTopBar(
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 14.dp),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            HomeGalleryButton(onClick = onBackPressed) {
                Icon(
                    imageVector = MyIconPack.IconNonFillLeftArrow,
                    contentDescription = "back",
                    tint = PrimaryA
                )
            }
        }
        NonScaleText(
            text = "주간 | 평화",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            fontFamily = NanumSquareNeoFontFamily,
        )
    }
}

@DevicePreviews
@Composable
private fun RegistrationPostScreenTopBarPreview() {
    RegistrationPostScreenTopBar()
}

