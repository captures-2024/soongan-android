plugins {
    captures("library")
    captures("compose")
}

android {
    namespace = "com.captures2024.soongan.feature.signUp"
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:designSystem"))
    implementation(project(":core:domain"))
}