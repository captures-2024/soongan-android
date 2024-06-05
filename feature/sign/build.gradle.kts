plugins {
    captures("library")
    captures("compose")
    captures("google-auth")
    captures("firebase")
}

android {
    namespace = "com.captures2024.soongan.feature.sign"
}

dependencies {
    implementation(project(":core:analytics"))
    implementation(project(":core:auth"))
    implementation(project(":core:common"))
    implementation(project(":core:designSystem"))
    implementation(project(":core:model"))

    implementation(project(":feature:privacyPolicy"))
    implementation(project(":feature:signIn"))
    implementation(project(":feature:signUp"))
    implementation(project(":feature:termsOfUse"))
    implementation(project(":feature:navigator"))

    implementation(libs.firebase.auth)
}