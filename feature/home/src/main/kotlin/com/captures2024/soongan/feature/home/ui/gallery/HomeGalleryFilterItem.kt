package com.captures2024.soongan.feature.home.ui.gallery

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.core.designsystem.component.NonScaleText
import com.captures2024.soongan.core.designsystem.icon.MyIconPack
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconFilterLike
import com.captures2024.soongan.core.designsystem.theme.PrimaryA
import com.captures2024.soongan.core.designsystem.theme.PrimaryC
import com.captures2024.soongan.core.designsystem.util.DevicePreviews
import com.captures2024.soongan.feature.home.R

@Composable
internal fun HomeGalleryFilterItem(
    modifier: Modifier = Modifier,
    text: String,
    icon: ImageVector,
    selected: Boolean,
    onClickItem: () -> Unit = {},
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .clickable { onClickItem() }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(horizontal = 19.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            NonScaleText(
                text = text,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = when (selected) {
                    true -> PrimaryA
                    false -> PrimaryC
                }
            )
            Icon(
                modifier = Modifier.size(26.dp),
                imageVector = icon,
                contentDescription = text,
                tint = when (selected) {
                    true -> PrimaryA
                    false -> PrimaryC
                }
            )
        }
    }
}

@DevicePreviews
@Composable
private fun HomeGalleryFilterItemPreview() {
    HomeGalleryFilterItem(
        text = stringResource(id = R.string.filter_likes),
        icon = MyIconPack.IconFilterLike,
        selected = false
    )
}