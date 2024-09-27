plugins {
    captures("feature")
    captures("google.auth")
    captures("google.firebase")
}

android {
    namespace = "com.captures2024.soongan.feature.sign"
}

dependencies {
    implementation(project(":core:auth"))

    implementation(project(":feature:privacyPolicy"))
    implementation(project(":feature:signIn"))
    implementation(project(":feature:signUp"))
    implementation(project(":feature:termsOfUse"))
}
