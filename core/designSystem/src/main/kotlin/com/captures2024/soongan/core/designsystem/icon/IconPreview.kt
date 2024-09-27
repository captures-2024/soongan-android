package com.captures2024.soongan.core.designsystem.icon

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconFillCheck
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconFillError
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconFillHeart
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconLogoApple
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconLogoGoogle
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconLogoKakao
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonFillBackArrow
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonFillComment
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonFillEdit
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonFillFillter
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonFillHeart
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonFillInfo
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonFillLeftArrow
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonFillMenu
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonFillPaperDelete
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonFillPlus
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonFillReport
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonFillRightArrow
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonFillTopArrow
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonSelectedAwards
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonSelectedFeed
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonSelectedHome
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonSelectedProfile
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconSelectedAwards
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconSelectedFeed
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconSelectedHome
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconSelectedProfile
import com.captures2024.soongan.core.designsystem.icon.myiconpack.Logo

@Preview
@Composable
private fun IconFillCheckPreview() {
    Box(
        modifier = Modifier
            .size(64.dp, 64.dp)
            .background(Color.White),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            imageVector = MyIconPack.IconFillCheck,
            contentDescription = "",
        )
    }
}

@Preview
@Composable
private fun IconSelectedHomePreview() {
    Box(
        modifier = Modifier
            .size(64.dp, 64.dp)
            .background(Color.White),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            imageVector = MyIconPack.IconSelectedHome,
            contentDescription = "",
        )
    }
}

@Preview
@Composable
private fun IconNonSelectedAwardsPreview() {
    Box(
        modifier = Modifier
            .size(64.dp, 64.dp)
            .background(Color.White),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            imageVector = MyIconPack.IconNonSelectedAwards,
            contentDescription = "",
        )
    }
}

@Preview
@Composable
private fun IconNonFillFilterPreview() {
    Box(
        modifier = Modifier
            .size(64.dp, 64.dp)
            .background(Color.White),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            imageVector = MyIconPack.IconNonFillFillter,
            contentDescription = "",
        )
    }
}

@Preview
@Composable
private fun IconNonFillTopArrowPreview() {
    Box(
        modifier = Modifier
            .size(64.dp, 64.dp)
            .background(Color.White),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            imageVector = MyIconPack.IconNonFillTopArrow,
            contentDescription = "",
        )
    }
}

@Preview
@Composable
private fun IconSelectedFeedPreview() {
    Box(
        modifier = Modifier
            .size(64.dp, 64.dp)
            .background(Color.White),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            imageVector = MyIconPack.IconSelectedFeed,
            contentDescription = "",
        )
    }
}

@Preview
@Composable
private fun IconNonFillPlusPreview() {
    Box(
        modifier = Modifier
            .size(64.dp, 64.dp)
            .background(Color.White),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            imageVector = MyIconPack.IconNonFillPlus,
            contentDescription = "",
        )
    }
}

@Preview
@Composable
private fun IconNonFillInfoPreview() {
    Box(
        modifier = Modifier
            .size(64.dp, 64.dp)
            .background(Color.White),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            imageVector = MyIconPack.IconNonFillInfo,
            contentDescription = "",
        )
    }
}

@Preview
@Composable
private fun IconNonFillHeartPreview() {
    Box(
        modifier = Modifier
            .size(64.dp, 64.dp)
            .background(Color.White),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            imageVector = MyIconPack.IconNonFillHeart,
            contentDescription = "",
        )
    }
}

@Preview
@Composable
private fun IconSelectedAwardsPreview() {
    Box(
        modifier = Modifier
            .size(64.dp, 64.dp)
            .background(Color.White),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            imageVector = MyIconPack.IconSelectedAwards,
            contentDescription = "",
        )
    }
}

@Preview
@Composable
private fun IconNonSelectedFeedPreview() {
    Box(
        modifier = Modifier
            .size(64.dp, 64.dp)
            .background(Color.White),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            imageVector = MyIconPack.IconNonSelectedFeed,
            contentDescription = "",
        )
    }
}

@Preview
@Composable
private fun IconFillErrorPreview() {
    Box(
        modifier = Modifier
            .size(64.dp, 64.dp)
            .background(Color.White),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            imageVector = MyIconPack.IconFillError,
            contentDescription = "",
        )
    }
}

