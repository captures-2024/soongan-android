package com.captures2024.soongan.core.navigator.screen.main.report

import kotlinx.serialization.Serializable

@Serializable
sealed interface ReportNavigator {

    @Serializable
    data object Idle : ReportNavigator

    @Serializable
    data class Check(
        val reportType: String,
    ) : ReportNavigator

    @Serializable
    data class Done(
        val hasExtraMessage: Boolean,
    ) : ReportNavigator
}
