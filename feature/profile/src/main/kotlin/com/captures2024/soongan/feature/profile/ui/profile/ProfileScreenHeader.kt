package com.captures2024.soongan.feature.profile.ui.profile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Badge
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.captures2024.soongan.core.design.R as RDesign
import com.captures2024.soongan.core.designsystem.component.HeightSpacer
import com.captures2024.soongan.core.designsystem.component.NonScaleText
import com.captures2024.soongan.core.designsystem.component.WeightSpacer
import com.captures2024.soongan.core.designsystem.component.WidthSpacer
import com.captures2024.soongan.core.designsystem.icon.MyIconPack
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonFillBell
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonFillMenu
import com.captures2024.soongan.core.designsystem.util.DevicePreviews
import com.captures2024.soongan.feature.profile.R as RProfile

@Composable
internal fun ProfileScreenHeader(
    modifier: Modifier = Modifier,
    profileImageUrl: String,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 16.dp)
    ) {
        ProfileCard(profileImageUrl = profileImageUrl)
        WeightSpacer(1f)
        IconBox(
            onClickNotification = { TODO("Navigate notification screen") },
            onClickMenu = { TODO("Open bottom modal sheet") }
        )
    }
}

@Composable
private fun ProfileCard(
    modifier: Modifier = Modifier,
    profileImageUrl: String,
) {
    Row(modifier = modifier) {
        AsyncImage(
            model = profileImageUrl,
            contentDescription = "profile image",
            modifier = Modifier.size(60.dp),
            placeholder = painterResource(RDesign.drawable.ic_border_profile)
        )
        WidthSpacer(16.dp)
        Column {
            NonScaleText(
                text = "user1",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
            )
            HeightSpacer(8.dp)
            NonScaleText(
                text = stringResource(RProfile.string.default_self_introduction),
                fontSize = 12.sp,
                fontWeight = FontWeight.Light
            )
        }
    }
}

@Composable
fun IconBox(
    modifier: Modifier = Modifier,
    onClickNotification: () -> Unit,
    onClickMenu: () -> Unit,
) {
    Row(modifier = modifier) {
        Box(
            modifier = Modifier.clickable {
                onClickNotification()
            }
        ) {
            Badge(
                containerColor = Color(0xffFBC304)
            )
            Icon(
                imageVector = MyIconPack.IconNonFillBell,
                contentDescription = MyIconPack.IconNonFillBell.name,
            )
        }
        WidthSpacer(16.dp)
        Box(
            modifier = Modifier.clickable {
                onClickMenu()
            }
        ) {
            Icon(
                imageVector = MyIconPack.IconNonFillMenu,
                contentDescription = MyIconPack.IconNonFillMenu.name,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

@DevicePreviews
@Composable
private fun ProfileScreenHeaderPreview() {
    ProfileScreenHeader(profileImageUrl = "")
}