package com.captures2024.soongan.feature.profile.ui.profile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Badge
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.captures2024.soongan.core.designsystem.component.HeightSpacer
import com.captures2024.soongan.core.designsystem.component.NonScaleText
import com.captures2024.soongan.core.designsystem.component.WeightSpacer
import com.captures2024.soongan.core.designsystem.component.WidthSpacer
import com.captures2024.soongan.core.designsystem.icon.MyIconPack
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonFillBell
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonFillMenu
import com.captures2024.soongan.core.designsystem.theme.NanumSquareNeoFontFamily
import com.captures2024.soongan.core.designsystem.theme.PoppinsFontFamily
import com.captures2024.soongan.core.designsystem.util.DevicePreviews
import com.captures2024.soongan.core.model.UserProfile
import com.captures2024.soongan.core.design.R as RDesign

@Composable
internal fun ProfileScreenHeader(
    userProfile: UserProfile,
    modifier: Modifier = Modifier,
    onClickNotification: () -> Unit,
    onClickMenu: () -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
    ) {
        ProfileCard(
            userProfile = userProfile,
            modifier = Modifier.padding(top = 8.dp)
        )
        WeightSpacer(1f)
        IconBox(
            onClickNotification = onClickNotification,
            onClickMenu = onClickMenu
        )
    }
}

@Composable
private fun ProfileCard(
    userProfile: UserProfile,
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier) {
        AsyncImage(
            model = userProfile.profileImageUrl,
            contentDescription = null,
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape),
            placeholder = painterResource(RDesign.drawable.ic_border_profile),
            error = painterResource(RDesign.drawable.ic_border_profile),
            contentScale = ContentScale.Crop
        )
        WidthSpacer(16.dp)
        Column {
            NonScaleText(
                text = userProfile.nickname,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = PoppinsFontFamily,
                letterSpacing = (-5).em,
                lineHeight = 20.sp
            )
            HeightSpacer(8.dp)
            NonScaleText(
                text = userProfile.selfIntroduction,
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = NanumSquareNeoFontFamily,
                letterSpacing = (-5).em,
                lineHeight = 12.sp
            )
        }
    }
}

@Composable
private fun IconBox(
    modifier: Modifier = Modifier,
    onClickNotification: () -> Unit,
    onClickMenu: () -> Unit,
) {
    Row(modifier = modifier) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clickable { onClickNotification() },
            contentAlignment = Alignment.Center
        ) {
            Badge(
                modifier = Modifier
                    .size(8.dp)
                    .offset(x = (-6).dp, y = (-6).dp),
                containerColor = Color(0xffFBC304)
            )
            Icon(
                imageVector = MyIconPack.IconNonFillBell,
                contentDescription = "notification icon"
            )
        }
        WidthSpacer(8.dp)
        Box(
            modifier = Modifier
                .size(40.dp)
                .clickable { onClickMenu() },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = MyIconPack.IconNonFillMenu,
                contentDescription = "bottom sheet menu icon",
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

@DevicePreviews
@Composable
private fun ProfileScreenHeaderPreview() {
    ProfileScreenHeader(
        userProfile = UserProfile(),
        onClickNotification = {},
        onClickMenu = {}
    )
}