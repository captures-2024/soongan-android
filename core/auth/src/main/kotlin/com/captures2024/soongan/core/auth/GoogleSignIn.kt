package com.captures2024.soongan.core.auth

import android.content.Context
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential

suspend fun Context.requestGoogleLogin(): String? {
    val googleIdOption = GetGoogleIdOption.Builder()
        .setFilterByAuthorizedAccounts(false)
        .setAutoSelectEnabled(false)
        .setServerClientId(BuildConfig.GOOGLE_WEB_CLIENT_ID)
        .build()

    val request: GetCredentialRequest = GetCredentialRequest.Builder()
        .addCredentialOption(googleIdOption)
        .build()

    val credentialManager: CredentialManager = CredentialManager.create(this)

    val response = runCatching {
        credentialManager.getCredential(
            request = request,
            context = this,
        )
    }.onFailure {
        it.printStackTrace()
    }.getOrNull()

    if (response == null) {
        return null
    }

    val credential = response.credential

    if (credential !is CustomCredential) {
        return null
    }

    val type = credential.type

    if (type != GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
        return null
    }

    val idToken = kotlin.runCatching { GoogleIdTokenCredential.createFrom(credential.data).idToken }.getOrNull()

    return idToken
}
