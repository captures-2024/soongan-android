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
    implementation(projects.core.domain)
    implementation(projects.core.model)
    implementation(projects.core.navigator)

    implementation(projects.data.datastore)
    implementation(projects.data.network)

    implementation(projects.data.source.auth)
    implementation(projects.data.source.authImpl)
    implementation(projects.data.source.contest)
    implementation(projects.data.source.contestImpl)
    implementation(projects.data.source.fcm)
    implementation(projects.data.source.fcmImpl)
    implementation(projects.data.source.home)
    implementation(projects.data.source.homeImpl)
    implementation(projects.data.source.member)
    implementation(projects.data.source.memberImpl)
    implementation(projects.data.source.notification)
    implementation(projects.data.source.notificationImpl)
    implementation(projects.data.source.report)
    implementation(projects.data.source.reportImpl)
    implementation(projects.data.source.system)
    implementation(projects.data.source.systemImpl)
    implementation(projects.data.source.utils)

    implementation(projects.presentation.designSystem.icon)
    implementation(projects.presentation.designSystem.ui)

    implementation(projects.presentation.feature.main)
    implementation(projects.presentation.feature.sign)

    implementation(projects.presentation.viewmodel)

    implementation(libs.google.firebase.crashlytics)

    implementation(libs.android.startup)
    implementation(libs.kakao.login)
}