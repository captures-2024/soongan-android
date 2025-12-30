package com.captures2024.soongan.presentation.feature.main.profile.component.faq

import androidx.compose.material3.Tab
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.presentation.designsystem.ui.component.text.SGText
import com.captures2024.soongan.presentation.designsystem.ui.component.text.getSGNonScaleTextStyle
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGColor
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGTheme
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGTypography
import com.captures2024.soongan.presentation.designsystem.ui.util.DevicePreviews
import com.captures2024.soongan.presentation.feature.main.profile.utils.extension.getTabResId
import com.captures2024.soongan.presentation.viewmodel.model.FaqCategory

@Composable
internal fun FaqTabComponent(
    category: FaqCategory,
    selected: Boolean,
    onClick: () -> Unit,
) {
    Tab(
        modifier = when (selected) {
            true -> Modifier
            false -> Modifier.graphicsLayer(alpha = 0.4f)
        },
        selected = selected,
        onClick = onClick,
        text = @Composable {
            SGText(
                text = stringResource(category.getTabResId()),
                style = getSGNonScaleTextStyle(
                    color = SGColor.Grayscale.black100,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 20.sp,
                    fontFamily = SGTypography.pretendard,
                    letterSpacing = (-5).em,
                ),
            )
        },
    )
}

@DevicePreviews
@Composable
private fun PreviewFaqTabComponent() {
    SGTheme {
        FaqTabComponent(
            category = FaqCategory.DEFAULT,
            selected = true,
            onClick = {},
        )
    }
}
