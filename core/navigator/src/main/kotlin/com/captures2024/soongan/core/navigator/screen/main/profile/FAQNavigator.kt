package com.captures2024.soongan.core.navigator.screen.main.profile

import androidx.navigation.NavController
import androidx.navigation.NavOptions

data object FAQNavigator

fun NavController.navigateToFAQ() = navigate(FAQNavigator)

fun NavController.navigateToFAQ(navOptions: NavOptions?) = navigate(
    route = FAQNavigator,
    navOptions = navOptions,
)
