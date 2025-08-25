package com.captures2024.soongan.presentation.feature.main.awards.component.info

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.core.model.dto.awards.AwardsDetailDto
import com.captures2024.soongan.core.model.dto.awards.AwardsPostDto
import com.captures2024.soongan.core.model.enums.AwardsPostStatusType
import com.captures2024.soongan.presentation.designsystem.ui.component.text.SGText
import com.captures2024.soongan.presentation.designsystem.ui.component.text.getSGNonScaleTextStyle
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGColor
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGTypography
import com.captures2024.soongan.presentation.designsystem.ui.util.DevicePreviews
import com.captures2024.soongan.presentation.feature.main.awards.R

@Composable
internal fun AwardsInfoComponent(
    awardsInfo: AwardsDetailDto,
    modifier: Modifier = Modifier,
    onClickBack: () -> Unit,
    onClickPost: (postId: Long) -> Unit,
    onClickAllPosts: () -> Unit,
) {
    val gridPadding = 8.dp

    val firstPrizePost = remember(awardsInfo.prizePosts) {
        awardsInfo.prizePosts.first()
    }

    val otherTop7Posts = remember(awardsInfo.prizePosts) {
        if (awardsInfo.prizePosts.size > 1) {
            awardsInfo.prizePosts.subList(1, awardsInfo.prizePosts.lastIndex)
        } else {
            emptyList()
        }
    }

    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(gridPadding),
        verticalItemSpacing = gridPadding,
        contentPadding = PaddingValues(gridPadding),
    ) {
        item(span = StaggeredGridItemSpan.FullLine) {
            AwardsInfoTopBarComponent(
                onClickIcon = onClickBack,
            )
        }

        item(span = StaggeredGridItemSpan.FullLine) {
            AwardsInfoWinnerPostComponent(
                winnerPost = firstPrizePost,
                onClickPost = onClickPost,
            )
        }

        item(span = StaggeredGridItemSpan.FullLine) {
            AwardsInfoRoundInfoComponent(
                awardsInfo = awardsInfo,
            )
        }

        itemsIndexed(
            items = otherTop7Posts,
            key = { _, topPost -> topPost.postId },
        ) { index, topPost ->
            AwardsInfoTopPostComponent(
                topPost = topPost,
                isWinnerPost = false,
                onClickPost = onClickPost,
            )
        }

        item(span = StaggeredGridItemSpan.FullLine) {
            TempButton(
                onClick = onClickAllPosts,
            )
        }
    }
}

@Composable
private fun TempButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .heightIn(min = 48.dp)
            .padding(horizontal = 36.dp, vertical = 76.dp),
        shape = RoundedCornerShape(30.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = SGColor.Main.primary,
        ),
    ) {
        SGText(
            text = stringResource(R.string.awards_info_navigate_to_feed_text),
            style = getSGNonScaleTextStyle(
                color = SGColor.Main.secondary,
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                lineHeight = 20.sp,
                fontFamily = SGTypography.pretendard,
                letterSpacing = (-5).em,
            ),
        )
    }
}

@DevicePreviews
@Composable
private fun AwardsInfoComponent_Preview() {
    AwardsInfoComponent(
        awardsInfo = AwardsDetailDto(
            subject = "subject",
            round = 1L,
            startAt = "startAt",
            endAt = "endAt",
            postsCount = 30L,
            prizePosts = List(7) { index ->
                when (index) {
                    0 -> AwardsPostDto(
                        postId = 0,
                        title = "title_0",
                        imageUrl = "",
                        nickname = "nickname_0",
                        score = "0",
                        status = AwardsPostStatusType.ACTIVE,
                    )

                    else -> {
                        AwardsPostDto(
                            postId = index.toLong(),
                            title = "title_$index",
                            imageUrl = "",
                            nickname = "nickname_$index",
                            score = "$index",
                            status = AwardsPostStatusType.ACTIVE,
                        )
                    }
                }
            },
        ),
        onClickBack = {},
        onClickPost = {},
        onClickAllPosts = {},
    )
}
