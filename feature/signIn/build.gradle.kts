plugins {
    captures("library")
    captures("compose")
    captures("firebase")
}

android {
    namespace = "com.captures2024.soongan.feature.signIn"
}

dependencies {
    implementation(project(":core:designSystem"))

    implementation(libs.firebase.auth)
}