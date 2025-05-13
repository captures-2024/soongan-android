import com.captures2024.soongan.plugin.implementation

plugins {
    alias(libs.plugins.captures2024.soongan.android.application)
    alias(libs.plugins.captures2024.soongan.android.hilt)
    alias(libs.plugins.captures2024.soongan.google.auth)
    alias(libs.plugins.captures2024.soongan.google.firebase)
    alias(libs.plugins.captures2024.soongan.okhttp)
    alias(libs.plugins.captures2024.soongan.retrofit)
    alias(libs.plugins.captures2024.soongan.test.junit5)
    alias(libs.plugins.captures2024.soongan.test.kotest)
    alias(libs.plugins.google.crashlytics)
}

android {
    namespace = "com.captures2024.soongan"

    defaultConfig {
        val properties = loadProperties()

        val kakaoApiKey = DefaultKeyValue.isAllowedBaseUrl(properties["kakaoApiKey"] as? String)

        manifestPlaceholders["KAKAO_API_KEY"] = kakaoApiKey

        buildConfigField("String", "KAKAO_API_KEY", "\"${kakaoApiKey}\"")
    }

    signingConfigs {
        getByName("debug") {
            storeFile = File("${project.rootDir.absolutePath}/keystore/debug.keystore")
            storePassword = "android"
            keyAlias = "androiddebugkey"
            keyPassword = "android"
        }

        create("release") {
            val properties = loadKeyProperties()

            storeFile = File("${project.rootDir.absolutePath}/keystore/release.keystore.jks")
            storePassword = properties.getProperty("storePassword")
            keyAlias = properties.getProperty("keyAlias")
            keyPassword = properties.getProperty("keyPassword")
        }
    }

    buildTypes {
        getByName("debug") {
            isDebuggable = true
            manifestPlaceholders += mapOf(
                "appName" to "@string/app_name_dev",
            )
        }

        getByName("release") {
            isDebuggable = false
            signingConfig = signingConfigs.getByName("release")
            manifestPlaceholders += mapOf(
                "appName" to "@string/app_name",
            )
        }
    }
}

dependencies {
    implementation(projects.core.analytics)
    implementation(projects.core.analyticsAndroid)
    implementation(projects.core.auth)
    implementation(projects.core.common)
    implementation(projects.core.data)
    implementation(projects.core.datastore)
    implementation(projects.core.designSystem.icon)
    implementation(projects.core.designSystem.ui)
    implementation(projects.core.domain)
    implementation(projects.core.model)
    implementation(projects.core.navigator)
    implementation(projects.core.network)
    implementation(projects.core.viewmodel)

    implementation(projects.feature.awards)
    implementation(projects.feature.feed)
    implementation(projects.feature.home)
    implementation(projects.feature.intro)
    implementation(projects.feature.main)
    implementation(projects.feature.profile)
    implementation(projects.feature.welcome)

    implementation(projects.presentation.feature.main)
    implementation(projects.presentation.feature.sign)

    implementation(projects.presentation.viewmodel)

    implementation(libs.google.firebase.crashlytics)

    implementation(libs.android.startup)
    implementation(libs.kakao.login)
}