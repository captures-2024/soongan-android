plugins {
    alias(libs.plugins.captures2024.soongan.android.feature)
}

android {
    namespace = "com.captures2024.soongan.presentation.feature.main.feed"
}

dependencies {
    implementation(projects.presentation.viewmodel)
    implementation("androidx.compose.material3:material3:1.4.0-alpha02")
    implementation("androidx.compose.material:material-icons-core:1.7.8")
}
