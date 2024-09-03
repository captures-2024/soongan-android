plugins {
    captures("application")
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