package com.captures2024.soongan.presentation.feature.main.profile.component.profile

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.captures2024.soongan.presentation.designsystem.icon.MyIconPack
import com.captures2024.soongan.presentation.designsystem.icon.myiconpack.IconNonFillBell
import com.captures2024.soongan.presentation.designsystem.icon.myiconpack.IconNonFillMenu
import com.captures2024.soongan.presentation.designsystem.ui.component.HeightSpacer
import com.captures2024.soongan.presentation.designsystem.ui.component.WeightSpacer
import com.captures2024.soongan.presentation.designsystem.ui.component.WidthSpacer
import com.captures2024.soongan.presentation.designsystem.ui.component.text.SGText
import com.captures2024.soongan.presentation.designsystem.ui.component.text.getSGNonScaleTextStyle
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGColor
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGTheme
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGTypography
import com.captures2024.soongan.presentation.designsystem.ui.util.DevicePreviews
import com.captures2024.soongan.presentation.feature.main.profile.R
import com.captures2024.soongan.presentation.viewmodel.model.UserProfile
import com.captures2024.soongan.presentation.designsystem.ui.R as RDesign

@Composable
internal fun ProfileTopBarComponent(
    userProfile: UserProfile,
    isShowBadge: Boolean,
    modifier: Modifier = Modifier,
    onClickNotification: () -> Unit,
    onClickMenu: () -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        ProfileCard(
            userProfile = userProfile,
            modifier = Modifier.padding(top = 8.dp),
        )

        WeightSpacer(1f)

        IconBox(
            isShowBadge = isShowBadge,
            onClickNotification = onClickNotification,
            onClickMenu = onClickMenu,
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
            contentDescription = stringResource(R.string.image_description),
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape),
            placeholder = painterResource(RDesign.drawable.ic_border_profile),
            error = painterResource(RDesign.drawable.ic_border_profile),
            contentScale = ContentScale.Crop,
        )

        WidthSpacer(16.dp)

        Column {
            SGText(
                text = userProfile.nickname,
                style = getSGNonScaleTextStyle(
                    color = SGColor.Grayscale.black100,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    lineHeight = 20.sp,
                    fontFamily = SGTypography.pretendard,
                    letterSpacing = (-5).em,
                ),
            )

            HeightSpacer(8.dp)

            SGText(
                text = userProfile.selfIntroduction,
                style = getSGNonScaleTextStyle(
                    color = SGColor.Grayscale.black100,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    lineHeight = 12.sp,
                    fontFamily = SGTypography.pretendard,
                    letterSpacing = (-5).em,
                ),
            )
        }
    }
}

@Composable
private fun IconBox(
    isShowBadge: Boolean,
    modifier: Modifier = Modifier,
    onClickNotification: () -> Unit,
    onClickMenu: () -> Unit,
) {
    Row(modifier = modifier) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clickable(onClick = onClickNotification),
            contentAlignment = Alignment.Center,
        ) {
            if (isShowBadge) {
                Badge(
                    modifier = Modifier
                        .size(8.dp)
                        .offset(
                            x = (-6).dp,
                            y = (-6).dp,
                        ),
                    containerColor = Color(0xffFBC304),
                )
            }

            Icon(
                imageVector = MyIconPack.IconNonFillBell,
                contentDescription = stringResource(R.string.notification_description),
            )
        }
        WidthSpacer(8.dp)
        Box(
            modifier = Modifier
                .size(40.dp)
                .clickable(onClick = onClickMenu),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                imageVector = MyIconPack.IconNonFillMenu,
                contentDescription = stringResource(R.string.menu_description),
                modifier = Modifier.size(20.dp),
            )
        }
    }
}

@DevicePreviews
@Composable
private fun PreviewProfileTopBarComponent() {
    SGTheme {
        ProfileTopBarComponent(
            userProfile = UserProfile(),
            isShowBadge = true,
            onClickNotification = {},
            onClickMenu = {},
        )
    }
}
