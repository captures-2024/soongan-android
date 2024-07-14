package com.captures2024.soongan.feature.home.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.core.design.R
import com.captures2024.soongan.core.designsystem.component.NonScaleText
import com.captures2024.soongan.core.designsystem.icon.MyIconPack
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconContestInfo
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNext
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconPlus
import com.captures2024.soongan.core.designsystem.icon.myiconpack.Logo
import com.captures2024.soongan.core.designsystem.theme.Accent
import com.captures2024.soongan.core.designsystem.theme.PrimaryA
import com.captures2024.soongan.core.designsystem.theme.PrimaryC
import com.captures2024.soongan.core.designsystem.theme.dropShadow
import com.captures2024.soongan.core.designsystem.util.DevicePreviews
import com.captures2024.soongan.feature.home.R as Rhome

const val MAX_EXHIBIT_CNT = 3;

@Composable
internal fun HomeScreen(
    modifier: Modifier = Modifier,
    navigateToFeed: () -> Unit,
) {
    Image(
        modifier = Modifier.fillMaxSize(),
        painter = painterResource(id = R.drawable.background_home_gallery),
        contentScale = ContentScale.Crop,
        alpha = 0.8f,
        contentDescription = "background"
    )
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 40.dp)
    ) {
        MainTopBar()
        Spacer(modifier = Modifier.height(68.dp))
        MainContent()
        Spacer(modifier = Modifier.height(32.dp))
        MainBottomBar(navigateToFeed)
    }
}

@Composable
private fun MainTopBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 32.dp
            )
    ) {
        TitleBackground()
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            NonScaleText(
                text = "평화",
                fontSize = 42.sp,
                fontWeight = FontWeight.ExtraBold,
                color = PrimaryA,
                modifier = Modifier.padding(start = 4.dp)
            )
            Image(
                imageVector = MyIconPack.Logo,
                contentDescription = "logo",
                modifier = Modifier
                    .width(33.1f.dp)
                    .height(50.1f.dp)
            )
        }
    }
}

@Composable
private fun TitleBackground() {
    Box(
        modifier = Modifier.offset(x = (-20).dp, y = (-10).dp)
    ) {
        Canvas(
            modifier = Modifier
                .size(40.dp)
                .align(Alignment.Center)
        ) {
            drawCircle(
                color = Accent, radius = size.width / 2
            )
        }
    }
}

@Composable
private fun MainContent() {
    Column(
        verticalArrangement = Arrangement.Center
    ) {
        ExhibitPhoto()
    }
    Spacer(modifier = Modifier.height(52.dp))
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 32.dp
            ), horizontalArrangement = Arrangement.End
    ) {
        WeeklyDailySelector()
    }
}


@Composable
private fun ExhibitPhoto() {
    val exhibitPhoto =
        remember { mutableListOf<Int>(R.drawable.test, R.drawable.test2, R.drawable.test3) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(257.dp)
            .horizontalScroll(rememberScrollState())
            .padding(
                horizontal = 32.dp
            ), horizontalArrangement = Arrangement.Center
    ) {
        when (exhibitPhoto.toList().size) {
            0 -> {
                ExhibitBtn(width = 257, exhibitPhoto.toList().size)
            }

            else -> {
                ExhibitBtn(width = 60, exhibitPhoto.toList().size)
            }
        }
        exhibitPhoto.toList().forEach { photo ->
            Spacer(modifier = Modifier.width(16.dp))
            Image(
                painter = painterResource(id = photo),
                contentDescription = "photo",
                contentScale = ContentScale.Inside,
                modifier = Modifier
                    .padding(0.dp)
                    .fillMaxHeight()
            )
        }
    }
}

