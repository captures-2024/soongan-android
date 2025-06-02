package com.captures2024.soongan.feature.feed.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.core.designsystem.ui.component.gallery.SGGalleryHeaderTitle
import com.captures2024.soongan.core.designsystem.ui.component.text.SGText
import com.captures2024.soongan.core.designsystem.ui.component.text.getSGNonScaleTextStyle
import com.captures2024.soongan.core.designsystem.ui.theme.SGColor
import com.captures2024.soongan.core.designsystem.ui.theme.SGTypography
import com.captures2024.soongan.core.designsystem.ui.util.DevicePreviews

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun FeedDropDownTitle(
    selectedOption: Pair<Int, String>,
    options: List<Pair<Int, String>>,
    modifier: Modifier = Modifier,
    onClickTitle: (round: Int) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = modifier,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(fraction = 0.45f)
                .menuAnchor(MenuAnchorType.PrimaryEditable),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround,
        ) {
            SGGalleryHeaderTitle(
                prefix = "${selectedOption.first}회차",
                suffix = selectedOption.second,
            )

            Icon(
                imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                contentDescription = null,
            )
        }

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
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
                        onClickTitle(round)
                    },
                )
            }
        }
    }
}

@DevicePreviews
@Composable
private fun FeedDropDownMenuPreview() {
    FeedDropDownTitle(
        selectedOption = 1 to "평화",
        options = listOf(1 to "평화", 2 to "행복", 3 to "즐거움"),
        onClickTitle = {}
    )
}
