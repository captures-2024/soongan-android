import com.captures2024.soongan.plugin.implementation

plugins {
    alias(libs.plugins.captures2024.soongan.android.feature)
    alias(libs.plugins.captures2024.soongan.google.auth)
    alias(libs.plugins.captures2024.soongan.google.firebase)
}

android {
    namespace = "com.captures2024.soongan.presentation.feature.sign"
}

dependencies {
    implementation(projects.core.auth)

    implementation(projects.presentation.feature.privacyPolicy)
    implementation(projects.presentation.feature.signIn)
    implementation(projects.presentation.feature.signUp)
    implementation(projects.presentation.feature.terms)
}
