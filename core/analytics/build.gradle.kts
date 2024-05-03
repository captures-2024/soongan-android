plugins {
    captures("library")
    captures("compose")
}

android {
    namespace = "com.captures2024.soongan.core.analytics"
}

dependencies {
    implementation(libs.compose.runtime)
}
