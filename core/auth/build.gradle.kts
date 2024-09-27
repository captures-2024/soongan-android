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

        manifestPlaceholders["GOOGLE_WEB_CLIENT_ID"] = googleWebClientId
        manifestPlaceholders["KAKAO_API_KEY"] = kakaoApiKey

        buildConfigField("String", "GOOGLE_WEB_CLIENT_ID", "\"${googleWebClientId}\"")
        buildConfigField("String", "KAKAO_API_KEY", "\"${kakaoApiKey}\"")
    }
}

dependencies {
    implementation(project(":core:model"))

    implementation(libs.kakao.login)
}
