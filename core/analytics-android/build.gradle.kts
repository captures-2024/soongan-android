plugins {
    captures("library")
    captures("compose")
}

android {
    namespace = "com.captures2024.soongan.core.analytics.android"
}

dependencies {
    implementation(libs.android.compose.runtime)
    implementation(libs.aakira.napier)
}
