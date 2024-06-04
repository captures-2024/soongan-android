plugins {
    captures("library")
}

android {
    namespace = "com.captures2024.soongan.core.data"
}

dependencies {
    implementation(project(":core:datastore"))
    implementation(project(":core:domain"))
}