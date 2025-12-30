package com.captures2024.soongan.presentation.feature.main.awards.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import com.captures2024.soongan.core.navigator.screen.main.awards.AwardsInfoNavigator
import com.captures2024.soongan.core.navigator.screen.main.awards.AwardsNavigator
import com.captures2024.soongan.core.navigator.screen.main.home.HomeNavigator
import com.captures2024.soongan.presentation.feature.main.awards.route.AwardsInfoRoute
import com.captures2024.soongan.presentation.feature.main.awards.route.AwardsRoute

fun NavGraphBuilder.mainAwards(
    navigateToAwardsInfo: (id: Long) -> Unit,
    navigateToBack: () -> Unit,
    navigateToPost: (postId: Long) -> Unit,
    navigateToFeed: (NavOptions, round: Long) -> Unit,
) {
    composable<AwardsNavigator> {
        AwardsRoute(
            navigateToAwardsInfo = navigateToAwardsInfo,
        )
    }

    composable<AwardsInfoNavigator> {
        val feedNavOption = navOptions {
            popUpTo(HomeNavigator) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }

        AwardsInfoRoute(
            navigateToBack = navigateToBack,
            navigateToPost = navigateToPost,
            navigateToFeed = { round -> navigateToFeed(feedNavOption, round) },
        )
    }
}
