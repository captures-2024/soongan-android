package com.captures2024.soongan.core.data.source.members.local

import kotlinx.coroutines.flow.StateFlow

interface GuestLocalDataSource {

    val isGuestMode: StateFlow<Boolean>

    fun setGuestMode(isGuestMode: Boolean)
}
