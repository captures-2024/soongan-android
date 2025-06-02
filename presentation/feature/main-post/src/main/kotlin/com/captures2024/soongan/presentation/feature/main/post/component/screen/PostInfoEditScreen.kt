package com.captures2024.soongan.presentation.feature.main.post.component.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.captures2024.soongan.presentation.designsystem.ui.component.HeightSpacer
import com.captures2024.soongan.presentation.designsystem.ui.component.WeightSpacer
import com.captures2024.soongan.presentation.designsystem.ui.component.button.SGTextButtonType2
import com.captures2024.soongan.presentation.designsystem.ui.component.dialog.SGSingleButtonDialog
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGColor
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGTheme
import com.captures2024.soongan.presentation.designsystem.ui.util.DevicePreviews
import com.captures2024.soongan.presentation.feature.main.post.R
import com.captures2024.soongan.presentation.feature.main.post.component.info.PostInfoEditTitleComponent
import com.captures2024.soongan.presentation.feature.main.post.component.info.PostInfoTopBarComponent
import com.captures2024.soongan.presentation.viewmodel.main.post.PostInfoEditViewModel

@Composable
internal fun PostInfoEditScreen(
    state: PostInfoEditViewModel.State,
    modifier: Modifier = Modifier,
    onClickBack: () -> Unit,
    onTitleValueChanged: (String) -> Unit,
    onClickEdit: () -> Unit,
    onClickConfirmInitErrorDialog: () -> Unit,
) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()

    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .background(color = SGColor.BG.background),
        topBar = @Composable {
            PostInfoTopBarComponent(
                round = state.round,
                subject = state.subject,
                onClickBack = onClickBack,
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
                .padding(horizontal = 20.dp)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Box(
                modifier = Modifier
                    .width(360.dp)
                    .height(460.dp),
                contentAlignment = Alignment.Center,
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(context)
                        .data(state.defaultUrl)
                        .build(),
                    contentDescription = stringResource(R.string.image_description),
                    modifier = modifier
                        .widthIn(max = 360.dp)
                        .heightIn(max = 460.dp),
                    contentScale = ContentScale.Fit,
                )
            }
            HeightSpacer(36.dp)

            PostInfoEditTitleComponent(
                value = state.editTitle,
                maxInputLength = state.maxInputLength,
                onValueChange = onTitleValueChanged,
            )

            WeightSpacer(1f)

            SGTextButtonType2(
                text = stringResource(R.string.button_edit),
                enabled = state.isEditable,
                onClick = onClickEdit,
            )
        }
    }

    if (state.isShowInitErrorDialog) {
        SGSingleButtonDialog(
            content = stringResource(R.string.post_info_edit_init_error_content),
            confirmContent = stringResource(R.string.button_confirm),
            onClickConfirm = onClickConfirmInitErrorDialog,
            onDismissRequest = onClickConfirmInitErrorDialog,
        )
    }
}

@DevicePreviews
@Composable
private fun PreviewPostInfoEditScreen() {
    SGTheme {
        PostInfoEditScreen(
            state = PostInfoEditViewModel.State(
                round = 1,
                subject = "평화",
                postId = -1L,
                defaultUrl = "test",
                defaultTitle = "test",
                editTitle = "test",
                maxInputLength = 15,
                isShowInitErrorDialog = false,
            ),
            onClickBack = {},
            onTitleValueChanged = {},
            onClickEdit = {},
            onClickConfirmInitErrorDialog = {},
        )
    }
}
