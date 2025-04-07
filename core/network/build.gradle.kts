import com.captures2024.soongan.plugin.implementation

plugins {
    alias(libs.plugins.captures2024.soongan.android.library)
    alias(libs.plugins.captures2024.soongan.android.hilt)
    alias(libs.plugins.captures2024.soongan.okhttp)
    alias(libs.plugins.captures2024.soongan.retrofit)
}

android {
    namespace = "com.captures2024.soongan.core.network"

    defaultConfig {
        val properties = loadProperties()

        val capturesBaseUrl = DefaultKeyValue.isAllowedBaseUrl(properties["capturesBaseUrl"] as? String)
        val headerKey = DefaultKeyValue.isAllowedHeaderKey(properties["headerKey"] as? String)
        val headerValue = DefaultKeyValue.isAllowedHeaderKey(properties["headerValue"] as? String)

        manifestPlaceholders["CAPTURES_BASE_URL"] = capturesBaseUrl
        manifestPlaceholders["HEADER_KEY"] = headerKey
        manifestPlaceholders["HEADER_VALUE"] = headerValue

        buildConfigField("String", "CAPTURES_BASE_URL", "\"${capturesBaseUrl}\"")
        buildConfigField("String", "HEADER_KEY", "\"${headerKey}\"")
        buildConfigField("String", "HEADER_VALUE", "\"${headerValue}\"")
    }
}

dependencies {
    implementation(projects.core.analytics)
    implementation(projects.core.datastore)
    implementation(projects.core.model)
}
