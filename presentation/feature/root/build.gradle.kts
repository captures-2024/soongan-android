import com.captures2024.soongan.plugin.implementation

plugins {
    alias(libs.plugins.captures2024.soongan.android.feature)
}

android {
    namespace = "com.captures2024.soongan.presentation.feature.root"
}

dependencies {
    implementation(projects.presentation.viewmodel)

    implementation(projects.presentation.feature.main)
    implementation(projects.presentation.feature.sign)
}
