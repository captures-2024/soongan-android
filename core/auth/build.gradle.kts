import com.captures2024.soongan.plugin.implementation

plugins {
    alias(libs.plugins.captures2024.soongan.android.library)
    alias(libs.plugins.captures2024.soongan.android.hilt)
    alias(libs.plugins.captures2024.soongan.google.auth)
    alias(libs.plugins.captures2024.soongan.google.firebase)
}

android {
    namespace = "com.captures2024.soongan.core.auth"

    defaultConfig {
        val properties = loadProperties()

        val googleWebClientId = DefaultKeyValue.isAllowedGoogleClientId(properties["googleWebClientId"] as? String)
        val kakaoApiKey = DefaultKeyValue.isAllowedBaseUrl(properties["kakaoApiKey"] as? String)
        val appleEndpoint = DefaultKeyValue.isAllowedAppleEndpoint(properties["appleAuthEndpoint"] as? String)
        val appleRedirectUrl = DefaultKeyValue.isAllowedAppleEndpoint(properties["appleRedirectUrl"] as? String)
        val appleResponseUrl = DefaultKeyValue.isAllowedAppleEndpoint(properties["appleResponseUrl"] as? String)

        manifestPlaceholders["GOOGLE_WEB_CLIENT_ID"] = googleWebClientId
        manifestPlaceholders["KAKAO_API_KEY"] = kakaoApiKey
        manifestPlaceholders["APPLE_ENDPOINT"] = appleEndpoint
        manifestPlaceholders["APPLE_REDIRECT_URL"] = appleRedirectUrl
        manifestPlaceholders["APPLE_RESPONSE_URL"] = appleResponseUrl

        buildConfigField("String", "GOOGLE_WEB_CLIENT_ID", "\"${googleWebClientId}\"")
        buildConfigField("String", "KAKAO_API_KEY", "\"${kakaoApiKey}\"")
        buildConfigField("String", "APPLE_ENDPOINT", "\"${appleEndpoint}\"")
        buildConfigField("String", "APPLE_REDIRECT_URL", "\"${appleRedirectUrl}\"")
        buildConfigField("String", "APPLE_RESPONSE_URL", "\"${appleResponseUrl}\"")
    }
}

dependencies {
    implementation(projects.core.analytics)
    implementation(projects.core.model)

    implementation(libs.kakao.login)
}
