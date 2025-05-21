package com.captures2024.soongan.presentation.feature.main.home.component.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.core.designsystem.icon.MyIconPack
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonFillPlus
import com.captures2024.soongan.core.designsystem.ui.component.HeightSpacer
import com.captures2024.soongan.core.designsystem.ui.component.text.SGText
import com.captures2024.soongan.core.designsystem.ui.component.text.getSGNonScaleTextStyle
import com.captures2024.soongan.core.designsystem.ui.theme.SGColor
import com.captures2024.soongan.core.designsystem.ui.theme.SGTheme
import com.captures2024.soongan.core.designsystem.ui.theme.SGTypography
import com.captures2024.soongan.core.designsystem.ui.theme.dropShadow
import com.captures2024.soongan.core.designsystem.ui.util.DevicePreviews
import com.captures2024.soongan.presentation.feature.main.home.R

@Composable
internal fun HomeBodyEmptyComponent(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    val blur = 4.dp

    Box(
        modifier = modifier
            .padding(blur)
            .width(257.dp + blur)
            .height(257.dp + blur)
            .dropShadow(
                shape = RectangleShape,
                blur = blur,
            )
            .clickable(onClick = onClick),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = SGColor.Grayscale.white),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Icon(
                imageVector = MyIconPack.IconNonFillPlus,
                contentDescription = stringResource(R.string.register_button_description),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .size(40.dp),
                tint = SGColor.Grayscale.black100,
            )

            HeightSpacer(16.dp)

            SGText(
                text = stringResource(R.string.register_button_title),
                style = getSGNonScaleTextStyle(
                    color = SGColor.Grayscale.black100,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    lineHeight = 16.sp,
                    fontFamily = SGTypography.pretendard,
                    letterSpacing = 0.em,
                ),
            )
        }
    }
}

@DevicePreviews
@Composable
private fun PreviewHomeBodyEmptyComponent() {
    SGTheme {
        HomeBodyEmptyComponent(
            onClick = {},
        )
    }
}
