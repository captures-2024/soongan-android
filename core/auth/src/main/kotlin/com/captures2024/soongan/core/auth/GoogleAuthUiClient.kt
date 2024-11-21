package com.captures2024.soongan.core.auth

import android.content.Context
import android.content.Intent
import android.content.IntentSender
import android.util.Log
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import kotlin.coroutines.cancellation.CancellationException
import kotlinx.coroutines.tasks.await

class GoogleAuthUiClient(
    private val oneTapClient: SignInClient,
    private val signInRequest: BeginSignInRequest,
    private val context: Context,
    private val credentialManager: CredentialManager,
    private val googleIdOption: GetGoogleIdOption,
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
        Log.d("Test", "credential.id = ${credential.id}")
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

    suspend fun requestGoogleLogin(): String? {
        val request: GetCredentialRequest = GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()

        val response = runCatching {
            credentialManager.getCredential(
                request = request,
                context = context,
            )
        }.onFailure {
            Log.e("Test", "error credentialManager.getCredential", it)
            it.printStackTrace()
        }.getOrNull()

        if (response == null) {
            Log.d("Test", "unknown error")
            return null
        }

        val credential = response.credential

        if (credential !is CustomCredential) {
            Log.d("Test", "credential is not CustomCredential")
            return ""
        }

        val type = credential.type

        if (type != GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
            Log.d("Test", "credential.type is not TYPE_GOOGLE_ID_TOKEN_CREDENTIAL")
            return ""
        }

        val idToken = kotlin.runCatching { GoogleIdTokenCredential.createFrom(credential.data).idToken }
            .onFailure { Log.e("Test", "Received an invalid google id token response", it) }
            .getOrNull()

        return idToken
    }
}
