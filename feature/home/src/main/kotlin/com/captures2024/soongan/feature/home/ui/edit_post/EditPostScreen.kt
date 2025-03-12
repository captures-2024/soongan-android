package com.captures2024.soongan.feature.home.ui.edit_post

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
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.captures2024.soongan.core.designsystem.component.HeightSpacer
import com.captures2024.soongan.core.designsystem.component.WeightSpacer
import com.captures2024.soongan.core.designsystem.component.button.SGTextButtonType2
import com.captures2024.soongan.core.designsystem.theme.SGColor
import com.captures2024.soongan.core.designsystem.theme.dropShadow
import com.captures2024.soongan.core.designsystem.util.DevicePreviews
import com.captures2024.soongan.core.viewmodel.post.EditPostViewModel
import com.captures2024.soongan.feature.home.ui.registration_post.RegistrationPostScreenTopBar
import com.captures2024.soongan.feature.home.ui.registration_post.TitleInputEditText

@Composable
internal fun EditPostScreen(
    uiState: EditPostViewModel.State,
    intent: (EditPostViewModel.Intent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .background(color = SGColor.tempPrimaryD)
            .paint(
                painter = painterResource(id = com.captures2024.soongan.core.design.R.drawable.background_home_gallery),
                contentScale = ContentScale.Crop,
            ),
        topBar = @Composable {
            RegistrationPostScreenTopBar(
                onBackPressed = { intent(EditPostViewModel.Intent.OnClickBack) }
            )
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
                    .data(uiState.imageUrl)
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
                onValueChange = { intent(EditPostViewModel.Intent.OnTitleValueChanged(it)) },
            )

            WeightSpacer(1f)

            SGTextButtonType2(
                text = "수정하기",
                enabled = uiState.isEditable,
                onClick = { intent(EditPostViewModel.Intent.OnClickEditRemote) },
            )
        }
    }
}

@DevicePreviews
@Composable
private fun RegistrationPostScreenPreview() {
    EditPostScreen(
        uiState = EditPostViewModel.State(),
        intent = {}
    )
}