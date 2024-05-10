plugins {
    captures("library")
    captures("compose")
}

android {
    namespace = "com.captures2024.soongan.feature.sign"
}

dependencies {
    implementation(project(":core:analytics"))
    implementation(project(":core:common"))
    implementation(project(":core:designSystem"))

    implementation(project(":feature:privacyPolicy"))
    implementation(project(":feature:signIn"))
    implementation(project(":feature:termsOfUse"))
    implementation(project(":feature:navigator"))
}