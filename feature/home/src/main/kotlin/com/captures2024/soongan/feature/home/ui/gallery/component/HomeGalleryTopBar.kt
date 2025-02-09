package com.captures2024.soongan.feature.home.ui.gallery.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.core.designsystem.component.SoonGanIconButton
import com.captures2024.soongan.core.designsystem.component.WidthSpacer
import com.captures2024.soongan.core.designsystem.component.text.SGText
import com.captures2024.soongan.core.designsystem.component.text.getSGNonScaleTextStyle
import com.captures2024.soongan.core.designsystem.icon.MyIconPack
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonFillFillter
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonFillLeftArrow
import com.captures2024.soongan.core.designsystem.theme.SGColor
import com.captures2024.soongan.core.designsystem.theme.SGTypography
import com.captures2024.soongan.core.designsystem.util.DevicePreviews
import com.captures2024.soongan.feature.home.R

@Composable
internal fun HomeGalleryTopBar(
    modifier: Modifier = Modifier,
    lazyStaggeredGridState: LazyStaggeredGridState = rememberLazyStaggeredGridState(),
    onBackPressed: () -> Unit = {},
    onClickFilter: () -> Unit = {},
) {
    var scrolledY = 0f
    var previousOffset = 0

    Row(
        modifier = modifier
            .graphicsLayer {
                scrolledY += lazyStaggeredGridState.firstVisibleItemScrollOffset - previousOffset
                translationY = scrolledY * 0.5f
                previousOffset = lazyStaggeredGridState.firstVisibleItemScrollOffset
            }
            .fillMaxWidth()
            .height(100.dp)
            .padding(20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        SoonGanIconButton(onClick = onBackPressed) {
            Icon(
                imageVector = MyIconPack.IconNonFillLeftArrow,
                contentDescription = "",
                tint = SGColor.primaryA,
                modifier = Modifier.size(
                    width = 20.dp,
                    height = 16.dp
                )
            )
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            SGText(
                text = stringResource(id = R.string.home_gallery_top_week_example),
                style = getSGNonScaleTextStyle(
                    color = SGColor.primaryA,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 20.sp,
                    fontFamily = SGTypography.nanumSquareNeo,
                    letterSpacing = (-5).em,
                ),
            )

            WidthSpacer(8.dp)

            SGText(
                text = stringResource(id = R.string.home_gallery_top_split),
                style = getSGNonScaleTextStyle(
                    color = SGColor.primaryA,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 20.sp,
                    fontFamily = SGTypography.poppins,
                    letterSpacing = 0.em,
                ),
            )

            WidthSpacer(8.dp)

            SGText(
                text = stringResource(id = R.string.home_gallery_top_topic_example),
                style = getSGNonScaleTextStyle(
                    color = SGColor.primaryA,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 20.sp,
                    fontFamily = SGTypography.nanumSquareNeo,
                    letterSpacing = (-5).em,
                ),
            )
        }

        SoonGanIconButton(onClick = onClickFilter) {
            Icon(
                imageVector = MyIconPack.IconNonFillFillter,
                contentDescription = "",
                tint = SGColor.primaryA,
                modifier = Modifier.size(
                    width = 20.dp,
                    height = 20.dp
                )
            )
        }
    }
}


@DevicePreviews
@Composable
private fun HomeGalleryTopBarPreview() {
    HomeGalleryTopBar()
}