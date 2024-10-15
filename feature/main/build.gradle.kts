plugins {
    alias(libs.plugins.captures2024.soongan.android.feature)
}

android {
    namespace = "com.captures2024.soongan.feature.main"
}

dependencies {
    implementation(projects.feature.awards)
    implementation(projects.feature.feed)
    implementation(projects.feature.home)
    implementation(projects.feature.profile)
    implementation(projects.feature.welcome)
}
