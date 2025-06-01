package com.captures2024.soongan.core.navigator.screen.main.util

import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.navOptions
import com.captures2024.soongan.core.model.AppConst
import com.captures2024.soongan.core.model.utils.NotificationSubType
import com.captures2024.soongan.core.navigator.screen.main.feed.FeedNavigator
import com.captures2024.soongan.core.navigator.screen.main.home.HomeGalleryNavigator
import com.captures2024.soongan.core.navigator.screen.main.home.HomeNavigator
import com.captures2024.soongan.core.navigator.screen.main.home.navigateToHome

private enum class HideTargetScreen(val navigator: Any) {
    HOME_GALLERY(HomeGalleryNavigator),
    FEED(FeedNavigator),
}

/**
 *  직전 화면에 즉시 숨겨야 할 콘텐츠가 있는 경우, target ID를 직전 화면 스택에 저장 후 뒤로 가기
 *  save hideTargetContentId in previousBackStackEntry (HomeGallery || Feed) + popBackStack
 *
 *  ps. 직전 화면에서 NavController.getHideTargetContentId()를 통해 targetId를 획득 가능
 *
 * @param targetId
 */
fun NavController.navigateToBackWithHideTargetContentId(targetId: Long) {
    val prevDestination = this.previousBackStackEntry?.destination ?: return

    val hideTargetScreen = HideTargetScreen.entries.find { screen ->
        prevDestination.hasRoute(screen.navigator::class)
    }

    if(hideTargetScreen == null) {
        popBackStack()

        return
    }

    setHideTargetContentId(targetId)

    when(hideTargetScreen) {
        HideTargetScreen.HOME_GALLERY -> popBackStack<HomeGalleryNavigator>(inclusive = false)

        HideTargetScreen.FEED -> popBackStack<FeedNavigator>(inclusive = false)
    }
}

private fun NavController.setHideTargetContentId(targetId: Long) =
    this.previousBackStackEntry?.savedStateHandle?.set(AppConst.SavedStateHandle.UGC_HIDE_KEY, targetId)

fun NavController.getHideTargetContentId(): Long =
    this.currentBackStackEntry?.savedStateHandle?.get(AppConst.SavedStateHandle.UGC_HIDE_KEY) ?: -1L

/**
 * 알림 하위 타입에 따른 타겟 화면으로 전환.
 *
 * @param subType [NotificationSubType]
 * @param redirectUrl 타겟 화면 url
 */
@Suppress("UNUSED_PARAMETER")
fun NavController.navigateFromNotification(
    subType: NotificationSubType,
    redirectUrl: String? = null,
) {
    when (subType) {
        NotificationSubType.CONTEST_START, NotificationSubType.CONTEST_END -> {
            navigateToHome(
                navOptions = navOptions {
                    popUpTo(HomeNavigator) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                },
            )
        }

        NotificationSubType.COMMENT -> TODO("navigate comment screen")

        NotificationSubType.LIKE -> TODO("navigate post screen")

        NotificationSubType.APPEAL -> TODO("navigate refute screen")

        NotificationSubType.NOTICE -> TODO()
    }
}
