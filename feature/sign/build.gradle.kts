import com.captures2024.soongan.plugin.implementation

plugins {
    alias(libs.plugins.captures2024.soongan.android.feature)
    alias(libs.plugins.captures2024.soongan.google.auth)
    alias(libs.plugins.captures2024.soongan.google.firebase)
}

android {
    namespace = "com.captures2024.soongan.feature.sign"
}

dependencies {
    implementation(projects.core.auth)

    implementation(projects.feature.privacyPolicy)
    implementation(projects.feature.signIn)
    implementation(projects.feature.signUp)
    implementation(projects.feature.termsOfUse)
}
