package com.captures2024.soongan.presentation.feature.root.component.dialog

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.captures2024.soongan.presentation.designsystem.icon.MyIconPack
import com.captures2024.soongan.presentation.designsystem.icon.myiconpack.IconClose
import com.captures2024.soongan.presentation.designsystem.icon.myiconpack.Logo
import com.captures2024.soongan.presentation.designsystem.ui.component.button.SGIconButton
import com.captures2024.soongan.presentation.designsystem.ui.component.button.SGTextButtonType2
import com.captures2024.soongan.presentation.designsystem.ui.component.text.SGText
import com.captures2024.soongan.presentation.designsystem.ui.component.text.getSGNonScaleTextStyle
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGColor
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGTheme
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGTypography
import com.captures2024.soongan.presentation.designsystem.ui.util.DevicePreviews

@Composable
internal fun GuestModeDialog(
    content: String,
    confirmContent: String,
    onClickConfirm: () -> Unit,
    onDismissRequest: () -> Unit,
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false,
            usePlatformDefaultWidth = true,
        ),
    ) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .widthIn(min = 273.dp)
                .heightIn(min = 240.dp)
                .background(color = SGColor.white)
                .padding(vertical = 12.dp)
                .padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Box(modifier = Modifier.fillMaxWidth()) {
                Image(
                    imageVector = MyIconPack.Logo,
                    contentDescription = "logo",
                    modifier = Modifier
                        .width(33.1f.dp)
                        .height(50.1f.dp)
                        .align(Alignment.Center),
                )

                SGIconButton(
                    onClick = onDismissRequest,
                    modifier = Modifier.align(Alignment.CenterEnd),
                    defaultSize = 24.dp,
                ) {
                    Image(
                        imageVector = MyIconPack.IconClose,
                        contentDescription = "close",
                    )
                }
            }

            SGText(
                text = content,
                style = getSGNonScaleTextStyle(
                    color = SGColor.black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 24.sp,
                    fontFamily = SGTypography.pretendard,
                    letterSpacing = 0.em,
                    textAlign = TextAlign.Center,
                ),
            )

            SGTextButtonType2(
                text = confirmContent,
                modifier = Modifier.fillMaxWidth(),
                onClick = onClickConfirm,
            )
        }
    }
}

@DevicePreviews
@Composable
private fun PreviewGuestModeDialog() {
    SGTheme {
        GuestModeDialog(
            content = "content",
            confirmContent = "confirmContent",
            onClickConfirm = {},
            onDismissRequest = {},
        )
    }
}
