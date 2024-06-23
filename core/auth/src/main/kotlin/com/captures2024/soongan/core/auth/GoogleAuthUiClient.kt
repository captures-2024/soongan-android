package com.captures2024.soongan.core.auth

import android.content.Intent
import android.content.IntentSender
import android.util.Log
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.GoogleAuthProvider
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

    suspend fun signInWithIntent(intent: Intent) : com.captures2024.soongan.core.model.SignInResult {
        val credential = oneTapClient.getSignInCredentialFromIntent(intent)
        val googleIdToken = credential.googleIdToken
        val googleCredentials = GoogleAuthProvider.getCredential(googleIdToken, null)
        Log.d("token", "googleIdToken = $googleIdToken")
//        return try {
//            val user = auth.signInWithCredential(googleCredentials).await().user
//            SignInResult(
//                data = user?.run {
//                    UserData(
//                        userId = uid,
//                        username = displayName,
//                        profilePictureUrl = photoUrl?.toString()
//                    )
//                },
//                errorMessage = null
//            )
//        } catch (e: Exception) {
//            e.printStackTrace()
//            if(e is CancellationException) throw e
//            SignInResult(
//                data = null,
//                errorMessage = e.message
//            )
//        }
        return com.captures2024.soongan.core.model.SignInResult(
            data = null,
            errorMessage = ""
        )
    }

    suspend fun signOut() {
        try {
            oneTapClient.signOut().await()
//            auth.signOut()
        } catch(e: Exception) {
            e.printStackTrace()
            if(e is CancellationException) throw e
        }
    }

//    fun getSignedInUser(): UserData? = auth.currentUser?.run {
//        UserData(
//            userId = uid,
//            username = displayName,
//            profilePictureUrl = photoUrl?.toString()
//        )
//    }
}