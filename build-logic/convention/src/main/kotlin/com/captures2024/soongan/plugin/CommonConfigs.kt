package com.captures2024.soongan.plugin

import com.android.build.gradle.BaseExtension
import java.util.Properties
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.getByType

internal fun Project.configureAndroidCommonPlugin() {
    val properties = Properties().apply {
        load(rootProject.file("local.properties").inputStream())
    }

    with(plugins) {
        apply("kotlin-parcelize")
        apply("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
    }

    apply<AndroidKotlinPlugin>()
    apply<KotlinSerializationPlugin>()
    apply<KotlinCoroutinesPlugin>()
    apply<AndroidHiltPlugin>()
    apply<TimberPlugin>()

    extensions.getByType<BaseExtension>().apply {
        defaultConfig {
            val googleApiKey = properties["googleApiKey"] as? String ?: ""
            val googleWebClientId = properties["googleWebClientId"] as? String ?: ""
            val capturesBaseUrl = properties["capturesBaseUrl"] as? String ?: ""

            manifestPlaceholders["googleApiKey"] = properties["googleApiKey"] as String
            manifestPlaceholders["googleWebClientId"] = properties["googleWebClientId"] as String
            manifestPlaceholders["capturesBaseUrl"] = properties["capturesBaseUrl"] as String

            buildConfigField("String", "GOOGLE_API_KEY", "\"${googleApiKey}\"")
            buildConfigField("String", "GOOGLE_WEB_CLIENT_ID", "\"${googleWebClientId}\"")
            buildConfigField("String", "CAPTURES_BASE_URL", "\"${capturesBaseUrl}\"")
        }
        buildFeatures.apply {
            viewBinding = true
            buildConfig = true
        }
    }
}
