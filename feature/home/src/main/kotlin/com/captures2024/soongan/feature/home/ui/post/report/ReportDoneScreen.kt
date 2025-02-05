package com.captures2024.soongan.feature.home.ui.post.report

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.core.designsystem.component.SoonGanButton
import com.captures2024.soongan.core.designsystem.component.text.SGNonScaleTextStyle
import com.captures2024.soongan.core.designsystem.component.text.SGText
import com.captures2024.soongan.core.designsystem.theme.NanumSquareNeoFontFamily
import com.captures2024.soongan.core.designsystem.theme.PretendardFontFamily
import com.captures2024.soongan.core.designsystem.theme.SGColor
import com.captures2024.soongan.core.model.utils.ReportTargetType
import com.captures2024.soongan.feature.home.R

@Composable
internal fun ReportDoneScreen(
    targetType: ReportTargetType,
    hasExtraMessage: Boolean,
    modifier: Modifier = Modifier,
    onClickConfirm: () -> Unit,
) {
    val targetTypeText = when (targetType) {
        ReportTargetType.COMMENT -> stringResource(R.string.report_target_type_comment)
        else -> stringResource(R.string.report_target_type_post)
    }
    val doneMessage =
        "${stringResource(id = R.string.report_finish_text1)}$targetTypeText${stringResource(R.string.report_finish_text2)}".trimIndent()

    Column(
        modifier = modifier
            .padding(horizontal = 20.dp)
            .padding(bottom = 28.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 40.dp),
        ) {
            SGText(
                text = doneMessage,
                SGNonScaleTextStyle(
                    color = SGColor.primaryA,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    lineHeight = 24.sp,
                    fontFamily = NanumSquareNeoFontFamily,
                    letterSpacing = (-5).em
                ),
                modifier = Modifier.fillMaxWidth()
            )
            if (hasExtraMessage) {
                SGText(
                    text = "\n신고 내용의 구체적인 확인이 더 필요한 경우\n이메일로 연락드릴 예정입니다.",
                    SGNonScaleTextStyle(
                        color = SGColor.primaryA,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        lineHeight = 24.sp,
                        fontFamily = NanumSquareNeoFontFamily,
                        letterSpacing = (-5).em,
                        textDecoration = TextDecoration.Underline
                    ),
                )
            }
            SGText(
                text = "\n감사합니다.",
                SGNonScaleTextStyle(
                    color = SGColor.primaryA,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    lineHeight = 24.sp,
                    fontFamily = NanumSquareNeoFontFamily,
                    letterSpacing = (-5).em
                ),
            )
        }
        SoonGanButton(
            onClick = onClickConfirm,
            modifier = Modifier.fillMaxWidth()
        ) {
            SGText(
                text = stringResource(id = R.string.report_confirm_text),
                style = SGNonScaleTextStyle(
                    color = SGColor.primaryA,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    lineHeight = 20.sp,
                    fontFamily = PretendardFontFamily,
                    letterSpacing = (-5).em
                )
            )
        }
    }
}

@Preview(name = "no extra msg", showBackground = true)
@Composable
private fun ReportDoneScreen1Preview() {
    ReportDoneScreen(targetType = ReportTargetType.WEEKLY_POST, hasExtraMessage = false) {}
}

@Preview(name = "has extra msg", showBackground = true)
@Composable
private fun ReportDoneScreen2Preview() {
    ReportDoneScreen(targetType = ReportTargetType.WEEKLY_POST, hasExtraMessage = true) {}
}