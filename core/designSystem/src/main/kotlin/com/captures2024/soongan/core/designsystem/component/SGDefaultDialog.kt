package com.captures2024.soongan.core.designsystem.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.captures2024.soongan.core.designsystem.icon.MyIconPack
import com.captures2024.soongan.core.designsystem.icon.myiconpack.Logo
import com.captures2024.soongan.core.designsystem.theme.NanumSquareNeoFontFamily
import com.captures2024.soongan.core.designsystem.theme.SGColor
import com.captures2024.soongan.core.designsystem.theme.SoonGanTheme

@Composable
fun SGSingleButtonDialog(
    content: String,
    confirmContent: String,
    onClickConfirm: () -> Unit,
    onDismissRequest: () -> Unit,
    properties: DialogProperties = DialogProperties(
        dismissOnBackPress = false,
        dismissOnClickOutside = false,
        usePlatformDefaultWidth = true,
    ),
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = properties,
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
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                imageVector = MyIconPack.Logo,
                contentDescription = "logo",
                modifier = Modifier
                    .width(33.1f.dp)
                    .height(50.1f.dp)
            )

            NonScaleText(
                text = content,
                fontSize = 16.sp,
                fontFamily = NanumSquareNeoFontFamily,
                fontWeight = FontWeight.Bold,
                color = SGColor.black,
                lineHeight = 24.sp,
                textAlign = TextAlign.Center,
            )

            SoonGanButton(
                onClick = onClickConfirm,
                modifier = Modifier.fillMaxWidth(),
            ) {
                NonScaleText(
                    text = confirmContent,
                    fontSize = 14.sp,
                    fontFamily = NanumSquareNeoFontFamily,
                    fontWeight = FontWeight.Bold,
                    color = SGColor.black,
                )
            }
        }
    }
}

@Composable
fun SGDoubleButtonDialog(
    onDismissRequest: () -> Unit,
    properties: DialogProperties = DialogProperties(
        dismissOnBackPress = false,
        dismissOnClickOutside = false,
        usePlatformDefaultWidth = true,
    ),
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = properties,
    ) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .widthIn(min = 273.dp)
                .heightIn(min = 240.dp)
                .background(color = SGColor.white)
                .padding(vertical = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

        }
    }
}

@Preview
@Composable
private fun SGSingleButtonDialogPreview() {
    SoonGanTheme {
        Box(Modifier.fillMaxSize()) {
            SGSingleButtonDialog(
                content = "해당 기능은\n로그인이 필요한 기능입니다.",
                confirmContent = "확인",
                onClickConfirm = {},
                onDismissRequest = {},
            )
        }
    }
}