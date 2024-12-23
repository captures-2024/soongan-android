package com.captures2024.soongan.feature.home.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.captures2024.soongan.core.designsystem.component.HeightSpacer
import com.captures2024.soongan.core.designsystem.component.NonScaleText
import com.captures2024.soongan.core.designsystem.component.WidthSpacer
import com.captures2024.soongan.core.designsystem.icon.MyIconPack
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconFillHeart
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonFillComment
import com.captures2024.soongan.core.designsystem.theme.SGColor
import com.captures2024.soongan.core.designsystem.util.DevicePreviews
import com.captures2024.soongan.core.model.UserPost
import com.captures2024.soongan.feature.home.ui.home.component.HomeExhibitButton
import com.captures2024.soongan.core.design.R as RDesign

@Composable
internal fun HomeScreenBody(
    modifier: Modifier = Modifier,
    onClickPlus: () -> Unit,
    onClickMyPost: (UserPost.PhotoPost) -> Unit,
    myPosts: List<UserPost.PhotoPost>,
) {
    Column(
        modifier = modifier,
    ) {
        ExhibitContent(
            onClickPlus = onClickPlus,
            onClickMyPost = onClickMyPost,
            myPosts = myPosts
        )
    }
}

@Composable
private fun ExhibitContent(
    modifier: Modifier = Modifier,
    onClickPlus: () -> Unit,
    onClickMyPost: (UserPost.PhotoPost) -> Unit,
    myPosts: List<UserPost.PhotoPost>,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.Center,
    ) {
        HomeExhibitButton(
            onClick = onClickPlus,
            exhibitCount = myPosts.size
        )
        myPosts.forEach { myPost ->
            MyPostPhoto(
                modifier = Modifier.padding(horizontal = 4.dp),
                onClick = { onClickMyPost(myPost) },
                url = myPost.url,
            )
        }
    }
}

@Composable
private fun MyPostPhoto(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    url: String,
) {
    Column(modifier = modifier) {
        AsyncImage(
            model = url,
            contentDescription = "photo",
            contentScale = ContentScale.FillHeight,
            placeholder = painterResource(RDesign.drawable.test),
            modifier = Modifier
                .height(257.dp)
//                .dropShadow(shape = RectangleShape)
                .clickable { onClick() }
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.padding(start = 8.dp)
        ) {
            InteractionIconBox(
                imageVector = MyIconPack.IconFillHeart,
                contentDescription = "heart",
                interactionCount = "220"
            )
            WidthSpacer(8.dp)
            InteractionIconBox(
                imageVector = MyIconPack.IconNonFillComment,
                contentDescription = "comment",
                interactionCount = "220"
            )
        }
    }
}

@Composable
private fun InteractionIconBox(
    modifier: Modifier = Modifier,
    imageVector: ImageVector,
    contentDescription: String,
    interactionCount: String,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = contentDescription,
            modifier = Modifier.size(12.dp)
        )
        WidthSpacer(4.dp)
        NonScaleText(
            text = interactionCount,
            color = SGColor.primaryA,
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal,
            lineHeight = 12.sp
        )
    }
}


@DevicePreviews
@Composable
private fun HomeScreenBodyPreview() {
    // size 체크용
    val samples = List(3) { UserPost.PhotoPost(0, "", "") }

    Column(modifier = Modifier.background(SGColor.white)) {
        HomeScreenBody(
            onClickPlus = {},
            onClickMyPost = {},
            myPosts = emptyList()
        )
        HeightSpacer(20.dp)
        HomeScreenBody(
            onClickPlus = {},
            onClickMyPost = {},
            myPosts = samples
        )
    }
}