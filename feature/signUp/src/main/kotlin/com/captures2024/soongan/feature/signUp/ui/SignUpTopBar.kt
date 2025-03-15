package com.captures2024.soongan.feature.signUp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.core.designsystem.icon.MyIconPack
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonFillBackArrow
import com.captures2024.soongan.core.designsystem.ui.component.WidthSpacer
import com.captures2024.soongan.core.designsystem.ui.theme.SGColor
import com.captures2024.soongan.core.designsystem.ui.theme.SGTypography
import com.captures2024.soongan.core.designsystem.ui.component.button.SGIconButton
import com.captures2024.soongan.core.designsystem.ui.component.text.SGText
import com.captures2024.soongan.core.designsystem.ui.component.text.getSGNonScaleTextStyle
import com.captures2024.soongan.core.designsystem.ui.util.DevicePreviews
import com.captures2024.soongan.feature.signUp.R

@Composable
internal fun SignUpTopBar(
    modifier: Modifier = Modifier,
    onClickBack: () -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(color = SGColor.primaryA)
            .padding(vertical = 20.dp)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        SGIconButton(
            imageVector = MyIconPack.IconNonFillBackArrow,
            contentDescription = "back",
            color = SGColor.primaryB,
            onClick = onClickBack,
        )
        WidthSpacer(16.dp)
        SGText(
            text = stringResource(id = R.string.sign_up_text),
            style = getSGNonScaleTextStyle(
                color = SGColor.primaryB,
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium,
                lineHeight = 32.sp,
                fontFamily = SGTypography.pretendard,
            ),
        )
    }
}

@DevicePreviews
@Composable
private fun PreviewSignUpTopBar() {
    SignUpTopBar {}
}
