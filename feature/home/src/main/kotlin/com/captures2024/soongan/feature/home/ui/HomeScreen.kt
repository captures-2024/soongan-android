package com.captures2024.soongan.feature.home.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.core.design.R
import com.captures2024.soongan.core.designsystem.theme.dropShadow
import com.captures2024.soongan.core.designsystem.util.DevicePreviews
import com.captures2024.soongan.feature.home.R as Rhome

@Composable
internal fun HomeScreen(
    modifier: Modifier = Modifier
) {
    Image(
        modifier = Modifier.fillMaxSize(),
        painter = painterResource(id = R.drawable.background_home_gallery),
        contentScale = ContentScale.Crop,
        alpha = 0.8f,
        contentDescription = ""
    )
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(
                horizontal = 32.dp
            )
            .padding(top = 40.dp)
    ) {
        MainTopBar()
        Spacer(modifier = Modifier.height(68.dp))
        MainContent()
        Spacer(modifier = Modifier.height(32.dp))
        MainBottomBar()
    }
}

@Composable
private fun MainTopBar() {
    val black800 = colorResource(id = R.color.black_800)

    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        TitleBackground()
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "평화",
                fontSize = 42.sp,
                fontWeight = FontWeight.ExtraBold,
                color = black800,
                modifier = Modifier.padding(start = 4.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.logo),
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
    val color = colorResource(id = R.color.yellow_600)

    Box(
        modifier = Modifier.offset(x = (-20).dp, y = (-10).dp)
    ) {
        Canvas(
            modifier = Modifier
                .size(40.dp)
                .align(Alignment.Center)
        ) {
            drawCircle(
                color = color, radius = size.width / 2
            )
        }
    }
}


@Composable
private fun MainContent() {
    val black800 = colorResource(id = R.color.black_800)

    Column(
        modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface(
            modifier = Modifier
                .size(257.dp)
                .background(Color.White)
                .dropShadow(shape = RectangleShape)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                IconButton(
                    onClick = { /*TODO*/ }, modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_plus),
                        contentDescription = "exhibit",
                        modifier = Modifier.size(40.dp)
                    )
                }
                Text(
                    text = stringResource(id = Rhome.string.exhibit),
                    color = black800,
                    fontSize = 14.sp
                )
            }
        }
        Spacer(modifier = Modifier.height(52.dp))
        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End
        ) {
            WeeklyDailySelector()
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
        contestPeriodText(stringResource(id = Rhome.string.start_date), "2024.05.10")
        Spacer(modifier = Modifier.height(8.dp))
        contestPeriodText(stringResource(id = Rhome.string.end_date), "2024.05.10")
    }
}

@Composable
private fun WeeklyDailyBtn(periodText: String, selected: Boolean, onClick: () -> Unit) {
    Button(
        onClick = onClick, colors = ButtonDefaults.buttonColors(
            containerColor = if (selected) colorResource(id = R.color.black_800) else Color.White,
            contentColor = if (selected) Color.White else colorResource(id = R.color.black_400)
        ), modifier = Modifier
            .width(79.dp)
            .height(32.dp), shape = RoundedCornerShape(4.dp)
    ) {
        Text(
            text = periodText,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}


@Composable
private fun contestPeriodText(text: String, period: String) {
    val black800 = colorResource(id = R.color.black_800)

    Text(
        text = "$text | $period", color = black800, fontSize = 14.sp
    )
}

@Composable
private fun MainBottomBar() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()
    ) {
        MainBottomIcon(
            {}, R.drawable.ic_contest_info, stringResource(id = Rhome.string.contest_info)
        )
        MainBottomIcon(
            {}, R.drawable.ic_next_page, stringResource(id = Rhome.string.participated_photo)
        )
    }
}

@Composable
private fun MainBottomIcon(onClick: () -> Unit, iconResource: Int, iconDescription: String) {
    val black800 = colorResource(id = R.color.black_800)

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        IconButton(
            onClick = { onClick }, modifier = Modifier
                .padding(0.dp)
                .size(44.dp)
                .dropShadow(
                    shape = CircleShape, offsetY = 1.dp, offsetX = 0.dp, blur = 4.dp, spread = 0.dp
                )
        ) {
            Image(
                imageVector = ImageVector.vectorResource(iconResource),
                contentDescription = iconDescription,
                modifier = Modifier
                    .padding(0.dp)
                    .fillMaxSize()

            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = iconDescription, fontSize = 12.sp, color = black800
        )
    }
}

@DevicePreviews
@Composable
private fun HomeScreenPreview() {
    HomeScreen()
}

enum class ContestPeriod {
    DAILY, WEEKLY
}