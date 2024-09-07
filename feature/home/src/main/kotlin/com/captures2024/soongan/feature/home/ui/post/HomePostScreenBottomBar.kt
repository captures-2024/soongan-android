package com.captures2024.soongan.feature.home.ui.post

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.core.designsystem.component.NonScaleText
import com.captures2024.soongan.core.designsystem.icon.MyIconPack
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconFillHeart
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonFillComment
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonFillMenu
import com.captures2024.soongan.core.designsystem.theme.PrimaryA
import com.captures2024.soongan.core.designsystem.theme.dropShadow
import com.captures2024.soongan.core.designsystem.util.DevicePreviews

@Composable
internal fun HomePostScreenBottomBar(
    modifier: Modifier = Modifier,
    onClickMenu: () -> Unit = {},
    onClickHeart: () -> Unit = {},
    onClickComment: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(83.dp)
            .dropShadow(
                shape = RoundedCornerShape(0.dp),
                color = Color(0x40000000),
                blur = 4.dp,
                offsetX = 0.dp,
                offsetY = (-2).dp
            )
            .background(color = Color.White)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier =  Modifier.size(
                width = 24.dp,
                height = 24.dp
            ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = MyIconPack.IconNonFillMenu,
                contentDescription = "menu",
                tint = PrimaryA,
                modifier = Modifier.size(
                    width = 4.dp,
                    height = 20.dp
                ).clickable(
                    onClick = onClickMenu
                )
            )
        }
        Row {
            Icon(
                imageVector = MyIconPack.IconFillHeart,
                contentDescription = "heart",
                tint = PrimaryA,
                modifier = Modifier.size(
                    width = 24.dp,
                    height = 21.dp
                ).clickable(
                    onClick = onClickHeart
                )
            )
            Spacer(modifier = Modifier.width(8.dp))
            NonScaleText(
                text = "1.2m",
                color = PrimaryA,
                fontSize = 12.sp,
                fontWeight = FontWeight.Light
            )
            Spacer(modifier = Modifier.width(31.dp))
            Icon(
                imageVector = MyIconPack.IconNonFillComment,
                contentDescription = "comment",
                tint = PrimaryA,
                modifier = Modifier
                    .size(
                        width = 24.dp,
                        height = 24.dp
                    )
                    .clickable(
                        onClick = onClickComment
                    ),
            )
            Spacer(modifier = Modifier.width(8.dp))
            NonScaleText(
                text = "1.2m",
                color = PrimaryA,
                fontSize = 12.sp,
                fontWeight = FontWeight.Light
            )
            Spacer(modifier = Modifier.width(15.dp))
        }
    }
}

@DevicePreviews
@Composable
private fun HomePostScreenBottomBarPreview() {
    HomePostScreenBottomBar()
}