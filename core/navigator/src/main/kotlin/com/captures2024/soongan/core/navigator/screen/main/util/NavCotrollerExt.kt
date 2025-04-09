package com.captures2024.soongan.core.navigator.screen.main.util

import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.navOptions
import com.captures2024.soongan.core.model.utils.NotificationSubType
import com.captures2024.soongan.core.navigator.screen.main.home.HomeGalleryNavigator
import com.captures2024.soongan.core.navigator.screen.main.home.HomeNavigator
import com.captures2024.soongan.core.navigator.screen.main.home.navigateToHome

private const val SAVED_STATE_HANDLE_KEY = "hide_post_id"

/**
 * 갤러리 화면에서 접근한 게시물이 삭제/신고 되었을 경우 popBackStack + hidePost
 *
 * @param postId
 */
fun NavController.navigateToBackWithHidePost(postId: Long) {
    this.previousBackStackEntry?.destination?.hasRoute<HomeGalleryNavigator>()
        ?.let { isGalleryRoute ->
            when (isGalleryRoute) {
                true -> setHidedPostId(postId)
                false -> popBackStack()
            }
        }
}

private fun NavController.setHidedPostId(postId: Long) {
    this.previousBackStackEntry?.savedStateHandle?.set(SAVED_STATE_HANDLE_KEY, postId)

    popBackStack<HomeGalleryNavigator>(inclusive = false)
}

fun NavController.getHidedPostId(): Long =
    this.currentBackStackEntry?.savedStateHandle?.get(SAVED_STATE_HANDLE_KEY) ?: -1L

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
