package com.captures2024.soongan.feature.signUp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.core.designsystem.component.NonScaleText
import com.captures2024.soongan.core.designsystem.icon.MyIconPack
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonFillBackArrow
import com.captures2024.soongan.core.designsystem.theme.PrimaryA
import com.captures2024.soongan.core.designsystem.theme.PrimaryB
import com.captures2024.soongan.core.designsystem.util.DevicePreviews
import com.captures2024.soongan.feature.signUp.R

@Composable
internal fun SignUpTopBar(
    modifier: Modifier = Modifier,
    onClickBack: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 60.dp)
            .background(color = PrimaryA)
            .padding(vertical = 20.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = MyIconPack.IconNonFillBackArrow,
            contentDescription = "",
            modifier = Modifier.size(
                width = 24.dp,
                height = 24.dp
            ).clickable {
                onClickBack()
            },
            tint = PrimaryB
        )
        Spacer(modifier = Modifier.width(16.dp))
        NonScaleText(
            text = stringResource(id = R.string.sign_up_text),
            color = PrimaryB,
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@DevicePreviews
@Composable
private fun SignUpTopBarPreview() {
    SignUpTopBar()
}