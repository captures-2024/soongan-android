package com.captures2024.soongan.data.source.member.local

import kotlinx.coroutines.flow.StateFlow

interface GuestLocalDataSource {

    val isGuestMode: StateFlow<Boolean>

    fun setGuestMode(isGuestMode: Boolean)
}