@Preview
@Composable
private fun IconNonFillCommentPreview() {
    Box(
        modifier = Modifier
            .size(64.dp, 64.dp)
            .background(Color.White),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            imageVector = MyIconPack.IconNonFillComment,
            contentDescription = "",
        )
    }
}

@Preview
@Composable
private fun IconNonFillEditPreview() {
    Box(
        modifier = Modifier
            .size(64.dp, 64.dp)
            .background(Color.White),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            imageVector = MyIconPack.IconNonFillEdit,
            contentDescription = "",
        )
    }
}

@Preview
@Composable
private fun IconNonFillBackArrowPreview() {
    Box(
        modifier = Modifier
            .size(64.dp, 64.dp)
            .background(Color.White),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            imageVector = MyIconPack.IconNonFillBackArrow,
            contentDescription = "",
        )
    }
}

@Preview
@Composable
private fun IconFillHeartPreview() {
    Box(
        modifier = Modifier
            .size(64.dp, 64.dp)
            .background(Color.White),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            imageVector = MyIconPack.IconFillHeart,
            contentDescription = "",
        )
    }
}

@Preview
@Composable
private fun IconSelectedProfilePreview() {
    Box(
        modifier = Modifier
            .size(64.dp, 64.dp)
            .background(Color.White),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            imageVector = MyIconPack.IconSelectedProfile,
            contentDescription = "",
        )
    }
}

@Preview
@Composable
private fun IconLogoGooglePreview() {
    Box(
        modifier = Modifier
            .size(64.dp, 64.dp)
            .background(Color.White),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            imageVector = MyIconPack.IconLogoGoogle,
            contentDescription = "",
        )
    }
}

@Preview
@Composable
private fun IconLogoApplePreview() {
    Box(
        modifier = Modifier
            .size(64.dp, 64.dp)
            .background(Color.White),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            imageVector = MyIconPack.IconLogoApple,
            contentDescription = "",
        )
    }
}

@Preview
@Composable
private fun IconNonFillPaperDeletePreview() {
    Box(
        modifier = Modifier
            .size(64.dp, 64.dp)
            .background(Color.White),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            imageVector = MyIconPack.IconNonFillPaperDelete,
            contentDescription = "",
        )
    }
}

@Preview
@Composable
private fun IconNonSelectedProfilePreview() {
    Box(
        modifier = Modifier
            .size(64.dp, 64.dp)
            .background(Color.White),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            imageVector = MyIconPack.IconNonSelectedProfile,
            contentDescription = "",
        )
    }
}

@Preview
@Composable
private fun IconNonFillMenuPreview() {
    Box(
        modifier = Modifier
            .size(64.dp, 64.dp)
            .background(Color.White),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            imageVector = MyIconPack.IconNonFillMenu,
            contentDescription = "",
        )
    }
}

@Preview
@Composable
private fun IconLogoKakaoPreview() {
    Box(
        modifier = Modifier
            .size(64.dp, 64.dp)
            .background(Color.White),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            imageVector = MyIconPack.IconLogoKakao,
            contentDescription = "",
        )
    }
}

@Preview
@Composable
private fun IconNonFillLeftArrowPreview() {
    Box(
        modifier = Modifier
            .size(64.dp, 64.dp)
            .background(Color.White),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            imageVector = MyIconPack.IconNonFillLeftArrow,
            contentDescription = "",
        )
    }
}

@Preview
@Composable
private fun IconNonFillRightArrowPreview() {
    Box(
        modifier = Modifier
            .size(64.dp, 64.dp)
            .background(Color.White),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            imageVector = MyIconPack.IconNonFillRightArrow,
            contentDescription = "",
        )
    }
}

@Preview
@Composable
private fun IconNonSelectedHomePreview() {
    Box(
        modifier = Modifier
            .size(64.dp, 64.dp)
            .background(Color.White),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            imageVector = MyIconPack.IconNonSelectedHome,
            contentDescription = "",
        )
    }
}

@Preview
@Composable
private fun IconNonFillReportPreview() {
    Box(
        modifier = Modifier
            .size(64.dp, 64.dp)
            .background(Color.White),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            imageVector = MyIconPack.IconNonFillReport,
            contentDescription = "",
        )
    }
}

@Preview
@Composable
private fun LogoPreview() {
    Box(
        modifier = Modifier
            .size(64.dp, 64.dp)
            .background(Color.White),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            imageVector = MyIconPack.Logo,
            contentDescription = "",
        )
    }
}
