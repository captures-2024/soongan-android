package com.captures2024.soongan.presentation.feature.main.profile.component.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.presentation.designsystem.ui.component.HeightSpacer
import com.captures2024.soongan.presentation.designsystem.ui.component.WeightSpacer
import com.captures2024.soongan.presentation.designsystem.ui.component.text.SGText
import com.captures2024.soongan.presentation.designsystem.ui.component.text.getSGNonScaleTextStyle
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGColor
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGTheme
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGTypography
import com.captures2024.soongan.presentation.designsystem.ui.util.DevicePreviews
import com.captures2024.soongan.presentation.feature.main.profile.R
import com.captures2024.soongan.presentation.feature.main.profile.component.faq.FaqItemComponent
import com.captures2024.soongan.presentation.feature.main.profile.component.faq.FaqTapRowComponent
import com.captures2024.soongan.presentation.feature.main.profile.component.faq.FaqTopBarComponent
import com.captures2024.soongan.presentation.viewmodel.main.profile.FaqViewModel
import com.captures2024.soongan.presentation.viewmodel.model.FaqCategory

@Composable
internal fun FaqScreen(
    state: FaqViewModel.State,
    modifier: Modifier = Modifier,
    onClickBack: () -> Unit,
    onClickCategory: (FaqCategory) -> Unit,
    onClickInquiry: () -> Unit,
) {
    val scrollState = rememberScrollState()

    val pagerState = rememberPagerState(
        pageCount = { state.categories.size },
        initialPageOffsetFraction = 0f,
        initialPage = 0,
    )

    val categoryItems = state.selectedCategory.getCategoryItems()

    Column(modifier = modifier.fillMaxSize()) {
        FaqTopBarComponent(
            onClickBack = onClickBack,
        )

        FaqTapRowComponent(
            tabs = state.categories,
            selectedCategory = state.selectedCategory,
            pagerState = pagerState,
            onClickCategory = onClickCategory,
        )

        HorizontalPager(
            state = pagerState,
            userScrollEnabled = false,
        ) { page ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = SGColor.Grayscale.white)
                    .verticalScroll(state = scrollState),
            ) {
                categoryItems.forEach { categoryItem ->
                    FaqItemComponent(
                        faqCategoryItem = categoryItem,
                    )
                }

                WeightSpacer(1f)

                InquiryBox(
                    modifier = Modifier.padding(
                        top = 48.dp,
                        bottom = 64.dp,
                    ),
                    onClick = onClickInquiry,
                )
            }
        }
    }
}

@Composable
private fun InquiryBox(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    val commonTextStyle = getSGNonScaleTextStyle(
        color = SGColor.Grayscale.black100,
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        lineHeight = 12.sp,
        fontFamily = SGTypography.pretendard,
    )

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        SGText(
            text = stringResource(R.string.faq_inquiry_question_message),
            style = commonTextStyle,
        )
        HeightSpacer(12.dp)
        SGText(
            text = stringResource(R.string.faq_inquiry_text),
            modifier = Modifier.clickable(onClick = onClick),
            style = commonTextStyle.copy(textDecoration = TextDecoration.Underline),
        )
    }
}

@DevicePreviews
@Composable
private fun PreviewFaqScreen() {
    SGTheme {
        FaqScreen(
            state = FaqViewModel.State(
                categories = FaqCategory.entries,
                selectedCategory = FaqCategory.DEFAULT,
            ),
            onClickBack = {},
            onClickCategory = {},
            onClickInquiry = {},
        )
    }
}
