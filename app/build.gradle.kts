plugins {
    captures("application")
    captures("compose")
    captures("test")
    captures("google-auth")
    captures("firebase")
}

android {
    namespace = "com.captures2024.soongan"

    defaultConfig {
        applicationId = "com.captures2024.soongan"
        versionCode = libs.versions.versionCode.get().toInt()
        versionName = libs.versions.appVersion.get()
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
        debug {
            isDebuggable = true
//            signingConfig = signingConfigs.getByName("debug")
        }
        release {
//            isMinifyEnabled = true
//            isShrinkResources = true
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
//            signingConfig = signingConfigs.getByName("release")
        }
    }
}

dependencies {

    //region core module
    implementation(project(":core:analytics"))
    implementation(project(":core:auth"))
    implementation(project(":core:common"))
    implementation(project(":core:designSystem"))
    implementation(project(":core:data"))
    implementation(project(":core:datastore"))
    implementation(project(":core:domain"))
    implementation(project(":core:model"))
    implementation(project(":core:network"))
    //endregion

    //region feature module
    implementation(project(":feature:awards"))
    implementation(project(":feature:feed"))
    implementation(project(":feature:home"))
    implementation(project(":feature:intro"))
    implementation(project(":feature:main"))
    implementation(project(":feature:privacyPolicy"))
    implementation(project(":feature:profile"))
    implementation(project(":feature:sign"))
    implementation(project(":feature:signIn"))
    implementation(project(":feature:signUp"))
    implementation(project(":feature:termsOfUse"))
    implementation(project(":feature:welcome"))
    //endregion

    androidTestImplementation(libs.ui.test.junit4)
    implementation(libs.startup)
    implementation(libs.kakao.login)
}