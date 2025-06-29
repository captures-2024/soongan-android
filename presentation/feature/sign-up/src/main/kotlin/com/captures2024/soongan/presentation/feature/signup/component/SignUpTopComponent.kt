package com.captures2024.soongan.presentation.feature.signup.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.presentation.designsystem.icon.MyIconPack
import com.captures2024.soongan.presentation.designsystem.icon.myiconpack.IconNonFillBackArrow
import com.captures2024.soongan.presentation.designsystem.ui.component.WidthSpacer
import com.captures2024.soongan.presentation.designsystem.ui.component.background.SGBackground
import com.captures2024.soongan.presentation.designsystem.ui.component.button.SGIconButton
import com.captures2024.soongan.presentation.designsystem.ui.component.text.SGText
import com.captures2024.soongan.presentation.designsystem.ui.component.text.getSGNonScaleTextStyle
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGColor
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGTypography
import com.captures2024.soongan.presentation.designsystem.ui.util.DevicePreviews

@Composable
internal fun SignUpTopComponent(
    content: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Column {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(color = Color(0xFFFAFAF8))
                .padding(vertical = 18.dp)
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            SGIconButton(
                imageVector = MyIconPack.IconNonFillBackArrow,
                contentDescription = "back",
                color = SGColor.black100,
                modifier = Modifier.size(24.dp),
                onClick = onClick,
            )

            WidthSpacer(16.dp)

            SGText(
                text = content,
                style = getSGNonScaleTextStyle(
                    color = SGColor.black100,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Medium,
                    lineHeight = 32.sp,
                    fontFamily = SGTypography.pretendard,
                ),
            )
        }

        HorizontalDivider(color = SGColor.black60)
    }
}

@DevicePreviews
@Composable
private fun PreviewSignUpTopComponent() {
    SGBackground {
        SignUpTopComponent(
            content = "회원가입",
            onClick = {},
        )
    }
}
