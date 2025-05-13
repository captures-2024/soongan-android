import com.captures2024.soongan.plugin.implementation

plugins {
    alias(libs.plugins.captures2024.soongan.android.feature)
}

android {
    namespace = "com.captures2024.soongan.presentation.feature.main"
}

dependencies {
    implementation(projects.presentation.feature.mainAwards)
    implementation(projects.presentation.feature.mainFeed)
    implementation(projects.presentation.feature.mainHome)
    implementation(projects.presentation.feature.mainProfile)

    implementation(projects.presentation.viewmodel)
}
