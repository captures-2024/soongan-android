plugins {
    captures("library")
    captures("compose")
}

android {
    namespace = "com.captures2024.soongan.core.design"
}

dependencies {
    implementation(libs.core.ktx)
    implementation(libs.splash.screen)
}