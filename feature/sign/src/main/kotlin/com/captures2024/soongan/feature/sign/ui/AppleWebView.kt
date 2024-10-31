package com.captures2024.soongan.feature.sign.ui

import android.annotation.SuppressLint
import android.webkit.WebView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.captures2024.soongan.core.auth.AppleWebViewClient
import com.captures2024.soongan.core.auth.BuildConfig
import java.util.UUID

@SuppressLint("SetJavaScriptEnabled")
@Composable
internal fun AppleWebView(
    onSuccess: (idToken: String) -> Unit,
    onFailure: () -> Unit,
) {
    val authEndpoint = BuildConfig.APPLE_ENDPOINT
    val responseType = "code%20id_token"
    val responseMode = "form_post"
    val clientId = "com.soongan"
    val scope = "email"
    val state = UUID.randomUUID().toString()
    val redirectUrl = BuildConfig.APPLE_REDIRECT_URL

    val context = LocalContext.current

    val url = authEndpoint +
            "?response_type=$responseType" +
            "&response_mode=$responseMode" +
            "&client_id=$clientId" +
            "&scope=$scope" +
            "&state=$state" +
            "&redirect_uri=$redirectUrl"

    Scaffold(modifier = Modifier.fillMaxSize()) { paddingValues ->
        AndroidView(
            factory = {
                WebView(context).apply {
                    settings.javaScriptEnabled = true
                    webViewClient = AppleWebViewClient(
                        onSuccess = onSuccess,
                        onFailure = onFailure,
                        responseUrl = BuildConfig.APPLE_RESPONSE_URL,
                    )
                }
            },
            update = {
                it.loadUrl(url)
            },
            modifier = Modifier.padding(paddingValues)
        )
    }
}