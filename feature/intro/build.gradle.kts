plugins {
    alias(libs.plugins.captures2024.soongan.android.feature)
}

android {
    namespace = "com.captures2024.soongan.feature.intro"
}

dependencies {
    implementation(libs.android.splash.screen)
}
