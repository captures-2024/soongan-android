package com.captures2024.soongan.plugin

import com.android.build.gradle.BaseExtension
import java.util.Properties
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

internal fun Project.configureAndroidCommonPlugin() {
    val properties = Properties().apply {
        load(rootProject.file("local.properties").inputStream())
    }

    apply<AndroidKotlinPlugin>()
    apply<KotlinSerializationPlugin>()
    apply<KotlinCoroutinesPlugin>()
    with(plugins) {
        apply("kotlin-parcelize")
        apply("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
    }
    apply<AndroidHiltPlugin>()

    extensions.getByType<BaseExtension>().apply {
        defaultConfig {
            val googleApiKey = properties["googleApiKey"] as? String ?: ""
            val googleWebClientId = properties["googleWebClientId"] as? String ?: ""
            val kakaoApiKey = properties["kakaoApiKey"] as? String ?: ""
            val capturesBaseUrl = properties["capturesBaseUrl"] as? String ?: ""

            manifestPlaceholders["googleApiKey"] = properties["googleApiKey"] as String
            manifestPlaceholders["googleWebClientId"] = properties["googleWebClientId"] as String
            manifestPlaceholders["kakaoApiKey"] = properties["kakaoApiKey"] as String
            manifestPlaceholders["capturesBaseUrl"] = properties["capturesBaseUrl"] as String

            buildConfigField("String", "GOOGLE_API_KEY", "\"${googleApiKey}\"")
            buildConfigField("String", "GOOGLE_WEB_CLIENT_ID", "\"${googleWebClientId}\"")
            buildConfigField("String", "KAKAO_API_KEY", "\"${kakaoApiKey}\"")
            buildConfigField("String", "CAPTURES_BASE_URL", "\"${capturesBaseUrl}\"")
        }
        buildFeatures.apply {
            viewBinding = true
            buildConfig = true
        }
    }

    val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
    dependencies {
        "implementation"(libs.findLibrary("core.ktx").get())
        "implementation"(libs.findLibrary("appcompat").get())
        "implementation"(libs.findBundle("lifecycle").get())
        "implementation"(libs.findLibrary("material").get())
        "implementation"(libs.findLibrary("fragment.ktx").get())
        "implementation"(libs.findLibrary("splash-screen").get())
        "implementation"(libs.findLibrary("timber").get())
    }
}
