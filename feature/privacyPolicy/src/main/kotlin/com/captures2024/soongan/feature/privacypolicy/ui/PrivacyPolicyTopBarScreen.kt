package com.captures2024.soongan.feature.privacypolicy.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.core.designsystem.component.NonScaleText
import com.captures2024.soongan.core.designsystem.util.DevicePreviews

@Composable
internal fun PrivacyPolicyTopBarScreen(
    modifier: Modifier = Modifier,
    onClickBack: () -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(Color.Black),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Spacer(modifier = Modifier.width(8.dp))
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "",
            tint = Color.White,
            modifier = Modifier.clickable(onClick = onClickBack)
        )
        Spacer(modifier = Modifier.width(8.dp))
        NonScaleText(
            text = "PrivacyPolicyTopBarScreen",
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight(500)
        )
    }
}

@DevicePreviews
@Composable
private fun PrivacyPolicyTopBarScreenPreview() {
    PrivacyPolicyTopBarScreen(onClickBack = {})
}