plugins {
    `kotlin-dsl`
}

group = "com.captures.soongan.buildlogic"

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
            id = "com.captures.soongan.application"
            implementationClass = "com.captures.soongan.plugin.AndroidApplicationPlugin"
        }
        create("android-library") {
            id = "com.captures.soongan.library"
            implementationClass = "com.captures.soongan.plugin.AndroidLibraryPlugin"
        }
        create("android-kotlin") {
            id = "com.captures.soongan.kotlin"
            implementationClass = "com.captures.soongan.plugin.AndroidKotlinPlugin"
        }
        create("android-hilt") {
            id = "com.captures.soongan.hilt"
            implementationClass = "com.captures.soongan.plugin.AndroidHiltPlugin"
        }
        create("android-room") {
            id = "com.captures.soongan.room"
            implementationClass = "com.captures.soongan.plugin.AndroidRoomConventionPlugin"
        }
        create("kotlin-serialization") {
            id = "com.captures.soongan.serialization"
            implementationClass = "com.captures.soongan.plugin.KotlinSerializationPlugin"
        }
        create("junit5") {
            id = "ccom.captures.soongan.junit5"
            implementationClass = "com.captures.soongan.plugin.JUnit5Plugin"
        }
        create("android-test") {
            id = "com.captures.soongan.test"
            implementationClass = "com.captures.soongan.plugin.AndroidTestPlugin"
        }
        create("compose") {
            id = "com.captures.soongan.compose"
            implementationClass = "com.captures.soongan.plugin.ComposePlugin"
        }
    }
}

