package com.captures2024.soongan.core.auth

import android.util.Base64
import android.util.Log
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import org.json.JSONObject

class AppleWebViewClient(
    private val onSuccess: (idToken: String) -> Unit,
    private val onFailure: () -> Unit,
    private val responseUrl: String,
) : WebViewClient() {

    override fun shouldOverrideUrlLoading(
        view: WebView?, request: WebResourceRequest?
    ): Boolean {
        val url = request?.url

        return when (url.toString().contains(responseUrl)) {
            true -> {
                val idToken = url?.getQueryParameter("id_token")
                if (idToken != null) {
                    val decodedIdToken = decodeJWT(idToken)
                    val email = JSONObject(decodedIdToken).getString("email")

//                    appleLoginCallback.onSuccess(idToken)
                    Log.d("AppleWebViewClient", "1url == true")
                    return true
                }
//                appleLoginCallback.onFailure(null)

                Log.d("AppleWebViewClient", "1url == false")
                return false
            }

            false -> {
                Log.d("AppleWebViewClient", "2url == false")
//                appleLoginCallback.onFailure(null)
                false
            }
        }
    }

    private fun decodeJWT(jwt: String): String {
        val decodedJson = kotlin.runCatching {
            val split = jwt.split("\\.".toRegex()).toTypedArray()
            String(Base64.decode(split[1], Base64.URL_SAFE), charset("UTF-8"))
        }.getOrNull() ?: ""

        return decodedJson
    }
}
