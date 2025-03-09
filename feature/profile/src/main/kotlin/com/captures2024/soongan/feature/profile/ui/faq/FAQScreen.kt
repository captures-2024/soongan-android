package com.captures2024.soongan.feature.profile.ui.faq

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.core.designsystem.ui.component.HeightSpacer
import com.captures2024.soongan.core.designsystem.ui.component.WeightSpacer
import com.captures2024.soongan.core.designsystem.ui.component.text.SGText
import com.captures2024.soongan.core.designsystem.ui.component.text.getSGNonScaleTextStyle
import com.captures2024.soongan.core.designsystem.ui.theme.NanumSquareNeoFontFamily
import com.captures2024.soongan.core.designsystem.ui.theme.SGColor
import com.captures2024.soongan.core.designsystem.ui.util.DevicePreviews
import com.captures2024.soongan.feature.profile.R
import com.captures2024.soongan.feature.profile.ui.component.CustomTabRow
import com.captures2024.soongan.feature.profile.ui.component.CustomTopBar
import com.captures2024.soongan.feature.profile.ui.faq.model.FAQData

@Composable
internal fun FAQScreen(
    modifier: Modifier = Modifier,
    navigateToBack: () -> Unit,
) {
    val faqCategories = listOf(FAQData.Default, FAQData.Contest, FAQData.Copyright)
    val tabs = faqCategories.map { stringResource(it.titleId) }
    val pagerState = rememberPagerState(
        pageCount = { tabs.size },
        initialPageOffsetFraction = 0f,
        initialPage = 0,
    )
    val scrollState = rememberScrollState()

    Column(modifier = modifier.fillMaxSize()) {
        CustomTopBar(
            text = stringResource(R.string.faq_topbar_title),
            onBackPressed = navigateToBack
        )
        CustomTabRow(
            tabs = tabs,
            pagerState = pagerState,
            modifier = modifier,
        )
        HorizontalPager(
            state = pagerState,
            userScrollEnabled = false,
        ) { page ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = SGColor.white)
                    .verticalScroll(state = scrollState)
            ) {
                faqCategories[page].faqs.forEach {
                    FAQItem(
                        question = stringResource(it.question),
                        answer = stringResource(it.answer)
                    )
                }
                WeightSpacer(1f)
                InquiryBox(modifier = Modifier.padding(top = 48.dp, bottom = 64.dp)) {}
            }
        }
    }
}

@Composable
private fun FAQItem(
    question: String,
    answer: String,
    modifier: Modifier = Modifier,
) {
    var expanded by remember { mutableStateOf(false) }

    Column(modifier = modifier.padding(horizontal = 24.dp)) {
        Box(
            modifier = Modifier
                .height(60.dp)
                .fillMaxWidth()
                .clickable { expanded = !expanded },
            contentAlignment = Alignment.CenterStart
        ) {
            SGText(
                text = question,
                modifier = Modifier.padding(horizontal = 8.dp),
                style = getSGNonScaleTextStyle(
                    color = SGColor.primaryA,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 20.sp,
                    fontFamily = NanumSquareNeoFontFamily,
                    letterSpacing = (-5).em,
                ),
                maxLines = if (!expanded) 1 else Int.MAX_VALUE,
                overflow = if (!expanded) TextOverflow.Ellipsis else TextOverflow.Visible
            )
        }
        if (expanded) {
            HeightSpacer(12.dp)
            SGText(
                text = answer,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .padding(bottom = 20.dp),
                style = getSGNonScaleTextStyle(
                    color = SGColor.primaryA,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 20.sp,
                    fontFamily = NanumSquareNeoFontFamily,
                    letterSpacing = (-5).em,
                )
            )
        }
        HorizontalDivider(thickness = 1.dp, color = SGColor.buttonDisableGray)
    }
}

@Composable
private fun InquiryBox(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    val commonTextStyle = getSGNonScaleTextStyle(
        color = SGColor.primaryA,
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        lineHeight = 12.sp,
        fontFamily = NanumSquareNeoFontFamily,
    )

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SGText(
            text = stringResource(R.string.faq_inquiry_question_message),
            style = commonTextStyle
        )
        HeightSpacer(12.dp)
        SGText(
            text = stringResource(R.string.faq_inquiry_text),
            modifier = Modifier.clickable(onClick = onClick),
            style = commonTextStyle.copy(textDecoration = TextDecoration.Underline)
        )
    }
}

@DevicePreviews
@Composable
private fun FAQScreenPreview() {
    FAQScreen {}
}