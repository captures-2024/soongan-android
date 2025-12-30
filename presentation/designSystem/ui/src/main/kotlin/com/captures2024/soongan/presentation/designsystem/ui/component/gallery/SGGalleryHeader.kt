package com.captures2024.soongan.presentation.designsystem.ui.component.gallery

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.presentation.designsystem.icon.MyIconPack
import com.captures2024.soongan.presentation.designsystem.icon.myiconpack.IconNonFillFillter
import com.captures2024.soongan.presentation.designsystem.icon.myiconpack.IconNonFillLeftArrow
import com.captures2024.soongan.presentation.designsystem.ui.component.WidthSpacer
import com.captures2024.soongan.presentation.designsystem.ui.component.button.SGIconCircleButton
import com.captures2024.soongan.presentation.designsystem.ui.component.text.SGText
import com.captures2024.soongan.presentation.designsystem.ui.component.text.getSGNonScaleTextStyle
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGColor
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGTypography
import com.captures2024.soongan.presentation.designsystem.ui.util.DevicePreviews

@Composable
fun SGGalleryHeader(
    modifier: Modifier = Modifier,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    content: @Composable BoxScope.() -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(84.dp)
            .padding(top = 20.dp, bottom = 24.dp)
            .padding(horizontal = 20.dp),
        contentAlignment = Alignment.Center,
    ) {
        if (leadingIcon != null) {
            Box(
                modifier = Modifier
                    .align(Alignment.CenterStart),
            ) {
                leadingIcon()
            }
        }

        content()

        if (trailingIcon != null) {
            Box(
                modifier = Modifier
                    .align(Alignment.CenterEnd),
            ) {
                trailingIcon()
            }
        }
    }
}

private const val TITLE_DELIMITER = "|"

@Composable
fun SGGalleryHeaderTitle(
    prefix: String,
    suffix: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
    ) {
        SGText(
            text = prefix,
            style = getSGNonScaleTextStyle(
                color = SGColor.primaryA,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 20.sp,
                fontFamily = SGTypography.pretendard,
                letterSpacing = (-5).em,
            ),
        )

        WidthSpacer(8.dp)

        SGText(
            text = TITLE_DELIMITER,
            style = getSGNonScaleTextStyle(
                color = SGColor.primaryA,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 20.sp,
                fontFamily = SGTypography.pretendard,
                letterSpacing = 0.em,
            ),
        )

        WidthSpacer(8.dp)

        SGText(
            text = suffix,
            style = getSGNonScaleTextStyle(
                color = SGColor.primaryA,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 20.sp,
                fontFamily = SGTypography.pretendard,
                letterSpacing = (-5).em,
            ),
        )
    }
}

@DevicePreviews
@Composable
private fun SGGalleryHeader_Preview() {
    SGGalleryHeader(
        leadingIcon = {
            SGIconCircleButton(
                imageVector = MyIconPack.IconNonFillLeftArrow,
                contentDescription = "back",
                iconWidth = 20.dp,
                iconHeight = 16.dp,
                onClick = {},
            )
        },
        trailingIcon = {
            SGIconCircleButton(
                imageVector = MyIconPack.IconNonFillFillter,
                contentDescription = "filter",
                onClick = {},
            )
        },
    ) {
        SGGalleryHeaderTitle(
            prefix = "1회차",
            suffix = "평화",
        )
    }
}
