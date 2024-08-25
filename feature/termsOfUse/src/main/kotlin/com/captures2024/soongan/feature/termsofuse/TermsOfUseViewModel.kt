package com.captures2024.soongan.feature.termsofuse

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.captures2024.soongan.core.common.base.BaseViewModel
import com.captures2024.soongan.feature.termsofuse.state.TermsOfUseIntent
import com.captures2024.soongan.feature.termsofuse.state.TermsOfUseSideEffect
import com.captures2024.soongan.feature.termsofuse.state.TermsOfUseUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class TermsOfUseViewModel
@Inject
constructor(
    savedStateHandle: SavedStateHandle
) : BaseViewModel<TermsOfUseUIState, TermsOfUseSideEffect, TermsOfUseIntent>(savedStateHandle) {

    override fun createInitialState(savedStateHandle: SavedStateHandle): TermsOfUseUIState = TermsOfUseUIState()

    override fun handleClientException(throwable: Throwable) {
//        TODO("Not yet implemented")
    }

    override suspend fun handleIntent(intent: TermsOfUseIntent) {
        when (intent) {
            is TermsOfUseIntent.OnClickBack -> onClickBack()
        }
    }

    private fun onClickBack() {
        postSideEffect(TermsOfUseSideEffect.NavigateToBack)
    }

    companion object {
        private const val TAG = "TermsOfUseVM"
    }
}