plugins {
    captures("feature")
}

android {
    namespace = "com.captures2024.soongan.feature.main"
}

dependencies {
    implementation(project(":feature:awards"))
    implementation(project(":feature:feed"))
    implementation(project(":feature:home"))
    implementation(project(":feature:profile"))
    implementation(project(":feature:welcome"))
}