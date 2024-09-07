package com.captures2024.soongan.core.auth

import android.content.Intent
import android.content.IntentSender
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.SignInClient
import kotlin.coroutines.cancellation.CancellationException
import kotlinx.coroutines.tasks.await

class GoogleAuthUiClient(
    private val oneTapClient: SignInClient,
    private val signInRequest: BeginSignInRequest
) {

    suspend fun signIn(): IntentSender? {
        val result = try {
            oneTapClient.beginSignIn(signInRequest).await()
        } catch(e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
            null
        }
        return result?.pendingIntent?.intentSender
    }

    fun signInWithIntent(intent: Intent): String? {
        val credential = oneTapClient.getSignInCredentialFromIntent(intent)
        val googleIdToken = credential.googleIdToken
        return googleIdToken
    }

    suspend fun signOut() {
        try {
            oneTapClient.signOut().await()
        } catch(e: Exception) {
            e.printStackTrace()
            if(e is CancellationException) throw e
        }
    }

}