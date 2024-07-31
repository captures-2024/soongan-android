package com.captures2024.soongan.core.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.core.designsystem.icon.MyIconPack
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonFillTopArrow
import com.captures2024.soongan.core.designsystem.theme.NanumSquareNeoFontFamily
import com.captures2024.soongan.core.designsystem.theme.PrimaryA
import com.captures2024.soongan.core.designsystem.util.DevicePreviews
import com.captures2024.soongan.core.designsystem.util.nonScaleSp

@Composable
fun CommentInputTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (newValue: String) -> Unit,
    hint: String? = null,
    onSideBtnClick: (() -> Unit)? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Default,
    singleLine: Boolean = true,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()
    val focusRequester = remember { FocusRequester() }

    Box(
        modifier = modifier
            .heightIn(min = 40.dp)
            .wrapContentHeight(Alignment.CenterVertically)
            .border(
                width = 1.dp,
                color = PrimaryA.copy(alpha = 0.3f),
                shape = RoundedCornerShape(202.dp)
            )
            .focusRequester(focusRequester)
    ) {
        CommentInputBasicTextField(
            value = value,
            onValueChange = onValueChange,
            hint = hint,
            enable = true,
            onSideBtnClick = onSideBtnClick,
            interactionSource = interactionSource,
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
                imeAction = imeAction,
            ),
            singleLine = singleLine,
        )
    }
}

@Composable
private fun CommentInputBasicTextField(
    value: String,
    onValueChange: (String) -> Unit,
    hint: String? = null,
    enable: Boolean = true,
    onSideBtnClick: (() -> Unit)? = null,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Text,
        imeAction = ImeAction.Default,
    ),
    singleLine: Boolean = true,
) {
    BasicTextField(
        modifier = Modifier
            .heightIn(min = 40.dp)
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(start = 16.dp, end = 8.dp),
        enabled = enable,
        value = value,
        onValueChange = onValueChange,
        keyboardOptions = keyboardOptions,
        singleLine = singleLine,
        visualTransformation = VisualTransformation.None,
//        maxLines = 1,
        textStyle = TextStyle(
            fontFamily = NanumSquareNeoFontFamily,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal,
            fontSize = 14.sp.nonScaleSp,
            lineHeight = 14.sp.nonScaleSp
        ),
        interactionSource = interactionSource,
        decorationBox = @Composable { innerTextField ->
            Box(
                modifier = Modifier
                    .padding(top = 4.dp, end = 64.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                innerTextField()
            }

            Box(
                modifier = Modifier
                    .padding(top = 4.dp),
                contentAlignment = when (value.isEmpty()) {
                    true -> Alignment.CenterStart
                    false -> Alignment.CenterEnd
                },
            ) {
                when (value.isEmpty()) {
                    true -> if (hint != null) {
                        NonScaleText(
                            text = hint,
                            color = PrimaryA.copy(alpha = 0.3f),
                            fontSize = 14.sp,
                            lineHeight = 14.sp,
                            fontWeight = FontWeight.Normal,
                            fontFamily = NanumSquareNeoFontFamily
                        )
                    }

                    false -> Box(
                        modifier = Modifier
                            .width(48.dp)
                            .height(24.dp)
                            .background(
                                color = PrimaryA,
                                shape = RoundedCornerShape(20.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = MyIconPack.IconNonFillTopArrow,
                            contentDescription = "button",
                            modifier = Modifier.size(16.dp, 16.dp),
                            tint = Color.White
                        )
                    }
                }
            }
        },
    )
}

@DevicePreviews
@Composable
private fun CommentInputTextFieldPreview() {
    Column(
        modifier = Modifier.background(Color.White)
    ) {
        CommentInputTextField(
            modifier = Modifier.fillMaxWidth(),
            value = "",
            hint = "dasdsadsa",
            onValueChange = {}
        )
        CommentInputTextField(
            modifier = Modifier.fillMaxWidth(),
            value = "adsdsasdadsadsadsadsadsadsadsadsadsadsaddsadsadasadsdsasdadsadsadsadsadsadsadsadsadsadsaddsadsadasadsdsasdadsadsadsadsadsadsadsadsadsadsaddsadsadasadsdsasdadsadsadsadsadsadsadsadsadsadsaddsadsadasadsdsasdadsadsadsadsadsadsadsadsadsadsaddsadsadasadsdsasdadsadsadsadsadsadsadsadsadsadsaddsadsadasadsdsasdadsadsadsadsadsadsadsadsadsadsaddsadsadasadsdsasdadsadsadsadsadsadsadsadsadsadsaddsadsadas",
            onValueChange = {}
        )
    }
}