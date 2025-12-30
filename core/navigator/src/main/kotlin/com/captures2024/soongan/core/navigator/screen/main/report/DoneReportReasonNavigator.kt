package com.captures2024.soongan.core.navigator.screen.main.report

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import kotlinx.serialization.Serializable

@Serializable
data object DoneReportReasonNavigator

fun NavController.navigateToDoneReportReason() = navigateToDoneReportReason(
    navOptions = null,
)

fun NavController.navigateToDoneReportReason(navOptions: NavOptions?) = navigate(
    route = DoneReportReasonNavigator,
    navOptions = navOptions,
)
