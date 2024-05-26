plugins {
    captures("library")
    captures("compose")
}

android {
    namespace = "com.captures2024.soongan.feature.main"
}

dependencies {
    implementation(project(":core:analytics"))
    implementation(project(":core:common"))
    implementation(project(":core:designSystem"))

    implementation(project(":feature:navigator"))
    implementation(project(":feature:awards"))
    implementation(project(":feature:feed"))
    implementation(project(":feature:home"))
    implementation(project(":feature:profile"))
    implementation(project(":feature:welcome"))
}