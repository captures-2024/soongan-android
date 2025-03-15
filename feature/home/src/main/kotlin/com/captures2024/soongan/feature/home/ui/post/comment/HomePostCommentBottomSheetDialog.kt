package com.captures2024.soongan.feature.home.ui.post.comment

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Velocity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.captures2024.soongan.core.designsystem.ui.component.CommentInputTextField
import com.captures2024.soongan.core.designsystem.ui.component.HeightSpacer
import com.captures2024.soongan.core.designsystem.ui.component.WidthSpacer
import com.captures2024.soongan.core.designsystem.ui.component.text.SGText
import com.captures2024.soongan.core.designsystem.ui.component.text.getSGNonScaleTextStyle
import com.captures2024.soongan.core.designsystem.ui.theme.SGColor
import com.captures2024.soongan.core.designsystem.ui.theme.SGTypography
import com.captures2024.soongan.core.designsystem.ui.util.DevicePreviews
import com.captures2024.soongan.core.model.UserPost
import com.captures2024.soongan.core.model.mock.samplePhotos
import com.captures2024.soongan.feature.home.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun HomePostCommentBottomSheetDialog(
    modifier: Modifier = Modifier,
    comment: String,
    closeSheet: () -> Unit = {},
    onCommentValueChanged: (String) -> Unit = {},
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    val connection = remember {
        object : NestedScrollConnection {
            override fun onPostScroll(
                consumed: Offset,
                available: Offset,
                source: NestedScrollSource,
            ): Offset = available.copy(x = 0f)

            override suspend fun onPostFling(
                consumed: Velocity,
                available: Velocity,
            ): Velocity = available.copy(x = 0f)
        }
    }

    ModalBottomSheet(
        modifier = modifier
            .height(600.dp),
        onDismissRequest = closeSheet,
        sheetState = sheetState,
        containerColor = SGColor.white,
    ) {
        Scaffold(
            modifier = modifier
                .fillMaxSize()
                .nestedScroll(connection),
            topBar = @Composable {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    SGText(
                        text = stringResource(id = R.string.home_post_comment_bottom_sheet_dialog_title),
                        style = getSGNonScaleTextStyle(
                            color = SGColor.primaryA,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            lineHeight = 24.sp,
                            fontFamily = SGTypography.nanumSquareNeo,
                            letterSpacing = 0.em,
                        ),
                    )

                    HeightSpacer(8.dp)

                    HorizontalDivider(
                        color = SGColor.primaryA.copy(alpha = 0.3f),
                        thickness = 1.dp,
                    )
                }
            },
            bottomBar = @Composable {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 12.dp,
                            end = 16.dp,
                            top = 8.dp,
                            bottom = 8.dp,
                        ),
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data((samplePhotos[0] as UserPost.PhotoPost).url)
                            .build(),
                        contentDescription = "my_profile",
                        modifier = Modifier
                            .size(36.dp, 36.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop,
                    )

                    WidthSpacer(8.dp)

                    CommentInputTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = comment,
                        onValueChange = onCommentValueChanged,
                        hint = stringResource(id = R.string.home_post_comment_bottom_sheet_dialog_input_hint),
                    )
                }
            },
            containerColor = SGColor.white,
        ) { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                verticalArrangement = Arrangement.spacedBy(28.dp),
                contentPadding = PaddingValues(top = 16.dp),
            ) {
                items(10) {
                    HomePostCommentScreen()
                    if (true) {
                        HomePostNestedCommentScreen()
                    }
                }
            }
        }
    }
}

@DevicePreviews
@Composable
private fun HomePostCommentBottomSheetDialogPreview() {
    HomePostCommentBottomSheetDialog(
        comment = "asdads",
    )
}
