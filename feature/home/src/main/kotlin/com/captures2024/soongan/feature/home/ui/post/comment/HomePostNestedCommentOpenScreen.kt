package com.captures2024.soongan.feature.home.ui.post.comment

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.core.designsystem.component.NonScaleText
import com.captures2024.soongan.core.designsystem.theme.NanumSquareNeoFontFamily
import com.captures2024.soongan.core.designsystem.theme.PrimaryA
import com.captures2024.soongan.core.designsystem.util.DevicePreviews

@Composable
internal fun HomePostNestedCommentOpenScreen(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(
                start = 52.dp,
                top = 16.dp,
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Column(
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                HomePostCommentScreen()
            }
            Row(
                modifier = Modifier.padding(top = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.width(44.dp))
                Spacer(modifier = Modifier.width(4.dp))
                HorizontalDivider(
                    modifier = Modifier.width(27.dp),
                    color = PrimaryA.copy(alpha = 0.9f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                NonScaleText(
                    text = "답글 숨기기",
                    color = PrimaryA.copy(alpha = 0.9f),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 16.sp,
                    fontFamily = NanumSquareNeoFontFamily,
                    modifier = Modifier.clickable(
                        onClick = onClick
                    )
                )
            }
        }
    }
}

@DevicePreviews
@Composable
private fun HomePostNestedCommentOpenScreenPreview() {
    HomePostNestedCommentOpenScreen()
}