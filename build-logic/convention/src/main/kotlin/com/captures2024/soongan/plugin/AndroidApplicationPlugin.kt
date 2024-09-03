package com.captures2024.soongan.plugin

import com.android.build.gradle.BaseExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class AndroidApplicationPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(plugins) {
            apply("com.android.application")
            apply("com.google.gms.google-services")
        }

        apply<AndroidComposePlugin>()
        apply<AndroidTestPlugin>()
        apply<FirebasePlugin>()
        apply<GoogleAuthPlugin>()

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
            add("implementation", project(":core:analytics"))
            add("implementation", project(":core:auth"))
            add("implementation", project(":core:common"))
            add("implementation", project(":core:data"))
            add("implementation", project(":core:datastore"))
            add("implementation", project(":core:designSystem"))
            add("implementation", project(":core:domain"))
            add("implementation", project(":core:model"))
            add("implementation", project(":core:navigator"))
            add("implementation", project(":core:network"))

            add("implementation", project(":feature:awards"))
            add("implementation", project(":feature:feed"))
            add("implementation", project(":feature:home"))
            add("implementation", project(":feature:intro"))
            add("implementation", project(":feature:main"))
            add("implementation", project(":feature:privacyPolicy"))
            add("implementation", project(":feature:sign"))
            add("implementation", project(":feature:signIn"))
            add("implementation", project(":feature:signUp"))
            add("implementation", project(":feature:termsOfUse"))
            add("implementation", project(":feature:welcome"))

            add("implementation", libs.findLibrary("core.ktx").get())
            add("implementation", libs.findBundle("lifecycle").get())
            add("implementation", libs.findLibrary("splash-screen").get())
            add("implementation", libs.findLibrary("kakao.login").get())
            add("implementation", libs.findLibrary("startup").get())
            add("implementation", libs.findLibrary("coil.compose").get())
        }
    }
}
