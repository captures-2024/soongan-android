package com.captures2024.soongan.feature.privacypolicy

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.captures2024.soongan.core.common.base.BaseViewModel
import com.captures2024.soongan.feature.privacypolicy.state.PrivacyPolicyIntent
import com.captures2024.soongan.feature.privacypolicy.state.PrivacyPolicySideEffect
import com.captures2024.soongan.feature.privacypolicy.state.PrivacyPolicyUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class PrivacyPolicyViewModel
@Inject
constructor(
    savedStateHandle: SavedStateHandle
) : BaseViewModel<PrivacyPolicyUIState, PrivacyPolicySideEffect, PrivacyPolicyIntent>(savedStateHandle) {

    override fun createInitialState(savedStateHandle: SavedStateHandle): PrivacyPolicyUIState = PrivacyPolicyUIState()

    override fun handleClientException(throwable: Throwable) {
//        TODO("Not yet implemented")
    }

    override suspend fun handleIntent(intent: PrivacyPolicyIntent) {
        when (intent) {
            is PrivacyPolicyIntent.OnClickBack -> onClickBack()
        }
    }

    private fun onClickBack() {
        postSideEffect(PrivacyPolicySideEffect.NavigateToBack)
    }

    companion object {
        private const val TAG = "PrivacyPolicyVM"
    }
}