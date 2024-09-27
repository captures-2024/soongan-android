package com.captures2024.soongan.plugin

import com.android.build.gradle.BaseExtension
import com.captures2024.soongan.plugin.extension.implementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import java.util.Properties

class AndroidApplicationPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(plugins) {
            apply("com.android.application")
            apply("com.google.gms.google-services")
        }

        apply<AndroidComposePlugin>()
        apply<AndroidTestPlugin>()

        val properties = Properties().apply {
            load(rootProject.file("local.properties").inputStream())
        }

        extensions.getByType<BaseExtension>().apply {
            defaultConfig {
                val kakaoApiKey = properties["kakaoApiKey"] as? String ?: ""

                manifestPlaceholders["kakaoApiKey"] = kakaoApiKey

                buildConfigField("String", "KAKAO_API_KEY", "\"${kakaoApiKey}\"")
            }

//            signingConfigs {
//                getByName("debug") {
//                    keyAlias = "android_debug_key"
//                    keyPassword = "android"
//                    storeFile = File("${project.rootDir.absolutePath}/keystore/debug.keystore")
//                    storePassword = "android"
//                }
//                create("release") {
//                    keyAlias = properties.getProperty("keyAlias")
//                    keyPassword = properties.getProperty("keyPassword")
//                    storeFile = File("${project.rootDir.absolutePath}/keystore/release.keystore.jks")
//                    storePassword = properties.getProperty("storePassword")
//                }
//            }
        }

        configureAndroidCommonPlugin()

        val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

        dependencies {
            implementation(project(":core:analytics-android"))
            implementation(project(":core:auth"))
            implementation(project(":core:common"))
            implementation(project(":core:data"))
            implementation(project(":core:datastore"))
            implementation(project(":core:designSystem"))
            implementation(project(":core:domain"))
            implementation(project(":core:model"))
            implementation(project(":core:navigator"))
            implementation(project(":core:network"))

            implementation(project(":feature:awards"))
            implementation(project(":feature:feed"))
            implementation(project(":feature:home"))
            implementation(project(":feature:intro"))
            implementation(project(":feature:main"))
            implementation(project(":feature:privacyPolicy"))
            implementation(project(":feature:sign"))
            implementation(project(":feature:signIn"))
            implementation(project(":feature:signUp"))
            implementation(project(":feature:termsOfUse"))
            implementation(project(":feature:welcome"))

            implementation(libs.findLibrary("android-xml-core").get())
            implementation(libs.findBundle("lifecycle").get())
            implementation(libs.findLibrary("android-splash-screen").get())
            implementation(libs.findLibrary("kakao-login").get())
            implementation(libs.findLibrary("android-startup").get())
            implementation(libs.findLibrary("coil-compose").get())
        }
    }
}
