plugins {
    captures("library")
    captures("compose")
}

android {
    namespace = "com.captures2024.soongan.core.common"
}

dependencies {
    implementation(libs.compose.runtime)
}
