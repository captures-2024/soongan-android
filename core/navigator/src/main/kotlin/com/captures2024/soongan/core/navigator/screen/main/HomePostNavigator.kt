package com.captures2024.soongan.core.navigator.screen.main

import kotlinx.serialization.Serializable

@Serializable
data class HomePostNavigator(
    val id: Int,
    val url: String,
    val title: String
)