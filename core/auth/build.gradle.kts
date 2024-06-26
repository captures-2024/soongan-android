plugins {
    captures("library")
    captures("google-auth")
    captures("firebase")
}

android {
    namespace = "com.captures2024.soongan.core.auth"
}

dependencies {
    implementation(project(":core:model"))

    implementation(libs.firebase.auth)
    implementation(libs.kakao.login)
}