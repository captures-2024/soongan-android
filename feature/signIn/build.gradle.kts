plugins {
    captures("library")
    captures("compose")
    captures("firebase")
    captures("test")
}

android {
    namespace = "com.captures2024.soongan.feature.signIn"
}

dependencies {
    implementation(project(":core:designSystem"))
    implementation(project(":core:model"))

    implementation(libs.firebase.auth)
}