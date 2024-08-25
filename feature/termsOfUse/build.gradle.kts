plugins {
    captures("library")
    captures("compose")
}

android {
    namespace = "com.captures2024.soongan.feature.termsofuse"
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:designSystem"))
    implementation(project(":core:navigator"))
}