package com.captures2024.soongan.feature.home.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.core.design.R
import com.captures2024.soongan.core.designsystem.component.NonScaleText
import com.captures2024.soongan.core.designsystem.icon.MyIconPack
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconFillHeart
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonFillComment
import com.captures2024.soongan.core.designsystem.theme.PrimaryA
import com.captures2024.soongan.core.designsystem.util.DevicePreviews

@Composable
internal fun HomeScreenBody(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
                .padding(
                    horizontal = 32.dp
                ),
            horizontalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Image(
                    painter = painterResource(id = R.drawable.test),
                    contentDescription = "photo",
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .shadow(0.dp, clip = false)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.Top) {
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(
                        imageVector = MyIconPack.IconFillHeart,
                        contentDescription = "heart",
                        modifier = Modifier.size(12.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    NonScaleText(
                        text = "heartCnt",
                        color = PrimaryA,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        lineHeight = 12.sp
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Icon(
                        imageVector = MyIconPack.IconNonFillComment,
                        contentDescription = "comment",
                        modifier = Modifier.size(12.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    NonScaleText(
                        text = "commentCnt",
                        color = PrimaryA,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        lineHeight = 12.sp
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(32.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 32.dp
                ), horizontalArrangement = Arrangement.End
        ) {

        }
    }

}

@DevicePreviews
@Composable
private fun HomeScreenBodyPreview() {
    HomeScreenBody()
}