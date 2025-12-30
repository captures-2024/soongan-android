package com.captures2024.soongan.core.navigator.screen.main.report

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import kotlinx.serialization.Serializable

@Serializable
data object SelectReportReasonNavigator

fun NavController.navigateToSelectReportReason() = navigateToSelectReportReason(
    navOptions = null,
)

fun NavController.navigateToSelectReportReason(navOptions: NavOptions?) = navigate(
    route = SelectReportReasonNavigator,
    navOptions = navOptions,
)
