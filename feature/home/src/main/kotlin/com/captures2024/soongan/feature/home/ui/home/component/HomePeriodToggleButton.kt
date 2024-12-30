package com.captures2024.soongan.feature.home.ui.home.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.core.designsystem.component.NonScaleText
import com.captures2024.soongan.core.designsystem.theme.SGColor
import com.captures2024.soongan.feature.home.R

@Composable
internal fun HomePeriodToggleButton(
    selected: Boolean,
    onClick: () -> Unit,
) {
    val periodText = stringResource(id = if (selected) R.string.daily else R.string.weekly)
    val containerColor = when (selected) {
        true -> SGColor.primaryA
        false -> SGColor.white
    }
    val contentColor = when (selected) {
        true -> SGColor.white
        false -> SGColor.tempPrimaryC
    }

    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor,
        ),
        modifier = Modifier
            .width(79.dp)
            .height(32.dp),
        shape = RoundedCornerShape(4.dp),
        contentPadding = PaddingValues(0.dp),
    ) {
        NonScaleText(
            text = periodText,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}