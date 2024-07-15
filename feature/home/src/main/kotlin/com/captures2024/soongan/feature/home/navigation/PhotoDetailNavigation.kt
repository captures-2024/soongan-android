package com.captures2024.soongan.feature.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.captures2024.soongan.feature.home.route.PhotoDetailControlRoute
import com.captures2024.soongan.feature.home.route.PhotoDetailRoute
import timber.log.Timber
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

const val PHOTO_DETAIL_NAVIGATION_ROUTE = "photo_detail_route"
const val PHOTO_DETAIL_CONTROL_NAVIGATION_ROUTE = "photo_detail_control_route"

fun NavController.navigateToPhotoDetail(
    photoId: String,
    navOptions: NavOptions? = null
) {
    this.navigate("$PHOTO_DETAIL_NAVIGATION_ROUTE/$photoId", navOptions)
}

fun NavController.navigateToPhotoDetailControl(
    photoUrl: String,
    navOptions: NavOptions? = null
) {
    val encodedUrl = URLEncoder.encode(photoUrl, StandardCharsets.UTF_8.toString())
    this.navigate("$PHOTO_DETAIL_CONTROL_NAVIGATION_ROUTE/$encodedUrl", navOptions)
}


fun NavGraphBuilder.photoDetail(
    navigateToBack: () -> Unit,
    navigateToPhotoDetailControl: (String, NavOptions?) -> Unit
) {
    composable(
        route = "$PHOTO_DETAIL_NAVIGATION_ROUTE/{photo_id}",
        arguments = listOf(
            navArgument("photo_id") { type = NavType.StringType }
        )
    ) { navBackStackEntry ->
        val photoId = navBackStackEntry.arguments?.getString("photo_id")

        if (photoId == null) {
            navigateToBack()
        } else {
            PhotoDetailRoute(
                photoId = photoId,
                navigateToBack = navigateToBack,
                navigateToControlImage = navigateToPhotoDetailControl
            )
        }
    }
    composable(
        route = "$PHOTO_DETAIL_CONTROL_NAVIGATION_ROUTE/{photo_url}",
        arguments = listOf(
            navArgument("photo_url") { type = NavType.StringType }
        )
    ) { navBackStackEntry ->
        val photoUrl = navBackStackEntry.arguments?.getString("photo_url")

        Timber.tag("photo_url").d("photo_url = $photoUrl")

        if (photoUrl == null) {
            navigateToBack()
        } else {
            PhotoDetailControlRoute(
                photoUrl = photoUrl,
                onClickBack = navigateToBack
            )
        }
    }
}