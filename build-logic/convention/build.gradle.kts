plugins {
    `kotlin-dsl`
}

group = "com.captures2024.soongan.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}


dependencies {
    compileOnly(libs.agp)
    compileOnly(libs.kotlin.gradleplugin)
    compileOnly(libs.ksp.gradlePlugin)
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
        create("android-kotlin") {
            id = "com.captures2024.soongan.kotlin"
            implementationClass = "com.captures2024.soongan.plugin.AndroidKotlinPlugin"
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
            implementationClass = "com.captures2024.soongan.plugin.AndroidRoomConventionPlugin"
        }
        create("android-test") {
            id = "com.captures2024.soongan.test"
            implementationClass = "com.captures2024.soongan.plugin.AndroidTestPlugin"
        }
        create("android-firebase") {
            id = "com.captures2024.soongan.firebase"
            implementationClass = "com.captures2024.soongan.plugin.FirebasePlugin"
        }
        create("android-google-auth") {
            id = "com.captures2024.soongan.google_auth"
            implementationClass = "com.captures2024.soongan.plugin.GoogleAuthPlugin"
        }
        create("android-junit5") {
            id = "ccom.captures2024.soongan.junit5"
            implementationClass = "com.captures2024.soongan.plugin.JUnit5Plugin"
        }
        create("kotlin-coroutines") {
            id = "ccom.captures2024.soongan.coroutines"
            implementationClass = "com.captures2024.soongan.plugin.KotlinCoroutinesPlugin"
        }
        create("kotlin-serialization") {
            id = "com.captures2024.soongan.serialization"
            implementationClass = "com.captures2024.soongan.plugin.KotlinSerializationPlugin"
        }
        create("timber") {
            id = "com.captures2024.soongan.timber"
            implementationClass = "com.captures2024.soongan.plugin.TimberPlugin"
        }
    }
}

