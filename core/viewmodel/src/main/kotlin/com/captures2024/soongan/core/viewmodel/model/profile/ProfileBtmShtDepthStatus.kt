package com.captures2024.soongan.core.viewmodel.model.profile

sealed interface ProfileBtmShtDepthStatus {
    data object Idle : ProfileBtmShtDepthStatus

    data object Push : ProfileBtmShtDepthStatus

    sealed interface SignOut : ProfileBtmShtDepthStatus {
        data object Check : SignOut

        data object Done : SignOut
    }

    sealed interface Withdraw : ProfileBtmShtDepthStatus {
        data object Check : Withdraw

        data object Done : Withdraw
    }

    data object Error: ProfileBtmShtDepthStatus
}