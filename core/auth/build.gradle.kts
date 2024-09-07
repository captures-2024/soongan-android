plugins {
    captures("library")
    captures("google_auth")
    captures("firebase")
}

android {
    namespace = "com.captures2024.soongan.core.auth"
}

dependencies {
    implementation(project(":core:model"))

    implementation(libs.kakao.login)
}