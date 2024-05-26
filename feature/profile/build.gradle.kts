plugins {
    captures("library")
    captures("compose")
}

android {
    namespace = "com.captures2024.soongan.feature.profile"
}

dependencies {
    implementation(project(":core:designSystem"))
}