package com.captures2024.soongan.core.navigator.screen.main.util

import androidx.navigation.NavController
import androidx.navigation.navOptions
import com.captures2024.soongan.core.model.utils.NotificationSubType
import com.captures2024.soongan.core.navigator.screen.main.home.HomeNavigator
import com.captures2024.soongan.core.navigator.screen.main.home.navigateToHome

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
