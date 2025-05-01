package com.captures2024.soongan.feature.feed.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MenuAnchorType
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.core.designsystem.icon.MyIconPack
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonFillFillter
import com.captures2024.soongan.core.designsystem.ui.component.WidthSpacer
import com.captures2024.soongan.core.designsystem.ui.component.button.SGIconCircleButton
import com.captures2024.soongan.core.designsystem.ui.component.text.SGText
import com.captures2024.soongan.core.designsystem.ui.component.text.getSGNonScaleTextStyle
import com.captures2024.soongan.core.designsystem.ui.theme.SGColor
import com.captures2024.soongan.core.designsystem.ui.theme.SGTypography
import com.captures2024.soongan.core.designsystem.ui.util.DevicePreviews
import com.captures2024.soongan.core.viewmodel.feed.FeedViewModel

@Composable
internal fun FeedTopBar(
    selectedOption: Pair<Int, String>,
    options: List<Pair<Int, String>>,
    modifier: Modifier = Modifier,
    onClickRound: (Int) -> Unit = {},
    onClickFilter: () -> Unit = {},
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(20.dp),
        contentAlignment = Alignment.Center,
    ) {
        FeedDropDownMenu(
            selectedOption = selectedOption,
            options = options,
            onClickRound = onClickRound,
        )
        Box(
            modifier = Modifier
                .align(Alignment.CenterEnd),
        ) {
            SGIconCircleButton(
                imageVector = MyIconPack.IconNonFillFillter,
                contentDescription = "",
                onClick = onClickFilter,
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FeedDropDownMenu(
    selectedOption: Pair<Int, String>,
    options: List<Pair<Int, String>>,
    modifier: Modifier = Modifier,
    onClickRound: (Int) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = modifier,
    ) {
        Row(
            modifier = Modifier
                .menuAnchor(MenuAnchorType.PrimaryEditable)
                .fillMaxWidth(0.5f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            FeedTopBarAnnotatedTitle(prefix = selectedOption.first, suffix = selectedOption.second)
            WidthSpacer(8.dp)
            Icon(
                imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                contentDescription = null,
            )
        }

        ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            for ((round, title) in options) {
                DropdownMenuItem(
                    text = {
                        SGText(
                            text = "${round}회차 | $title",
                            style = getSGNonScaleTextStyle(
                                color = SGColor.primaryA,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                lineHeight = 20.sp,
                                fontFamily = SGTypography.nanumSquareNeo,
                                letterSpacing = (-5).em,
                            ),
                        )
                    },
                    onClick = {
                        expanded = false
                        onClickRound(round)
                    },
                )
            }
        }
    }
}

@Composable
private fun FeedTopBarAnnotatedTitle(
    prefix: Int,
    suffix: String,
) {
    SGText(
        text = "${prefix}회차",
        style = getSGNonScaleTextStyle(
            color = SGColor.primaryA,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 20.sp,
            fontFamily = SGTypography.nanumSquareNeo,
            letterSpacing = (-5).em,
        ),
    )

    WidthSpacer(8.dp)

    SGText(
        text = "|",
        style = getSGNonScaleTextStyle(
            color = SGColor.primaryA,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 20.sp,
            fontFamily = SGTypography.poppins,
            letterSpacing = 0.em,
        ),
    )

    WidthSpacer(8.dp)

    SGText(
        text = suffix,
        style = getSGNonScaleTextStyle(
            color = SGColor.primaryA,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 20.sp,
            fontFamily = SGTypography.nanumSquareNeo,
            letterSpacing = (-5).em,
        ),
    )
}

@DevicePreviews
@Composable
private fun HomeGalleryTopBarPreview() {
    val state = FeedViewModel.State()

    FeedTopBar(
        selectedOption = state.currentTitleOption,
        options = state.titleOptions,
        onClickRound = {},
    )
}
