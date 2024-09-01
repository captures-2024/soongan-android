plugins {
    captures("library")
    captures("compose")
}

android {
    namespace = "com.captures2024.soongan.feature.privacypolicy"
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:designSystem"))
    implementation(project(":core:navigator"))
}