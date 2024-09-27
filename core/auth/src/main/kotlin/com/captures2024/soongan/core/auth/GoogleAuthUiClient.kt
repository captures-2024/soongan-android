package com.captures2024.soongan.core.auth

import android.content.Intent
import android.content.IntentSender
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.SignInClient
import kotlin.coroutines.cancellation.CancellationException
import kotlinx.coroutines.tasks.await

class GoogleAuthUiClient(
    private val oneTapClient: SignInClient,
    private val signInRequest: BeginSignInRequest,
) {

    suspend fun signIn(): IntentSender? {
        val result = kotlin.runCatching {
            oneTapClient.beginSignIn(signInRequest).await()
        }.onFailure {
            it.printStackTrace()
            if (it is CancellationException) throw it
        }.getOrNull()

        return result?.pendingIntent?.intentSender
    }

    fun signInWithIntent(intent: Intent): String? {
        val credential = oneTapClient.getSignInCredentialFromIntent(intent)
        val googleIdToken = credential.googleIdToken
        return googleIdToken
    }

    suspend fun signOut() {
        kotlin.runCatching {
            oneTapClient.signOut().await()
        }.onFailure {
            it.printStackTrace()
            if (it is CancellationException) throw it
        }
    }
}
