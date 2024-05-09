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
        create("android-library") {
            id = "com.captures2024.soongan.library"
            implementationClass = "com.captures2024.soongan.plugin.AndroidLibraryPlugin"
        }
        create("android-kotlin") {
            id = "com.captures2024.soongan.kotlin"
            implementationClass = "com.captures2024.soongan.plugin.AndroidKotlinPlugin"
        }
        create("android-hilt") {
            id = "com.captures2024.soongan.hilt"
            implementationClass = "com.captures2024.soongan.plugin.AndroidHiltPlugin"
        }
        create("android-room") {
            id = "com.captures2024.soongan.room"
            implementationClass = "com.captures2024.soongan.plugin.AndroidRoomConventionPlugin"
        }
        create("kotlin-serialization") {
            id = "com.captures2024.soongan.serialization"
            implementationClass = "com.captures2024.soongan.plugin.KotlinSerializationPlugin"
        }
        create("junit5") {
            id = "ccom.captures2024.soongan.junit5"
            implementationClass = "com.captures2024.soongan.plugin.JUnit5Plugin"
        }
        create("android-test") {
            id = "com.captures2024.soongan.test"
            implementationClass = "com.captures2024.soongan.plugin.AndroidTestPlugin"
        }
        create("compose") {
            id = "com.captures2024.soongan.compose"
            implementationClass = "com.captures2024.soongan.plugin.ComposePlugin"
        }
        create("google-auth") {
            id = "com.captures2024.soongan.google-auth"
            implementationClass = "com.captures2024.soongan.plugin.GoogleAuthPlugin"
        }
    }
}

