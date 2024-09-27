plugins {
    captures("library")
    captures("compose")
}

android {
    namespace = "com.captures2024.soongan.core.design"
}

dependencies {
    implementation(libs.android.xml.core)
    implementation(libs.android.splash.screen)
}
