package com.captures2024.soongan.feature.home.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.core.designsystem.component.HeightSpacer
import com.captures2024.soongan.core.designsystem.component.NonScaleText
import com.captures2024.soongan.core.designsystem.icon.MyIconPack
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonFillInfo
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonFillRightArrow
import com.captures2024.soongan.core.designsystem.theme.PrimaryA
import com.captures2024.soongan.feature.home.ui.component.HomeGalleryButton

@Composable
internal fun HomeScreenFooter(
    modifier: Modifier = Modifier,
    onClickInfo: () -> Unit,
    onClickRightArrow: () -> Unit
) {
    Box(
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconBox(
                onClick = onClickInfo,
                imageVector = MyIconPack.IconNonFillInfo,
                text = stringResource(id = com.captures2024.soongan.feature.home.R.string.contest_info),
                width = 24.dp,
                height = 24.dp
            )
            IconBox(
                onClick = onClickRightArrow,
                imageVector = MyIconPack.IconNonFillRightArrow,
                text = stringResource(id = com.captures2024.soongan.feature.home.R.string.participated_photo),
                width = 20.dp,
                height = 16.dp
            )
        }
    }
}

@Composable
private fun IconBox(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    imageVector: ImageVector,
    text: String,
    width: Dp,
    height: Dp
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HomeGalleryButton(onClick = onClick) {
            Icon(
                imageVector = imageVector,
                contentDescription = text,
                modifier = Modifier.size(width = width, height = height),
                tint = PrimaryA
            )
        }
        HeightSpacer(4.dp)
        NonScaleText(
            text = text,
            fontSize = 13.sp
        )
    }
}

@Preview
@Composable
private fun HomeScreenFooterPreview() {
    Surface {
        HomeScreenFooter(
            onClickInfo = {},
            onClickRightArrow = {}
        )
    }
}