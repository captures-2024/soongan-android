package com.captures2024.soongan.feature.privacypolicy

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PrivacyPolicyViewModel
@Inject
constructor(

) : ViewModel() {

    companion object {
        private const val TAG = "PrivacyPolicyVM"
    }
}