@Composable
private fun ExhibitBtn(
    width: Int, exhibitCnt: Int
) {
    Surface(
        modifier = Modifier
            .width(width.dp)
            .height(257.dp)
            .dropShadow(shape = RectangleShape),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = when (exhibitCnt) {
                        MAX_EXHIBIT_CNT -> PrimaryC
                        else -> Color.White
                    }
                ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = MyIconPack.IconPlus,
                contentDescription = "exhibit",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .clickable { }
                    .size(
                        when (exhibitCnt) {
                            0 -> 40.dp
                            else -> 28.dp
                        }
                    ),
                tint = when (exhibitCnt) {
                    MAX_EXHIBIT_CNT -> Color.White
                    else -> Color.Black
                }

            )

            if (exhibitCnt == 0) {
                Spacer(modifier = Modifier.height(8.dp))
                NonScaleText(
                    text = stringResource(id = Rhome.string.exhibit),
                    style = TextStyle(fontSize = 14.sp, color = PrimaryA)
                )
            }
        }
        if (exhibitCnt > 0) {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                NonScaleText(
                    text = "$exhibitCnt/$MAX_EXHIBIT_CNT",
                    color = when (exhibitCnt) {
                        MAX_EXHIBIT_CNT -> Color.White
                        else -> PrimaryA
                    },
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 24.dp),
                )
            }
        }
    }
}


@Composable
private fun WeeklyDailySelector() {
    var contestPeriod by remember { mutableStateOf(ContestPeriod.DAILY) }

    Column(
        horizontalAlignment = Alignment.End
    ) {
        Row(
            modifier = Modifier
                .dropShadow(shape = RectangleShape)
                .background(color = Color.White, shape = RoundedCornerShape(4.dp))
        ) {
            WeeklyDailyBtn(
                stringResource(id = Rhome.string.daily), contestPeriod == ContestPeriod.DAILY
            ) { contestPeriod = ContestPeriod.DAILY }
            WeeklyDailyBtn(
                stringResource(id = Rhome.string.weekly), contestPeriod == ContestPeriod.WEEKLY
            ) { contestPeriod = ContestPeriod.WEEKLY }
        }
        Spacer(modifier = Modifier.height(16.dp))
        ContestPeriodText(stringResource(id = Rhome.string.start_date), "2024.05.10")
        ContestPeriodText(stringResource(id = Rhome.string.end_date), "2024.05.10")
    }
}

@Composable
private fun WeeklyDailyBtn(
    periodText: String,
    selected: Boolean,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (selected) PrimaryA else Color.White,
            contentColor = if (selected) Color.White else PrimaryC
        ),
        modifier = Modifier
            .width(79.dp)
            .height(32.dp),
        shape = RoundedCornerShape(4.dp),
        contentPadding = PaddingValues(0.dp),
    ) {
        NonScaleText(
            text = periodText,
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            ),
            textAlign = TextAlign.Center
        )
    }
}


@Composable
private fun ContestPeriodText(
    text: String,
    period: String,
) {
    NonScaleText(
        text = "$text | $period",
        style = TextStyle(fontSize = 15.sp, color = PrimaryA, fontWeight = FontWeight.SemiBold)
    )
}

@Composable
private fun MainBottomBar(navigateToFeed: () -> Unit) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 32.dp
            )
    ) {
        MainBottomIcon(
            {}, MyIconPack.IconContestInfo, stringResource(id = Rhome.string.contest_info)
        )
        MainBottomIcon(
            navigateToFeed,
            MyIconPack.IconNext,
            stringResource(id = Rhome.string.participated_photo)
        )
    }
}

@Composable
private fun MainBottomIcon(
    onClick: () -> Unit,
    icon: ImageVector,
    iconDescription: String,
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        IconButton(
            onClick = onClick, modifier = Modifier
                .size(34.dp)
                .dropShadow(
                    shape = CircleShape,
                    offsetY = 2.dp,
                    offsetX = 0.dp,
                    blur = 4.dp,
                    spread = 0.dp
                )
                .background(Color.White, CircleShape)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = iconDescription,
                modifier = Modifier.size(24.dp)
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        NonScaleText(
            text = iconDescription,
            style = TextStyle(fontSize = 13.sp, color = PrimaryA)
        )
    }
}

@DevicePreviews
@Composable
private fun HomeScreenPreview() {
}

enum class ContestPeriod {
    DAILY, WEEKLY
}