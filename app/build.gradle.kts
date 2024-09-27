import com.captures2024.soongan.plugin.implementation

plugins {
    alias(libs.plugins.captures2024.soongan.android.application)
    alias(libs.plugins.captures2024.soongan.android.application.compose)
    alias(libs.plugins.captures2024.soongan.android.hilt)
    alias(libs.plugins.captures2024.soongan.google.auth)
    alias(libs.plugins.captures2024.soongan.google.firebase)
    alias(libs.plugins.captures2024.soongan.okhttp)
    alias(libs.plugins.captures2024.soongan.retrofit)
    alias(libs.plugins.captures2024.soongan.test.junit5)
    alias(libs.plugins.captures2024.soongan.test.kotest)
}

android {
    namespace = "com.captures2024.soongan"

    defaultConfig {
        applicationId = "com.captures2024.soongan"
        versionCode = libs.versions.versionCode.get().toInt()
        versionName = libs.versions.appVersion.get()

        val properties = loadProperties()

        val kakaoApiKey = DefaultKeyValue.isAllowedBaseUrl(properties["kakaoApiKey"] as? String)

        manifestPlaceholders["KAKAO_API_KEY"] = kakaoApiKey

        buildConfigField("String", "KAKAO_API_KEY", "\"${kakaoApiKey}\"")
    }

//    signingConfigs {
//        getByName("debug") {
//            keyAlias = "android_debug_key"
//            keyPassword = "android"
//            storeFile = File("${project.rootDir.absolutePath}/keystore/debug.keystore")
//            storePassword = "android"
//        }
//        create("release") {
//            keyAlias = properties.getProperty("keyAlias")
//            keyPassword = properties.getProperty("keyPassword")
//            storeFile = File("${project.rootDir.absolutePath}/keystore/release.keystore.jks")
//            storePassword = properties.getProperty("storePassword")
//        }
//    }

    buildTypes {
        getByName("debug") {
            isDebuggable = true
            manifestPlaceholders += mapOf(
                "appName" to "@string/app_name_dev",
            )
        }

        getByName("release") {
            isDebuggable = false
//            signingConfig = signingConfigs.getByName("release")
            manifestPlaceholders += mapOf(
                "appName" to "@string/app_name",
            )
        }
    }
}

dependencies {
    implementation(projects.core.analyticsAndroid)
    implementation(projects.core.auth)
    implementation(projects.core.common)
    implementation(projects.core.data)
    implementation(projects.core.datastore)
    implementation(projects.core.designSystem)
    implementation(projects.core.domain)
    implementation(projects.core.model)
    implementation(projects.core.navigator)
    implementation(projects.core.network)

    implementation(projects.feature.awards)
    implementation(projects.feature.feed)
    implementation(projects.feature.home)
    implementation(projects.feature.intro)
    implementation(projects.feature.main)
    implementation(projects.feature.privacyPolicy)
    implementation(projects.feature.profile)
    implementation(projects.feature.sign)
    implementation(projects.feature.signIn)
    implementation(projects.feature.signUp)
    implementation(projects.feature.termsOfUse)
    implementation(projects.feature.welcome)

    implementation(libs.android.startup)
    implementation(libs.kakao.login)
}