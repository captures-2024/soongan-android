package com.captures2024.soongan.feature.home

import androidx.lifecycle.SavedStateHandle
import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.common.base.BaseViewModel
import com.captures2024.soongan.feature.home.state.registration_post.RegistrationPostIntent
import com.captures2024.soongan.feature.home.state.registration_post.RegistrationPostSideEffect
import com.captures2024.soongan.feature.home.state.registration_post.RegistrationPostUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class RegistrationPostViewModel
@Inject
constructor(
    private val analyticsHelper: AnalyticsHelper,
    savedStateHandle: SavedStateHandle,
) : BaseViewModel<RegistrationPostUIState, RegistrationPostSideEffect, RegistrationPostIntent>(savedStateHandle) {

    override fun createInitialState(savedStateHandle: SavedStateHandle): RegistrationPostUIState = RegistrationPostUIState()

    override fun handleClientException(throwable: Throwable) {
        analyticsHelper.e(
            throwable = throwable,
            logVariable = currentState.toLoggingElements(),
            message = "handleClientException"
        )
    }

    override suspend fun handleIntent(intent: RegistrationPostIntent) {
        analyticsHelper.d(message = "handleIntent - intent: $intent")

        when (intent) {
            is RegistrationPostIntent.Init -> handleInit(intent)

            is RegistrationPostIntent.InitMedia -> handleInitMedia(intent)

            is RegistrationPostIntent.OnTitleValueChanged -> handleOnTitleValueChanged(intent)

            is RegistrationPostIntent.OnClickSubmit -> handleOnClickSubmit(intent)
        }
    }

    private fun handleInit(intent: RegistrationPostIntent.Init) {
        analyticsHelper.d(message = "handleInit - intent: $intent")

        postSideEffect(RegistrationPostSideEffect.OpenMediaPicker)
    }

    private fun handleInitMedia(intent: RegistrationPostIntent.InitMedia) {
        analyticsHelper.d(message = "handleInitMedia - intent: $intent")

        val uri = intent.mediaUri

        if (null == uri) {
            postSideEffect(RegistrationPostSideEffect.NavigateToBack)
            return
        }

        reduce {
            copy(currentMedia = intent.mediaUri)
        }
    }

    private fun handleOnTitleValueChanged(intent: RegistrationPostIntent.OnTitleValueChanged) {
        analyticsHelper.d(message = "handleOnTitleValueChanged - intent: $intent")

        val newValue = intent.newValue

        if (newValue.length !in 0 .. 15) {
            return
        }

        reduce {
            copy(title = newValue)
        }
    }

    private fun handleOnClickSubmit(intent: RegistrationPostIntent.OnClickSubmit) {
        analyticsHelper.d(message = "handleOnClickSubmit - intent: $intent")

        val submitData = currentState

        if (null == submitData.currentMedia) {
            analyticsHelper.d(message = "handleOnClickSubmit - submitData.currentMedia is null")
            return
        }

        if (submitData.title.isEmpty()) {
            analyticsHelper.d(message = "handleOnClickSubmit - submitData.title is empty")
            return
        }

        reduce {
            copy(isOpenSubmitBottomSheet = true)
        }
    }
}