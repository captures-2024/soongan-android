package com.captures2024.soongan.core.viewmodel.model.profile

sealed interface ProfileBtmShtDepthState {
    data object Idle : ProfileBtmShtDepthState

    data object Push : ProfileBtmShtDepthState

    sealed interface SignOut : ProfileBtmShtDepthState {
        data object Check : SignOut

        data object Done : SignOut
    }

    sealed interface Withdraw : ProfileBtmShtDepthState {
        data object Check : Withdraw

        data object Done : Withdraw
    }

    data object Error: ProfileBtmShtDepthState
}