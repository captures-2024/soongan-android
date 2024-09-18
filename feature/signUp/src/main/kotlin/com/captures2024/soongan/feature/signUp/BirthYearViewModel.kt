package com.captures2024.soongan.feature.signUp

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import com.captures2024.soongan.core.common.base.BaseViewModel
import com.captures2024.soongan.core.navigator.screen.sign.BirthDateNavigator
import com.captures2024.soongan.feature.signUp.state.birthdate.BirthDateIntent
import com.captures2024.soongan.feature.signUp.state.birthdate.BirthDateSideEffect
import com.captures2024.soongan.feature.signUp.state.birthdate.BirthDateUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class BirthYearViewModel
@Inject
constructor(
    savedStateHandle: SavedStateHandle
) : BaseViewModel<BirthDateUIState, BirthDateSideEffect, BirthDateIntent>(savedStateHandle) {

    override fun createInitialState(savedStateHandle: SavedStateHandle): BirthDateUIState {
        val nickname = savedStateHandle.toRoute<BirthDateNavigator>().nickname
        return BirthDateUIState(nickname = nickname)
    }

    override fun handleClientException(throwable: Throwable) {
//        Timber.tag(TAG).e(throwable)
    }

    override suspend fun handleIntent(intent: BirthDateIntent) {
//        when (intent) {
//
//        }
    }

    companion object {
        private const val TAG = "BirthYearVM"
    }
}