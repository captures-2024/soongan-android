plugins {
    captures("library")
    captures("compose")
}

android {
    namespace = "com.captures2024.soongan.feature.home"
}

dependencies {
    implementation(project(":core:designSystem"))
    implementation(project(":core:model"))
}