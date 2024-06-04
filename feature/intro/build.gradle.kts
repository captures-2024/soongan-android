plugins {
    captures("library")
    captures("compose")
}

android {
    namespace = "com.captures2024.soongan.feature.intro"
}

dependencies {
    implementation(project(":core:analytics"))
    implementation(project(":core:designSystem"))
    implementation(project(":core:domain"))

    implementation(project(":feature:navigator"))
}