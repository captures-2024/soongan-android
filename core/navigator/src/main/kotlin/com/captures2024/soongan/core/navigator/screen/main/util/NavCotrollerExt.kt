package com.captures2024.soongan.core.navigator.screen.main.util

import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hasRoute
import com.captures2024.soongan.core.navigator.screen.main.home.HomeGalleryNavigator

private const val SAVED_STATE_HANDLE_KEY = "hide_post_id"

/* 갤러리 화면에서 접근한 게시물이 삭제, 신고 되었을 경우 popBackStack + hidePost */
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
