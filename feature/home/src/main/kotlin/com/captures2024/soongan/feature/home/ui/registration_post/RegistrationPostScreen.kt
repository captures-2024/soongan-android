package com.captures2024.soongan.feature.home.ui.registration_post

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.captures2024.soongan.core.designsystem.ui.component.HeightSpacer
import com.captures2024.soongan.core.designsystem.ui.component.WeightSpacer
import com.captures2024.soongan.core.designsystem.ui.component.button.SGTextButtonType2
import com.captures2024.soongan.core.designsystem.ui.component.text.SGText
import com.captures2024.soongan.core.designsystem.ui.component.text.getSGNonScaleTextStyle
import com.captures2024.soongan.core.designsystem.ui.theme.SGColor
import com.captures2024.soongan.core.designsystem.ui.theme.SGTypography
import com.captures2024.soongan.core.designsystem.ui.theme.dropShadow
import com.captures2024.soongan.core.designsystem.ui.util.DevicePreviews
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
                painter = painterResource(id = com.captures2024.soongan.core.designsystem.ui.R.drawable.background_home_gallery),
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
                    top = 28.dp,
                    bottom = 58.dp,
                )
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(uiState.currentMedia)
                    .build(),
                contentDescription = "photo",
                modifier = modifier
                    .width(353.dp)
                    .height(353.dp)
                    .dropShadow(RectangleShape)
                    .background(SGColor.white),
                contentScale = ContentScale.Crop,
            )

            HeightSpacer(36.dp)

            TitleInputEditText(
                value = uiState.title,
                onValueChange = onTitleValueChanged,
            )

            WeightSpacer(1f)

            SGTextButtonType2(
                text = stringResource(R.string.registration_post_button_title),
                enabled = uiState.currentMedia != null && uiState.title.isNotEmpty(),
                onClick = onClickSubmit,
            )

            HeightSpacer(8.dp)

            SGText(
                text = stringResource(R.string.registration_post_cautionary_phrase),
                style = getSGNonScaleTextStyle(
                    color = SGColor.primaryA,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    lineHeight = 12.sp,
                    fontFamily = SGTypography.nanumSquareNeo,
                    letterSpacing = (-5).em,
                )
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