package com.captures2024.soongan.feature.home.ui.post.comment

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import com.captures2024.soongan.core.designsystem.ui.component.WidthSpacer
import com.captures2024.soongan.core.designsystem.ui.component.text.SGText
import com.captures2024.soongan.core.designsystem.ui.component.text.getSGNonScaleTextStyle
import com.captures2024.soongan.core.designsystem.ui.theme.SGColor
import com.captures2024.soongan.core.designsystem.ui.theme.SGTypography
import com.captures2024.soongan.core.designsystem.ui.util.DevicePreviews

@Composable
internal fun HomePostNestedCommentOpenScreen(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(
                start = 52.dp,
                top = 16.dp,
            ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column {
            Column(
                verticalArrangement = Arrangement.spacedBy(20.dp),
            ) {
                HomePostCommentScreen()
            }
            Row(
                modifier = Modifier.padding(top = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                WidthSpacer(44.dp)

                WidthSpacer(4.dp)

                HorizontalDivider(
                    modifier = Modifier.width(27.dp),
                    color = SGColor.primaryA.copy(alpha = 0.9f),
                )

                WidthSpacer(8.dp)

                SGText(
                    text = "답글 숨기기",
                    style = getSGNonScaleTextStyle(
                        color = SGColor.primaryA.copy(alpha = 0.9f),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 16.sp,
                        fontFamily = SGTypography.nanumSquareNeo,
                    ),
                    modifier = Modifier.clickable(
                        onClick = onClick,
                    ),
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
