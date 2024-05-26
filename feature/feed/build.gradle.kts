plugins {
    captures("library")
    captures("compose")
}

android {
    namespace = "com.captures2024.soongan.feature.feed"
}

dependencies {
    implementation(project(":core:designSystem"))
}