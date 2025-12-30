package com.captures2024.soongan.core.navigator.screen.main.report

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import kotlinx.serialization.Serializable

@Serializable
data object CheckReportReasonNavigator

fun NavController.navigateToCheckReportReason() = navigateToCheckReportReason(
    navOptions = null,
)

fun NavController.navigateToCheckReportReason(navOptions: NavOptions?) = navigate(
    route = CheckReportReasonNavigator,
    navOptions = navOptions,
)
