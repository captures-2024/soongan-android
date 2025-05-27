package com.captures2024.soongan.presentation.feature.main.profile.component.menu

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.captures2024.soongan.core.designsystem.ui.theme.SGColor
import com.captures2024.soongan.core.navigator.screen.main.profile.menu.ProfileMenuDefaultNavigator
import com.captures2024.soongan.core.navigator.screen.main.profile.menu.ProfileMenuNotificationSettingNavigator
import com.captures2024.soongan.core.navigator.screen.main.profile.menu.ProfileMenuSignOutNavigator
import com.captures2024.soongan.core.navigator.screen.main.profile.menu.ProfileMenuWithdrawNavigator
import com.captures2024.soongan.core.navigator.screen.main.profile.menu.navigateToProfileMenuNotificationSetting
import com.captures2024.soongan.core.navigator.screen.main.profile.menu.navigateToProfileMenuSignOut
import com.captures2024.soongan.core.navigator.screen.main.profile.menu.navigateToProfileMenuWithdraw
import com.captures2024.soongan.presentation.feature.main.profile.route.ProfileMenuDefaultRoute
import com.captures2024.soongan.presentation.feature.main.profile.route.ProfileMenuNotificationSettingRoute
import com.captures2024.soongan.presentation.feature.main.profile.route.ProfileMenuSignOutRoute
import com.captures2024.soongan.presentation.feature.main.profile.route.ProfileMenuWithdrawRoute
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ProfileMenuBottomSheet(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
    navigateToEditProfile: () -> Unit,
    navigateToFaq: () -> Unit,
    navigateToTerms: () -> Unit,
) {
    val sheetState: SheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val navController = rememberNavController()

    val scope = rememberCoroutineScope()

    var isAvailableBack by remember { mutableStateOf(true) }

    val navigateToBack: () -> Unit = {
        if (isAvailableBack) {
            isAvailableBack = false
            navController.navigateUp()

            scope.launch {
                delay(500L)
                isAvailableBack = true
            }
        }
    }

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        modifier = modifier.fillMaxWidth(),
        sheetState = sheetState,
        containerColor = SGColor.Grayscale.white,
    ) {
        NavHost(
            navController = navController,
            startDestination = ProfileMenuDefaultNavigator,
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None },
            popEnterTransition = { EnterTransition.None },
            popExitTransition = { ExitTransition.None },
        ) {
            composable<ProfileMenuDefaultNavigator> {
                ProfileMenuDefaultRoute(
                    navigateToEditProfile = {
                        onDismissRequest()
                        navigateToEditProfile()
                    },
                    navigateToFaq = {
                        onDismissRequest()
                        navigateToFaq()
                    },
                    navigateToPush = navController::navigateToProfileMenuNotificationSetting,
                    navigateToSignOut = navController::navigateToProfileMenuSignOut,
                    navigateToTerms = {
                        onDismissRequest()
                        navigateToTerms()
                    },
                    navigateToWithdraw = navController::navigateToProfileMenuWithdraw,
                )
            }

            composable<ProfileMenuNotificationSettingNavigator> {
                ProfileMenuNotificationSettingRoute(
                    navigateToBack = navigateToBack,
                )
            }

            composable<ProfileMenuWithdrawNavigator> {
                ProfileMenuWithdrawRoute(
                    onDismissRequest = onDismissRequest,
                    navigateToBack = navigateToBack,
                )
            }

            composable<ProfileMenuSignOutNavigator> {
                ProfileMenuSignOutRoute(
                    onDismissRequest = onDismissRequest,
                    navigateToBack = navigateToBack,
                )
            }
        }
    }
}
