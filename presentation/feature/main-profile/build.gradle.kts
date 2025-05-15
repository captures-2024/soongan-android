plugins {
    alias(libs.plugins.captures2024.soongan.android.feature)
}

android {
    namespace = "com.captures2024.soongan.presentation.feature.main.profile"
}

dependencies {
    implementation(projects.presentation.viewmodel)
}
