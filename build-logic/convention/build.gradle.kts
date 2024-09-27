@file:Suppress("DSL_SCOPE_VIOLATION", "INLINE_FROM_HIGHER_PLATFORM")

plugins {
    `kotlin-dsl`
    alias(libs.plugins.gradle.dependency.handler.extensions)
}

group = "com.captures2024.soongan.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}


dependencies {
    compileOnly(libs.android.gradle.plugin)
    compileOnly(libs.kotlin.gradle.plugin)
    compileOnly(libs.google.ksp.gradle.plugin)

    compileOnly(files((libs as Any).javaClass.superclass.protectionDomain.codeSource.location))
}

gradlePlugin {
    plugins {
        create("android-application") {
            id = "com.captures2024.soongan.application"
            implementationClass = "com.captures2024.soongan.plugin.AndroidApplicationPlugin"
        }
        create("android-compose") {
            id = "com.captures2024.soongan.compose"
            implementationClass = "com.captures2024.soongan.plugin.AndroidComposePlugin"
        }
        create("android-feature") {
            id = "com.captures2024.soongan.feature"
            implementationClass = "com.captures2024.soongan.plugin.AndroidFeaturePlugin"
        }
        create("android-hilt") {
            id = "com.captures2024.soongan.hilt"
            implementationClass = "com.captures2024.soongan.plugin.AndroidHiltPlugin"
        }
        create("android-library") {
            id = "com.captures2024.soongan.library"
            implementationClass = "com.captures2024.soongan.plugin.AndroidLibraryPlugin"
        }
        create("android-network") {
            id = "com.captures2024.soongan.network"
            implementationClass = "com.captures2024.soongan.plugin.AndroidNetworkPlugin"
        }
        create("android-room") {
            id = "com.captures2024.soongan.room"
            implementationClass = "com.captures2024.soongan.plugin.AndroidRoomPlugin"
        }
        create("android-test") {
            id = "com.captures2024.soongan.test"
            implementationClass = "com.captures2024.soongan.plugin.AndroidTestPlugin"
        }
        create("google-auth") {
            id = "com.captures2024.soongan.google.auth"
            implementationClass = "com.captures2024.soongan.plugin.GoogleAuthPlugin"
        }
        create("google-firebase") {
            id = "com.captures2024.soongan.google.firebase"
            implementationClass = "com.captures2024.soongan.plugin.GoogleFirebasePlugin"
        }
        create("kotest") {
            id = "com.captures2024.soongan.kotest"
            implementationClass = "com.captures2024.soongan.plugin.KotestPlugin"
        }
        create("kotlin-android") {
            id = "com.captures2024.soongan.kotlin.android"
            implementationClass = "com.captures2024.soongan.plugin.KotlinAndroidPlugin"
        }
    }
}

