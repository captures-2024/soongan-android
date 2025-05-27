package com.captures2024.soongan.presentation.feature.main.profile.component.faq

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.core.designsystem.ui.component.HeightSpacer
import com.captures2024.soongan.core.designsystem.ui.component.text.SGText
import com.captures2024.soongan.core.designsystem.ui.component.text.getSGNonScaleTextStyle
import com.captures2024.soongan.core.designsystem.ui.theme.SGColor
import com.captures2024.soongan.core.designsystem.ui.theme.SGTheme
import com.captures2024.soongan.core.designsystem.ui.theme.SGTypography
import com.captures2024.soongan.core.designsystem.ui.util.DevicePreviews
import com.captures2024.soongan.presentation.feature.main.profile.utils.extension.getAnswerResId
import com.captures2024.soongan.presentation.feature.main.profile.utils.extension.getQuestionResId
import com.captures2024.soongan.presentation.viewmodel.model.FaqCategoryItem

@Composable
internal fun FaqItemComponent(
    faqCategoryItem: FaqCategoryItem,
    modifier: Modifier = Modifier,
) {
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .padding(horizontal = 24.dp)
            .animateContentSize(),
    ) {
        Box(
            modifier = Modifier
                .height(60.dp)
                .fillMaxWidth()
                .clickable { expanded = !expanded },
            contentAlignment = Alignment.CenterStart,
        ) {
            SGText(
                text = stringResource(faqCategoryItem.getQuestionResId()),
                modifier = Modifier.padding(horizontal = 8.dp),
                style = getSGNonScaleTextStyle(
                    color = SGColor.Grayscale.black100,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 20.sp,
                    fontFamily = SGTypography.pretendard,
                    letterSpacing = (-5).em,
                ),
                maxLines = when (expanded) {
                    true -> Int.MAX_VALUE
                    false -> 1
                },
                overflow = when (expanded) {
                    true -> TextOverflow.Visible
                    false -> TextOverflow.Ellipsis
                },
            )
        }

        if (expanded) {
            HeightSpacer(12.dp)

            SGText(
                text = stringResource(faqCategoryItem.getAnswerResId()),
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .padding(bottom = 20.dp),
                style = getSGNonScaleTextStyle(
                    color = SGColor.Grayscale.black100,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 20.sp,
                    fontFamily = SGTypography.pretendard,
                    letterSpacing = (-5).em,
                ),
            )
        }

        HorizontalDivider(
            thickness = 1.dp,
            color = SGColor.buttonDisableGray,
        )
    }
}

@DevicePreviews
@Composable
private fun PreviewFaqItemComponent() {
    SGTheme {
        FaqItemComponent(
            faqCategoryItem = FaqCategoryItem.Default.First,
        )
    }
}
