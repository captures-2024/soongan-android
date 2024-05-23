package com.captures2024.soongan.feature.intro

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.captures2024.soongan.feature.intro.IntroActivityUiState.Loading
import com.captures2024.soongan.feature.intro.IntroActivityUiState.Success
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class IntroViewModel
@Inject
constructor(

) : ViewModel() {
    val introState: StateFlow<IntroState> = flow<IntroState> {
        delay(100)
        emit(IntroState.Sign)
    }.stateIn(
        scope = viewModelScope,
        initialValue = IntroState.Loading,
        started = SharingStarted.WhileSubscribed(5_000),
    )

    companion object {
        private const val TAG = "IntroVM"
    }
}


/**
 * State 설명
 *
 * 차후 JWT가 추가되었을 때 Token의 만료 여부로 sign, main으로 진입 시점을 저장
 *
 * @property Loading
 * @property Sign
 * @property Main
 **/
sealed interface IntroState {
    data object Loading : IntroState
    data object Sign : IntroState
    data object Main : IntroState
}