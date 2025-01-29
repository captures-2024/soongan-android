package com.captures2024.soongan.feature.home.ui.registration_post

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.captures2024.soongan.core.designsystem.component.HeightSpacer
import com.captures2024.soongan.core.designsystem.component.NonScaleText
import com.captures2024.soongan.core.designsystem.theme.NanumSquareNeoFontFamily
import com.captures2024.soongan.core.designsystem.theme.SGColor
import com.captures2024.soongan.core.designsystem.theme.dropShadow
import com.captures2024.soongan.core.designsystem.util.DevicePreviews
import com.captures2024.soongan.core.viewmodel.post.RegistrationPostViewModel
import com.captures2024.soongan.feature.home.R

@Composable
internal fun RegistrationPostScreen(
    uiState: RegistrationPostViewModel.State,
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit = {},
    onTitleValueChanged: (String) -> Unit = {},
    onClickSubmit: () -> Unit = {},
) {
    Scaffold(
        modifier = modifier.fillMaxSize()
            .background(color = SGColor.tempPrimaryD)
            .paint(
                painter = painterResource(id = com.captures2024.soongan.core.design.R.drawable.background_home_gallery),
                contentScale = ContentScale.Crop,
            ),
        topBar = @Composable {
            RegistrationPostScreenTopBar(onBackPressed = onBackPressed)
        },
        containerColor = SGColor.transparent,
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(
                    vertical = 14.dp,
                    horizontal = 20.dp,
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Box(
                modifier = Modifier
                    .width(353.dp)
                    .height(353.dp),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(uiState.currentMedia)
                        .build(),
                    contentDescription = "photo",
                    modifier = modifier
                        .widthIn(max = 353.dp)
                        .heightIn(max = 353.dp)
                        .dropShadow(
                            shape = RoundedCornerShape(0.dp),
                            color = SGColor.black.copy(alpha = 0.25f),
                            blur = 3.dp,
                            offsetX = 6.dp,
                            offsetY = 6.dp,
                        ),
                    contentScale = ContentScale.Fit,
                )
            }
            HeightSpacer(36.dp)
            TitleInputEditText(
                value = uiState.title,
                onValueChange = onTitleValueChanged,
            )
            HeightSpacer(82.dp)
            RegistrationButton(
                text = stringResource(R.string.registration_post_button_title),
                onClick = onClickSubmit,
                enabled = uiState.currentMedia != null && uiState.title.isNotEmpty(),
            )
            HeightSpacer(8.dp)
            NonScaleText(
                text = stringResource(R.string.registration_post_cautionary_phrase),
                fontSize = 12.sp,
                lineHeight = 14.sp,
                fontFamily = NanumSquareNeoFontFamily,
                color = SGColor.primaryA
            )
        }
    }
}

@DevicePreviews
@Composable
private fun RegistrationPostScreenPreview() {
    RegistrationPostScreen(
        uiState = RegistrationPostViewModel.State(),
    )
}