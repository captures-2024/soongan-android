package com.captures2024.soongan.presentation.feature.main.awards.component.info

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.presentation.designsystem.icon.MyIconPack
import com.captures2024.soongan.presentation.designsystem.icon.myiconpack.Logo
import com.captures2024.soongan.presentation.designsystem.ui.component.text.SGText
import com.captures2024.soongan.presentation.designsystem.ui.component.text.getSGNonScaleTextStyle
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGColor
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGTheme
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGTypography
import com.captures2024.soongan.presentation.designsystem.ui.util.DevicePreviews

@Composable
internal fun AwardsInfoHiddenTopPostComponent(
    message: String,
    modifier: Modifier = Modifier,
    isWinnerPost: Boolean = false,
) {
    Column(
        modifier = modifier.fillMaxWidth()
            .height(
                when (isWinnerPost) {
                    true -> 240.dp
                    false -> 258.dp
                },
            )
            .border(
                width = 1.dp,
                color = SGColor.black40,
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
    ) {
        Image(
            imageVector = MyIconPack.Logo,
            contentDescription = "logo",
            modifier = Modifier
                .width(33.dp)
                .height(50.dp),
        )

        SGText(
            text = message,
            style = getSGNonScaleTextStyle(
                color = SGColor.black100,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 20.sp,
                fontFamily = SGTypography.pretendard,
                letterSpacing = (-5).em,
                textAlign = TextAlign.Center,
            ),
        )
    }
}

@DevicePreviews
@Composable
private fun PreviewAwardsInfoHiddenTopPostComponent() {
    SGTheme {
        AwardsInfoHiddenTopPostComponent(
            message = "reason",
        )
    }
}
