plugins {
    alias(libs.plugins.captures2024.soongan.android.library)
    alias(libs.plugins.captures2024.soongan.android.library.compose)
}

android {
    namespace = "com.captures2024.soongan.core.common"
}

dependencies {
    implementation(libs.android.compose.runtime)
}
