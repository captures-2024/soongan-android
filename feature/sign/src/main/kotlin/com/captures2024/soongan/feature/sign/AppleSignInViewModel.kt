package com.captures2024.soongan.feature.sign

import androidx.lifecycle.ViewModel
import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class AppleSignInViewModel
@Inject
constructor(
    private val analyticsHelper: AnalyticsHelper,
) : ViewModel() {
    private val _appleSignInState = MutableStateFlow<AppleSignInState>(AppleSignInState.NotInit)
    val appleSignInState: StateFlow<AppleSignInState>
        get() = _appleSignInState

    fun onClickAppleSignIn() {
        _appleSignInState.value = AppleSignInState.Init
    }

    fun cancelAppleSignIn() {
        _appleSignInState.value = AppleSignInState.NotInit
    }
}

sealed interface AppleSignInState {
    data object NotInit : AppleSignInState
    data object Init : AppleSignInState
    data class Success(
        val idToken: String,
    ) : AppleSignInState
    data object Failure
}